package edu.kit.iti.formal.pse2018.evote.view;

import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorGUI;
import edu.kit.iti.formal.pse2018.evote.view.voterview.VoterGUI;

import java.awt.Font;
import java.util.Locale;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class VoterMain {

    /**
     * Program Entry point. Starts GUI for Voter.
     * @param args irrelevant
     */
    public static void main(String[] args) {
        Locale.setDefault(new Locale("de", "DE"));
        UIManager.put("Title.font", new FontUIResource("Sans Serif", Font.BOLD, 35));
        UIManager.put("General.font", new FontUIResource("Sans Serif", Font.BOLD, 15));
        UIManager.put("Vote.font", new FontUIResource("Sans Serif", Font.BOLD, 30));

        VoterGUI gui = new VoterGUI();
    }
}
