package edu.kit.iti.formal.pse2018.evote.utils;

public class ConfigIssuesImpl implements ConfigIssues {

    private String nameIssue;
    private String candidateIssue;
    private String voterIssue;
    private String timespanIssue;

    public void setNameIssue(String nameIssue) {
        this.nameIssue = nameIssue;
    }

    public void setCandidateIssue(String candidateIssue) {
        this.candidateIssue = candidateIssue;
    }

    public void setVoterIssue(String voterIssue) {
        this.voterIssue = voterIssue;
    }

    public void setTimespanIssue(String timespanIssue) {
        this.timespanIssue = timespanIssue;
    }


    @Override
    public String getNameIssue() {
        return nameIssue;
    }

    @Override
    public String getCandidateIssue() {
        return candidateIssue;
    }

    @Override
    public String getVoterIssue() {
        return voterIssue;
    }

    @Override
    public String getTimespanIssue() {
        return timespanIssue;
    }
}
