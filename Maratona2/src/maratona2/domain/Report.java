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
public class Report extends Entity{
    private int idreport;
    private int idproblem;
    private int idteam;
    private boolean solved;
    private int penalty;
    
    public Report(int id)
    {
        super(id);
    }

    public Report(int idproblem, int idteam, boolean solved, int penalty) {
        this(-1, idproblem, idteam, solved, penalty);
    }
    
    public Report(int idreport, int idproblem, int idteam, boolean solved, int penalty) {
        this(idreport);
        this.idproblem = idproblem;
        this.idteam = idteam;
        this.solved = solved;
        this.penalty = penalty;
    }

    public int getIdReport() {
        return idreport;
    }

    public void setIdReport(int idreport) {
        this.idreport = idreport;
    }

    public int getIdProblem() {
        return idproblem;
    }

    public void setIdProblem(int idproblem) {
        this.idproblem = idproblem;
    }

    public int getIdTeam() {
        return idteam;
    }

    public void setIdTeam(int idteam) {
        this.idteam = idteam;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
    
    @Override
    public String toString()
    {
        return new StringBuilder()
                .append("<")
                .append(idproblem)
                .append(", ")
                .append(idteam)
                .append(">")
                .toString();
    }
}
