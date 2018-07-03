package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.CandidatePanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.FinishPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.GeneralConfigPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.TimespanPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.VoterPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javax.swing.JFrame;

/**
 * ConfigGUI is a JFrame which allows the configuration of an election by the user.
 */
public class ConfigGUI extends JFrame {
    private VerticalTabs tabsLayout;

    private GeneralConfigPanel generalConfigPanel;
    private TimespanPanel timespanPanel;
    private CandidatePanel candidatePanel;
    private VoterPanel voterPanel;
    private FinishPanel finishPanel;

    /**
     * Creates an instance of ConfigGUI
     */
    public ConfigGUI(){
        //File f = new File("src/main/java/edu/kit/iti/formal/pse2018/evote/SupervisorConfig_de_DE.properties");
        //try {
        //    BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
        //    while (br.ready()) {
        //        System.out.println(br.readLine());
        //    }
        //} catch (FileNotFoundException e) {
        //    e.printStackTrace();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        //System.out.println(f.exists());

        tabsLayout = new VerticalTabs(5);

        this.getContentPane().add(tabsLayout);

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");

        generalConfigPanel = new GeneralConfigPanel(tabsLayout.getTabPanel(0), this, tabsLayout);
        tabsLayout.setTabText(0, lang.getString("GeneralTab"));
        timespanPanel= new TimespanPanel(tabsLayout.getTabPanel(1), this, tabsLayout);
        tabsLayout.setTabText(1, lang.getString("TimespanTab"));
        candidatePanel = new CandidatePanel(tabsLayout.getTabPanel(2), this, tabsLayout);
        tabsLayout.setTabText(2, lang.getString("CandidateTab"));
        voterPanel = new VoterPanel(tabsLayout.getTabPanel(3), this, tabsLayout);
        tabsLayout.setTabText(3, lang.getString("VoterTab"));
        finishPanel = new FinishPanel(tabsLayout.getTabPanel(4), this, tabsLayout);
        tabsLayout.setTabText(4, lang.getString("FinishTab"));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(lang.getString("Title"));

        this.getContentPane().add(tabsLayout);

        this.setVisible(true);
    }
}
