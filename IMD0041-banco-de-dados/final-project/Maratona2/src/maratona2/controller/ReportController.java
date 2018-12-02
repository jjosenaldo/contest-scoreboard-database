
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
import javafx.scene.control.TextField;
import maratona2.domain.Entity;
import maratona2.domain.Problem;
import maratona2.domain.Report;
import maratona2.domain.Team;
import maratona2.exception.InvalidFieldValueException;
import maratona2.model.ProblemModel;
import maratona2.model.ReportModel;
import maratona2.model.TeamModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class ReportController extends AbstractDataController implements Initializable
{
    @FXML
    private ListView<Problem> listProblem;
    
    @FXML
    private ListView<Team> listTeam;
    
    @FXML
    private CheckBox chkSolved;
    
    @FXML
    private TextField txtPenalty;
    
    public ReportController()
    {
        super();
        this.model = new ReportModel();
    }
    
    @Override
    protected void clearFields()
    {
        this.listProblem.getSelectionModel().select(null);
        this.listTeam.getSelectionModel().select(null);
        this.txtPenalty.setText(null);
        chkSolved.setSelected(false);
    }
    
    @Override
    protected void setFieldsFromEntity(Entity entity)
    {
        Report report = (Report)entity;
        this.listProblem.getSelectionModel().select(new Problem(report.getIdProblem()));
        this.listTeam.getSelectionModel().select(new Team(report.getIdTeam()));
        this.txtPenalty.setText(String.valueOf(report.getPenalty()));
        this.chkSolved.setSelected(report.isSolved());   
    }
    
    @Override
    protected Entity getNewEntityFromFields() throws InvalidFieldValueException
    {
        Problem problem = listProblem.getSelectionModel().getSelectedItem();
        Team team = listTeam.getSelectionModel().getSelectedItem();
        
        if(problem == null || team == null)
            return null;
        
        try
        {
            int penalty = Integer.parseInt(txtPenalty.getText());
            
            return new Report(problem.getId(), team.getId(), this.chkSolved.isSelected(), penalty);
        }
        
        catch(NumberFormatException ex)
        {
            throw new InvalidFieldValueException("The penalty must be an integer!");
        }
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
    
    private void initListTeam()
    {
        ObservableList<Team> items = FXCollections.observableArrayList();
        try
        {
            items.addAll(new TeamModel().selectAll()
                .stream()
                .map(e -> (Team) e)
                .collect(Collectors.toList()));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AbstractDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        listTeam.setItems(items);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        super.initialize(url, rb);
        this.initListProblem();
        this.initListTeam();
    }    
    
}
