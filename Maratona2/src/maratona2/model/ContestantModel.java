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
import maratona2.domain.Contestant;
import maratona2.domain.Entity;

/**
 *
 * @author josenaldo
 */
public class ContestantModel extends AbstractModel
{
    public ContestantModel()
    {
        super("INSERT INTO Contestant(idteam, name, nickname) VALUES (?, ?, ?)",
                "SELECT idcontestant, idteam, name, nickname FROM Contestant",
                "UPDATE Contestant SET idteam = ?, name = ?, nickname = ? WHERE idcontestant = ?",
                "DELETE FROM Contestant WHERE idcontestant = ?");
    }
    
    @Override
    protected void setPreparedStatementInsertParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        Contestant contestant = (Contestant)entity;
        statement.setInt(1, contestant.getIdTeam());
        statement.setString(2, contestant.getName());
        statement.setString(3, contestant.getNickname());
    }
    
    @Override
    protected void setIdInPreparedStatementUpdateParams(PreparedStatement statement, Entity entity) throws SQLException
    {
        statement.setInt(4, ((Contestant)entity).getId());
    }
    
    @Override
    protected void addEntityToList(ResultSet resultSet, List<Entity> list) throws SQLException
    {
        int id = resultSet.getInt("idcontestant");
        int team = resultSet.getInt("idteam");
        String name = resultSet.getString("name");
        String nickname = resultSet.getString("nickname");
        Contestant contestant = new Contestant(id, team, name, nickname);
        list.add(contestant);
    }
}
