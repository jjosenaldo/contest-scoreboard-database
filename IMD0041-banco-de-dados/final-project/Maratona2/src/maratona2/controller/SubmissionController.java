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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import maratona2.domain.Entity;
import maratona2.domain.Report;
import maratona2.domain.Submission;
import maratona2.exception.InvalidFieldValueException;
import maratona2.model.ReportModel;
import maratona2.model.SubmissionModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class SubmissionController extends AbstractDataController implements Initializable
{
    @FXML
    private ListView<Report> listReport;
    
    @FXML
    private TextField txtTime;
    
    @FXML
    private TextField txtDuration;
    
    @FXML
    private ListView<String> listResult;
    
    @FXML
    private TextArea txtSolution;
    
    public SubmissionController()
    {
        super();
        this.model = new SubmissionModel();
    }
    
    @Override
    protected void clearFields()
    {
        this.listReport.getSelectionModel().select(null);
        this.listResult.getSelectionModel().select(null);
        this.txtTime.setText(null);
        this.txtDuration.setText(null);
        this.txtSolution.setText(null);
    }
    
    @Override
    protected void setFieldsFromEntity(Entity entity)
    {
        Submission submission = (Submission)entity;
        this.listReport.getSelectionModel().select(new Report(submission.getIdReport()));
        this.listResult.getSelectionModel().select(submission.getResult());
        this.txtTime.setText(String.valueOf(submission.getTime()));
        this.txtDuration.setText(String.valueOf(submission.getDuration()));
        this.txtSolution.setText(submission.getSolution());
    }
    
    @Override
    protected Entity getNewEntityFromFields() throws InvalidFieldValueException
    {
        Report report = listReport.getSelectionModel().getSelectedItem();
        String result = listResult.getSelectionModel().getSelectedItem();
        
        if(report == null || result == null)
            return null;
        
        try
        {
            int duration = Integer.parseInt(txtDuration.getText());
            
            try
            {
                int time = Integer.parseInt(txtTime.getText());
            
                return new Submission(report.getId(), time, duration, result, txtSolution.getText());
            }
            
            catch(NumberFormatException ex)
            {
                throw new InvalidFieldValueException("The time must be an integer!");
            }
            
        }
        
        catch(NumberFormatException ex)
        {
            throw new InvalidFieldValueException("The duration must be an integer!");
        }
    }
    
    private void initListProblem()
    {
        ObservableList<Report> items = FXCollections.observableArrayList();
        try
        {
            items.addAll(new ReportModel().selectAll()
                .stream()
                .map(e -> (Report) e)
                .collect(Collectors.toList()));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AbstractDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        listReport.setItems(items);
    }
    
    private void initListResult()
    {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add("ACC");
        items.add("RTE");
        items.add("WA");
        items.add("TLE");
        items.add("CE");
        listResult.setItems(items);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        super.initialize(url, rb);
        initListProblem();
        initListResult();
    }    
    
}
