/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import maratona2.domain.Coach;
import maratona2.domain.Entity;
import maratona2.model.CoachModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class CoachController extends AbstractDataController {
    
    @FXML
    private TextField txtName;
    
    public CoachController()
    {
        super();
        this.model = new CoachModel("INSERT INTO Coach (name) VALUES (?)","SELECT idcoach, name FROM coach", "UPDATE Coach SET name = ? WHERE idcoach = ?", "DELETE FROM Coach WHERE idcoach = ?");
    }
        
    @Override
    protected void clearFields()
    {
        txtName.setText("");
    }

    @Override
    protected void setFields(Entity entity)
    {
        txtName.setText(((Coach)entity).getName());
    }
    
    @Override
    protected Entity getNewEntityFromFields()
    {
        String name = txtName.getText();
        
        if(name == null)
            return null;
        
        else
            return new Coach(name);
    }
}
