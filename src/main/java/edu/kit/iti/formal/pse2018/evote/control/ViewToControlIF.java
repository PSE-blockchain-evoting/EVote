package edu.kit.iti.formal.pse2018.evote.control;

import java.awt.event.ActionListener;

public interface ViewToControlIF {

    public ActionListener getAuthenticationListener();

    public ActionListener getLogoutListener();
}