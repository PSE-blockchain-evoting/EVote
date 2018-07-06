package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.CandidatePanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.ConfigPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.FinishPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.GeneralConfigPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.TimespanPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.VoterPanel;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * ConfigGUI is a JFrame which allows the configuration of an election by the user.
 */
public class ConfigGUI extends JFrame {

    private SupervisorAdapter adapter;

    private VerticalTabs tabsLayout;

    private GeneralConfigPanel generalConfigPanel;
    private TimespanPanel timespanPanel;
    private CandidatePanel candidatePanel;
    private VoterPanel voterPanel;
    private FinishPanel finishPanel;

    private JLabel lblTitle;
    private JPanel pnlLogo;

    private GroupLayout layout;

    private int sideBarWidth = 200;
    private int sideBarWidthMin = 50;
    private int titleBarHeight = 100;
    private int titleBarHeightMin = 50;

    /**
     * Creates an instance of ConfigGUI.
     */
    public ConfigGUI(SupervisorAdapter adapter) {
        this.adapter = adapter;

        tabsLayout = new VerticalTabs(5, sideBarWidthMin, sideBarWidth);

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");

        generalConfigPanel = new GeneralConfigPanel(tabsLayout.getTabPanel(0), this, tabsLayout, adapter);
        tabsLayout.setTabText(0, lang.getString("GeneralTab"));
        timespanPanel = new TimespanPanel(tabsLayout.getTabPanel(1), this, tabsLayout, adapter);
        tabsLayout.setTabText(1, lang.getString("TimespanTab"));
        candidatePanel = new CandidatePanel(tabsLayout.getTabPanel(2), this, tabsLayout, adapter);
        tabsLayout.setTabText(2, lang.getString("CandidateTab"));
        voterPanel = new VoterPanel(tabsLayout.getTabPanel(3), this, tabsLayout, adapter);
        tabsLayout.setTabText(3, lang.getString("VoterTab"));
        finishPanel = new FinishPanel(tabsLayout.getTabPanel(4), this, tabsLayout, adapter);
        tabsLayout.setTabText(4, lang.getString("FinishTab"));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(lang.getString("Title"));

        lblTitle = new JLabel(lang.getString("Title"));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont((Font) UIManager.get("Title.font"));
        lblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlLogo = new JPanel();
        pnlLogo.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        buildLayout();
    }

    private void buildLayout() {
        layout = new GroupLayout(this.getContentPane());

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(pnlLogo, titleBarHeightMin, titleBarHeight, titleBarHeight)
                        .addComponent(lblTitle, titleBarHeightMin, titleBarHeight, titleBarHeight)
                )
                .addComponent(tabsLayout)
        );

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlLogo, sideBarWidthMin, sideBarWidth, sideBarWidth)
                        .addComponent(lblTitle, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
                .addComponent(tabsLayout)
        );
        this.getContentPane().setLayout(layout);
    }
}
