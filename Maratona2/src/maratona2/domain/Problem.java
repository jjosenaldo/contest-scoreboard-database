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
public class Problem extends Entity{
    private int idproblem;
    private String name;
    private String description;
    private String input;
    private String output;
    private int timelimit;
    
    public Problem(int id)
    {
        super(id);
    }

    public Problem(String name, String description, String input, String output, int timelimit) {
        this(-1, name, description, input, output, timelimit);
    }
    
    public Problem(int idproblem, String name, String description, String input, String output, int timelimit) {
        super(idproblem);
        this.name = name;
        this.description = description;
        this.input = input;
        this.output = output;
        this.timelimit = timelimit;
    }

    public int getIdProblem() {
        return idproblem;
    }

    public void setIdProblem(int idproblem) {
        this.idproblem = idproblem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }
}
