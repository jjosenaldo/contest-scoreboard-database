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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import maratona2.domain.Entity;
import maratona2.domain.Instance;
import maratona2.domain.Problem;
import maratona2.model.InstanceModel;
import maratona2.model.ProblemModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class InstanceController extends AbstractDataController implements Initializable
{
    @FXML 
    private ListView<Problem> listProblem;
    
    @FXML 
    private TextArea txtInput;
    
    @FXML 
    private TextArea txtOutput;
    
    @FXML
    private CheckBox chkBlind;
    
    public InstanceController()
    {
        super();
        this.model = new InstanceModel();
    }
    
    @Override
    protected void clearFields()
    {
        this.listProblem.getSelectionModel().select(null);
        txtInput.setText("");
        txtOutput.setText("");
        chkBlind.setSelected(false);
    }

    @Override
    protected void setFieldsFromEntity(Entity entity)
    {
        Instance instance = (Instance)entity;
        this.listProblem.getSelectionModel().select(new Problem(instance.getIdProblem()));
        this.txtInput.setText(instance.getInput());
        this.txtOutput.setText(instance.getOutput());
        this.chkBlind.setSelected(instance.isBlind());   
    }
    
    @Override
    protected Entity getNewEntityFromFields()
    {
        String input = txtInput.getText();
        String output = txtOutput.getText();
        
        if(input == null || output == null)
            return null;
        
        Problem problem = listProblem.getSelectionModel().getSelectedItem();
        
        if(problem == null)
            return null;
        
        else
            return new Instance(problem.getId(), input, output, this.chkBlind.isSelected());
    }
    
    private void initListProblem()
    {
        ObservableList<Problem> items = FXCollections.observableArrayList();
        try
        {
            items.addAll(new ProblemModel().selectAll()
                .stream()
                .map(e -> (Problem) e)
                .collect(Collectors.toList()));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AbstractDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        listProblem.setItems(items);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        super.initialize(url, rb);
        this.initListProblem();
    }    
    
}
