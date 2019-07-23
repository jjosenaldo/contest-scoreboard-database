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
public abstract class Entity
{
    protected int id;
    
    public Entity(int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Entity other = (Entity) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }
    
    
}
