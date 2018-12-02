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
public class Coach extends Entity{
    private String name;
    
    public Coach(int id)
    {
        super(id);
    }

    public Coach(String name) {
        this(-1, name);
    }

    public Coach(int idcoach, String name) {
        super(idcoach);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
}
