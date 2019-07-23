/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author josenaldo
 */
public class MainController extends AbstractController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleBtnCoachAction(ActionEvent event)
    {
        replaceSceneContent("/maratona2/view/Coach.fxml");
    }
    
    @FXML
    private void handleBtnTeamAction(ActionEvent event)
    {
        replaceSceneContent("/maratona2/view/Team.fxml");
    }
    
    @FXML
    private void handleBtnContestantAction(ActionEvent event)
    {
        replaceSceneContent("/maratona2/view/Contestant.fxml");
    }
    
    @FXML
    private void handleBtnProblemAction(ActionEvent event)
    {
        replaceSceneContent("/maratona2/view/Problem.fxml");
    }
    
    @FXML
    private void handleBtnInstanceAction(ActionEvent event)
    {
        replaceSceneContent("/maratona2/view/Instance.fxml");
    }
    
    @FXML
    private void handleBtnReportAction(ActionEvent event)
    {
        replaceSceneContent("/maratona2/view/Report.fxml");
    }
    
    @FXML 
    private void handleBtnSubmissionAction(ActionEvent event)
    {
        replaceSceneContent("/maratona2/view/Submission.fxml");
    }
}
