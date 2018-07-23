package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.ElectionDataImpl;
import edu.kit.iti.formal.pse2018.evote.view.components.ImagePanel;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.CandidatePanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.FinishPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.GeneralConfigPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.TimespanPanel;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels.VoterPanel;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * ConfigGUI is a JFrame which allows the configuration of an election by the user.
 */
public class ConfigGUI extends JDialog {

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
        tabsLayout.setTabFont((Font) UIManager.get("General.font"));

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

        ActiveListener[] l = new ActiveListener[5];
        l[0] = generalConfigPanel;
        l[1] = timespanPanel;
        l[2] = candidatePanel;
        l[3] = voterPanel;
        l[4] = finishPanel;

        tabsLayout.setActiveListener(l);

        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setTitle(lang.getString("Title"));

        lblTitle = new JLabel(lang.getString("Title"));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont((Font) UIManager.get("Title.font"));
        lblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlLogo = new JPanel();
        try {
            pnlLogo = new ImagePanel(ImageIO.read(new File("src/main/resources/logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
            pnlLogo = new JPanel();
            pnlLogo.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
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

    /**
     * Show the config problems in the UI.
     */
    public void showConfigIssues() {
        ConfigIssues ci = adapter.getConfigIssues();

        String prefix = "<html><ul>";
        String startTag = "<li>";
        String endTag = "</li>";

        String text = "";
        text += prefix;

        String ni = ci.getNameIssue();
        if (ni != null) {
            text += startTag + ni + endTag;
        }

        String ti = ci.getTimespanIssue();
        if (ti != null) {
            text += startTag + ti + endTag;
        }

        String cdi = ci.getCandidateIssue();
        if (cdi != null) {
            text += startTag + cdi + endTag;
        }

        String vi = ci.getVoterIssue();
        if (vi != null) {
            text += startTag + vi + endTag;
        }

        String suffix = "</ul></html>";
        text += suffix;

        finishPanel.setConfigIssuesText(text);
    }


    /**
     * Loads the ElectionData from model and presents it in the ConfigGUI.
     */
    public void loadConfigData() {
        ElectionDataIF data = adapter.getElectionData();

        generalConfigPanel.setElectionName(data.getName());
        generalConfigPanel.setElectionDescription(data.getDescription());
        generalConfigPanel.setVotingSystem(data.getVotingSystem());
        timespanPanel.setStartDate(data.getStartDate());
        timespanPanel.setEndDate(data.getEndDate());
        timespanPanel.setEndCondition(data.getEndCondition());
        candidatePanel.setCandidateNames(data.getCandidates());
        candidatePanel.setCandidateDescriptions(data.getCandidateDescriptions());
        String[] voters = adapter.getVoters();
        voterPanel.setVoters(voters);

        tabsLayout.setSelected(4);
    }

    /**
     * Access the entered Election Data.
     *
     * @return The Election Data.
     */
    public ElectionDataIF getElectionData() {
        ElectionDataImpl data = new ElectionDataImpl();

        data.setName(generalConfigPanel.getElectionName());
        data.setDesc(generalConfigPanel.getElectionDescription());
        data.setType(generalConfigPanel.getVotingSystem());
        data.setStart(timespanPanel.getStartDate());
        data.setEnd(timespanPanel.getEndDate());
        data.setEec(timespanPanel.getEndCondition());
        data.setCandidates(candidatePanel.getCandidateNames());
        data.setCandidatesDesc(candidatePanel.getCandidateDescriptions());
        data.setVoterCount(voterPanel.getVoters().length);

        return data;
    }

    /**
     * Set ElectionData in the ConfigGUI.
     *
     * @param data The data to set.
     */
    public void setElectionData(ElectionDataIF data) {
        generalConfigPanel.setElectionName(data.getName());
        generalConfigPanel.setElectionDescription(data.getDescription());
        generalConfigPanel.setVotingSystem(data.getVotingSystem());
        timespanPanel.setStartDate(data.getStartDate());
        timespanPanel.setEndDate(data.getEndDate());
        timespanPanel.setEndCondition(data.getEndCondition());
        candidatePanel.setCandidateNames(data.getCandidates());
        candidatePanel.setCandidateDescriptions(data.getCandidateDescriptions());
    }

    public void setVoters(String[] voters) {
        voterPanel.setVoters(voters);
    }

    public String[] getVoters() {
        return voterPanel.getVoters();
    }

    @Override
    public void setTitle(String s) {
        super.setTitle(s);
        if (lblTitle != null) {
            lblTitle.setText(s);
        }
    }

    public String getExportPath() {
        return finishPanel.getExportPath();
    }
}
