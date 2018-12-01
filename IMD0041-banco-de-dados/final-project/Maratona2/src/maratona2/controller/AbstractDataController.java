/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import maratona2.domain.Entity;
import maratona2.model.AbstractModel;

/**
 *
 * @author josenaldo
 */
public abstract class AbstractDataController extends AbstractController
{
    protected Entity selected;
    
    protected AbstractModel model;
    
    @FXML
    protected Button btnUnselect;
    
    @FXML
    protected Button btnSave;
    
    @FXML
    protected Button btnBack;
    
    @FXML
    protected ListView<Entity> list;
    
    public AbstractDataController()
    {
        this.selected = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeList();
        
    }   
    
    private void initializeList()
    {
        ObservableList<Entity> items = FXCollections.observableArrayList();
        try
        {
            items.addAll(model.selectAll());
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AbstractDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        list.setItems(items);
        
        list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Entity> observable, Entity oldValue, Entity newValue) ->
        {
            if(newValue != null)
            {
                AbstractDataController.this.selected = newValue;
                AbstractDataController.this.setFields(newValue);
            }
            
        });
    }
    
    @FXML
    private void handleBtnBackAction(ActionEvent event)
    {
        super.replaceSceneContent("/maratona2/view/Main.fxml");
    }
    
    @FXML
    private void handleBtnUnselectAction(ActionEvent event)
    {
        Entity selectedItem = this.list.getSelectionModel().getSelectedItem();
        
        if(selectedItem == null)
            showAlertError("No selected items!");
        
        else
            this.setNullSelection();
    }
    
    @FXML
    private void handleBtnDeleteAction(ActionEvent event) throws SQLException
    {
        if(this.selected != null)
        {
            this.model.delete(this.selected.getId());
            this.list.getItems().remove(this.selected);
            
            this.setNullSelection();
        }
        
        else
            showAlertError("No data selected!");
    }
    
    @FXML
    private void handleBtnSaveAction(ActionEvent event) throws SQLException {
        if(this.selected == null)
        {
            Entity newEntity = this.getNewEntity();
            
            if(newEntity != null)
                this.model.insert(newEntity);
            
            else
                this.showAlertError("Invalid data!");
        }
        
        else
        {
        //    this.coachModel.update(this.selected);
        }
        
        this.setNullSelection();
            
    }
    
    protected void showAlertError(String msg)
    {
        Alert alert = new Alert(AlertType.ERROR, msg);
        alert.showAndWait();
    }
    
    protected void setNullSelection()
    {
        this.list.getSelectionModel().select(null);
        this.selected = null;
        this.clearFields();
    }
    
    protected abstract void setFields(Entity entity);
    
    protected abstract void clearFields();
    
    protected abstract Entity getNewEntity();
}
