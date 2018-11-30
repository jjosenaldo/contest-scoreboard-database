/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import maratona2.Main;
import maratona2.domain.Coach;
import maratona2.model.CoachModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class CoachController implements Initializable {
    private final CoachModel coachModel; 
    private Coach selectedCoach;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnBack;
    
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
                if(!txtName.getText().trim().isEmpty())
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
    
    @FXML
    private void handleBtnDeleteAction(ActionEvent event)
    {
        if(this.selectedCoach != null)
        {
            this.coachModel.delete(this.selectedCoach);
            this.selectedCoach = null;
        }
    }
    
    @FXML
    private void handleBtnBackAction(ActionEvent event)
    {
        Parent page;
        Stage stage = Main.getMainStage();
        String fxml = "/maratona2/view/Main.fxml";

        try
        {
            page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
            Scene scene = stage.getScene();
            if (scene == null)
            {
                scene = new Scene(page);
                stage.setScene(scene);
            }

            else
            {
                stage.getScene().setRoot(page);
            }
            stage.sizeToScene();
        
        }
        
        catch (IOException ex)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void clearFields()
    {
        txtName.setText("");
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
