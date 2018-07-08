package edu.kit.iti.formal.pse2018.evote.view;

public interface ControlToViewIF {

    public void showResults();

    public void exit();

    public String getAuthenticationPath();

    public void showError(String message);

    public void showWarning(String message);

    public void showSuccess(String message);

    public boolean electionOver();

}
