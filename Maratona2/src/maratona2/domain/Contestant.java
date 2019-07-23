/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.domain;

/**
 *
 * @author josenaldo
 */
public class Contestant extends Entity{
    private int idcontestant;
    private int idteam;
    private String name;
    private String nickname;
    
    public Contestant(int idteam, String name, String nickname)
    {
        this(-1, idteam, name, nickname);
    }

    public Contestant(int idcontestant, int idteam, String name, String nickname) {
        super(idcontestant);
        this.idteam = idteam;
        this.name = name;
        this.nickname = nickname;
    }

    public int getIdcontestant() {
        return idcontestant;
    }

    public void setIdcontestant(int idcontestant) {
        this.idcontestant = idcontestant;
    }

    public int getIdTeam() {
        return idteam;
    }

    public void setIdTeam(int idteam) {
        this.idteam = idteam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    @Override
    public String toString()
    {
        return nickname != null ? nickname : name;
    }
}
