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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import maratona2.domain.Coach;
import maratona2.model.CoachModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class CoachController implements Initializable {
    private CoachModel coachModel; 
    private Coach selectedCoach;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private Button btnSave;
    
    public CoachController()
    {
        this.coachModel = new CoachModel();
        this.selectedCoach = null;
    }
        
    @FXML
    private void handleBtnSaveAction(ActionEvent event) {
        if(this.selectedCoach == null)
        {
            try
            {
                this.coachModel.insert(new Coach(txtName.getText()));
            }
            
            catch (SQLException ex)
            {
                Logger.getLogger(CoachController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.clearFields();
        }
        
        else
        {
            this.coachModel.update(this.selectedCoach);
            this.selectedCoach = null;
            this.clearFields();
        }
            
    }
    
    @FXML private void handleBtnDeleteAction(ActionEvent event)
    {
        if(this.selectedCoach != null)
        {
            this.coachModel.delete(this.selectedCoach);
            this.selectedCoach = null;
        }
    }
    
    private void clearFields()
    {
        txtName.setText("");
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
