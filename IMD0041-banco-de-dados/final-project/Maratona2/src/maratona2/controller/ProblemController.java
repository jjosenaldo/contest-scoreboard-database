/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import maratona2.domain.Entity;
import maratona2.domain.Problem;
import maratona2.exception.InvalidFieldValueException;
import maratona2.model.AbstractModel;
import maratona2.model.ProblemModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class ProblemController extends AbstractDataController implements Initializable
{
    @FXML
    private TextField txtName;
    
    @FXML 
    private TextArea txtDescription;    
    
    @FXML 
    private TextArea txtInput;    
    
    @FXML 
    private TextArea txtOutput;
    
    @FXML
    private TextField txtTimelimit;

    public ProblemController()
    {
        super();
        this.model = new ProblemModel();
    }    
    
    @Override
    protected void setFieldsFromEntity(Entity entity)
    {
        Problem problem = (Problem)entity;
        this.txtName.setText(problem.getName());
        this.txtDescription.setText(problem.getDescription());
        this.txtInput.setText(problem.getInput());
        this.txtOutput.setText(problem.getOutput());
        this.txtTimelimit.setText(String.valueOf(problem.getTimelimit()));
    }
    
    @Override
    protected Entity getNewEntityFromFields() throws InvalidFieldValueException
    {
        String name = txtName.getText();
        String description = txtDescription.getText();
        String input = txtInput.getText();
        String output = txtOutput.getText();
        
        if(name == null || description == null || input == null || output == null)
            return null;
        
        try
        {
            int timelimit = Integer.parseInt(txtTimelimit.getText());
            return new Problem(name, description, input, output, timelimit);
        }
        
        catch(NumberFormatException ex)
        {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
            throw new InvalidFieldValueException("The time limit must be a number!");
        }
    }
    
    @Override
    protected void clearFields()
    {
        txtName.setText("");
        txtDescription.setText("");
        txtInput.setText("");
        txtOutput.setText("");
        txtTimelimit.setText("");
    }
    
}
