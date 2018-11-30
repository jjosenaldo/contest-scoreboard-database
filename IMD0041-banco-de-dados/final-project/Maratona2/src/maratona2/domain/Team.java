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
public class Team {
    private int idteam;
    private int idcoach;
    private String name;
    private String college;

    public Team(int idcoach, String name, String college) {
        this(-1, idcoach, name, college);
    }
    
    public Team(int idteam, int idcoach, String name, String college) {
        this.idteam = idteam;
        this.idcoach = idcoach;
        this.name = name;
        this.college = college;
    }
    
    public int getIdteam() {
        return idteam;
    }

    public void setIdteam(int idteam) {
        this.idteam = idteam;
    }

    public int getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(int idcoach) {
        this.idcoach = idcoach;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
    
    
}
