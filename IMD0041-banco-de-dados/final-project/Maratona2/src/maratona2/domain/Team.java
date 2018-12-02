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
public class Team extends Entity{
    private int idcoach;
    private String name;
    private String college;
    private String about;

    public Team(int idcoach, String name, String college, String about) {
        this(-1, idcoach, name, college, about);
    }
    
    public Team(int idteam, int idcoach, String name, String college, String about) {
        super(idteam);
        this.idcoach = idcoach;
        this.name = name;
        this.college = college;
        this.about = about;
    }

    public int getIdCoach() {
        return idcoach;
    }

    public void setIdCoach(int idcoach) {
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

    public String getAbout()
    {
        return about;
    }

    public void setAbout(String about)
    {
        this.about = about;
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
}
