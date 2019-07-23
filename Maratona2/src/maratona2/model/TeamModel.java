/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import maratona2.domain.Entity;
import maratona2.domain.Team;

/**
 *
 * @author josenaldo
 */
public class TeamModel extends AbstractModel
{
    public TeamModel()
    {
        super("INSERT INTO Team(idcoach, name, college, about) VALUES (?, ?, ?, ?)",
                "SELECT idteam, idcoach, name, college, about FROM Team",
                "UPDATE Team SET idcoach = ?, name = ?, college = ?, about = ? WHERE idteam = ?",
                "DELETE FROM Team WHERE idteam = ?");
    }
    
    @Override
    protected void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        Team team = (Team)entity;
        statement.setInt(1, team.getIdCoach());
        statement.setString(2, team.getName());
        statement.setString(3, team.getCollege());
        statement.setString(4, team.getAbout());
    }
    
    @Override
    protected void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setInt(5, ((Team)entity).getId());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        int id = resultSet.getInt("idteam");
        int coach = resultSet.getInt("idcoach");
        String name = resultSet.getString("name");
        String college = resultSet.getString("college");
        String about = resultSet.getString("about");
        Team team = new Team(id, coach, name, college, about);
        list.add(team);
    }
}
