/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import maratona2.domain.Entity;

/**
 *
 * @author josenaldo
 */
public abstract class AbstractDataController extends AbstractController
{
    protected Entity selected;
    
    @FXML
    protected Button btnSave;
    
    @FXML
    protected Button btnBack;
    
    @FXML
    private void handleBtnBackAction(ActionEvent event)
    {
        super.replaceSceneContent("/maratona2/view/Main.fxml");
    }
    
    protected abstract void clearFields();
}
