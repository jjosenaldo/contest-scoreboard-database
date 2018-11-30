/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.controller;

import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    }   
    
    @FXML
    private void handleBtnBackAction(ActionEvent event)
    {
        super.replaceSceneContent("/maratona2/view/Main.fxml");
    }
    
    @FXML
    private void handleBtnDeleteAction(ActionEvent event) throws SQLException
    {
        if(this.selected != null)
        {
            this.model.delete(this.selected.getId());
            this.selected = null;
        }
    }
    
    protected abstract void clearFields();
}
