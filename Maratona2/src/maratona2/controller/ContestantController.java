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
import javafx.scene.control.TextField;
import maratona2.domain.Contestant;
import maratona2.domain.Entity;
import maratona2.domain.Team;
import maratona2.model.ContestantModel;
import maratona2.model.TeamModel;

/**
 * FXML Controller class
 *
 * @author josenaldo
 */
public class ContestantController extends AbstractDataController implements Initializable
{
    @FXML
    private TextField txtName;
    
    @FXML
    private TextField txtNickname;
    
    @FXML
    private ListView<Team> listTeam;
    
    public ContestantController()
    {
        super();
        this.model = new ContestantModel();
    }
    
    @Override
    protected void clearFields()
    {
        txtName.setText("");
        txtNickname.setText("");
        this.listTeam.getSelectionModel().select(null);
    }

    @Override
    protected void setFieldsFromEntity(Entity entity)
    {
        Contestant contestant = (Contestant)entity;
        this.txtName.setText(contestant.getName());
        this.txtNickname.setText(contestant.getNickname());
        this.listTeam.getSelectionModel().select(new Team(contestant.getIdTeam()));
    }
    
    @Override
    protected Entity getNewEntityFromFields()
    {
        String name = txtName.getText();
        String nickname = txtNickname.getText();
        
        if(name == null)
            return null;
        
        Team team = listTeam.getSelectionModel().getSelectedItem();
        
        if(team == null)
            return null;
        
        else
            return new Contestant(team.getId(), name, nickname);
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
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url,rb);
        initListTeam();
    }
    
}
