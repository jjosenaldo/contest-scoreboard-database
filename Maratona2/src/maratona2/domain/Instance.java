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
public class Instance extends Entity{
    private int idinstance;
    private int idproblem;
    private String input;
    private String output;
    private boolean blind;
    
    public Instance(int id)
    {
        super(id);
    }
    
    public Instance(int idproblem, String input, String output, boolean blind) {
        this(-1, idproblem, input, output, blind);
    }

    public Instance(int idinstance, int idproblem, String input, String output, boolean blind) {
        this(idinstance);
        this.idproblem = idproblem;
        this.input = input;
        this.output = output;
        this.blind = blind;
    }

    public int getIdInstance() {
        return idinstance;
    }

    public void setIdInstance(int idinstance) {
        this.idinstance = idinstance;
    }

    public int getIdProblem() {
        return idproblem;
    }

    public void setIdProblem(int idproblem) {
        this.idproblem = idproblem;
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

    public boolean isBlind() {
        return blind;
    }

    public void setBlind(boolean blind) {
        this.blind = blind;
    }
    
    @Override
    public String toString()
    {
        return new StringBuilder()
                .append("<")
                .append(input)
                .append(", ")
                .append(output)
                .append(">")
                .toString();
    }
}
