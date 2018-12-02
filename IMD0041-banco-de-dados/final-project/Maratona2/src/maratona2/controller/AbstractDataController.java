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
import maratona2.exception.InvalidFieldValueException;
import maratona2.model.AbstractModel;
import org.postgresql.util.PSQLException;

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
            AbstractDataController.this.selected = newValue;
            
            if(newValue != null)
                AbstractDataController.this.setFieldsFromEntity(newValue);
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
    private void handleBtnDeleteAction(ActionEvent event)
    {
        if(this.selected != null)
        {
            try
            {
                this.model.delete(this.selected.getId());
                this.list.getItems().remove(this.selected);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(AbstractDataController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            finally
            {
                this.setNullSelection();
            }
            
            
        }
        
        else
            showAlertError("No data selected!");
    }
    
    @FXML
    private void handleBtnSaveAction(ActionEvent event) {
        if(this.selected == null)
        {
            Entity newEntity = this.getNewEntityFromFields();
            
            try
            {
                if(newEntity != null)
                {
                    try
                    {
                        int id = this.model.insert(newEntity);
                        newEntity.setId(id);
                        this.list.getItems().add(newEntity);
                    }

                    catch (PSQLException ex)
                    {
                        this.showAlertError("Invalid data!");
                    }

                    catch(SQLException ex)
                    {
                        this.showAlertError("Something nasty happened...");
                    }
                }


                else
                    this.showAlertError("Invalid data!");
            }
            
            catch(InvalidFieldValueException ex)
            {
                showAlertError(ex.getMessage());
            }
        }
        
        else
        {
            try
            {
                Entity newEntity = this.getNewEntityFromFields();
                newEntity.setId(this.selected.getId());
                this.model.update(newEntity);
                this.list.getItems().set(this.list.getItems().indexOf(this.selected), newEntity);
                
            }
            
            catch (InvalidFieldValueException ex)
            {
                this.showAlertError(ex.getMessage());
            }

            catch (PSQLException ex)
            {
                this.showAlertError("Invalid data!");
            }

            catch(SQLException ex)
            {
                this.showAlertError("Something nasty happened...");
            }
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
    
    protected abstract void setFieldsFromEntity(Entity entity);
    
    protected abstract void clearFields();
    
    protected abstract Entity getNewEntityFromFields();
   
}
