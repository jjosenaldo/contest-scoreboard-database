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
public class Problem {
    private int idproblem;
    private String description;
    private String input;
    private String output;
    private int timelimit;

    public Problem(String description, String input, String output, int timelimit) {
        this(-1, description, input, output, timelimit);
    }
    
    public Problem(int idproblem, String description, String input, String output, int timelimit) {
        this.idproblem = idproblem;
        this.description = description;
        this.input = input;
        this.output = output;
        this.timelimit = timelimit;
    }

    public int getIdproblem() {
        return idproblem;
    }

    public void setIdproblem(int idproblem) {
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
    
    
}
