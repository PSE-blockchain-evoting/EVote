package edu.kit.iti.formal.pse2018.evote.view;

import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorGUI;

import java.util.Locale;

public class Main {

    /**
     * Program Entry point. Starts respective Main GUI either for Supervisor
     * or for Voter
     * @param args irrelevant
     */
    public static void main(String[] args) {
        Locale.setDefault(new Locale("de", "DE"));

        SupervisorGUI gui = new SupervisorGUI();
        //ConfigGUI cg = new ConfigGUI();
    }
}
