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
import maratona2.domain.Coach;
import maratona2.domain.Entity;
import maratona2.domain.Team;
import maratona2.model.CoachModel;
import maratona2.model.TeamModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class TeamController extends AbstractDataController implements Initializable
{
    @FXML
    private TextField txtName;
    
    @FXML
    private TextField txtCollege;
    
    @FXML
    private TextArea txtAbout;
    
    @FXML
    private ListView<Coach> listCoach;
    
    public TeamController()
    {
        super();
        this.model = new TeamModel();
    }
    
    @Override
    protected void clearFields()
    {
        txtName.setText("");
        txtCollege.setText("");
        txtAbout.setText("");
        this.listCoach.getSelectionModel().select(null);
    }

    @Override
    protected void setFieldsFromEntity(Entity entity)
    {
        Team team = (Team)entity;
        this.txtName.setText(team.getName());
        this.txtCollege.setText(team.getCollege());
        this.txtAbout.setText(team.getAbout());
        this.listCoach.getSelectionModel().select(new Coach(team.getIdCoach()));
    }
    
    @Override
    protected Entity getNewEntityFromFields()
    {
        String name = txtName.getText();
        String college = txtCollege.getText();
        String about = txtAbout.getText();
        
        if(name == null || college == null)
            return null;
        
        Coach coach = listCoach.getSelectionModel().getSelectedItem();
        
        if(coach == null)
            return null;
        
        else
            return new Team(coach.getId(), name, college, about);
    }
    
    private void initListCoach()
    {
        ObservableList<Coach> items = FXCollections.observableArrayList();
        try
        {
            items.addAll(new CoachModel().selectAll()
                .stream()
                .map(e -> (Coach) e)
                .collect(Collectors.toList()));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AbstractDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        listCoach.setItems(items);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url,rb);
        initListCoach();
    } 
}
