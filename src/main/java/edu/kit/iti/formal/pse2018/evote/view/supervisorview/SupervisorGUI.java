package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol.SupervisorControl;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.statemanagement.SupervisorElection;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * This class creates the Main GUI for the supervisor.
 */
public class SupervisorGUI extends JFrame implements SupervisorControlToViewIF {

    private SupervisorAdapter adapter;

    private SupervisorVSComponentManager componentManager;

    private ConfigGUI config;

    private JLabel lblTitle;

    private JPanel pnlLogo;
    private SupervisorGUIPanel currentPanel;

    private GroupLayout layout;

    private int titleBarHeight = 100;
    private int titleBarHeightMin = 50;
    private int logoWidth = 150;
    private int logoWidthMin = 50;

    /**
     * Creates a new instance of SupervisorGUI.
     */
    public SupervisorGUI() {
        ElectionStatusListener listener = new SupervisorElectionEndListenerImpl(this);

        SupervisorViewToModelIF model = new SupervisorElection(listener);
        SupervisorViewToControlIF control = new SupervisorControl(this, model);
        adapter = new SupervisorAdapter(control, model);

        showAuthentication();

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    private void initComponents() {

        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont((Font) UIManager.get("Title.font"));
        lblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlLogo = new JPanel();
        pnlLogo.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        layout = new GroupLayout(this.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(pnlLogo, titleBarHeightMin, titleBarHeight, titleBarHeight)
                .addComponent(lblTitle, titleBarHeightMin, titleBarHeight, titleBarHeight)
            )
            .addComponent(currentPanel)
        );

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlLogo, logoWidthMin, logoWidth, logoWidth)
                .addComponent(lblTitle, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
            .addComponent(currentPanel)
        );
        this.getContentPane().setLayout(layout);
    }

    /**
     * Shows the Authentication Panel in the SupervisorGUI.
     */
    public void showAuthentication() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");

        currentPanel = new SupervisorAuthentication(adapter);
        initComponents();
        lblTitle.setText(lang.getString("supervisorAuthenticationTitle"));
    }

    @Override
    public void showFrontpage() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");

        currentPanel = new SupervisorFrontpage(adapter);
        initComponents();
        lblTitle.setText(lang.getString("supervisorFrontpageTitle"));
    }

    @Override
    public void showResults() {
        ElectionDataIF data = adapter.getElectionData();
        VotingSystemType vs = data.getVotingSystem();
        componentManager = SupervisorVSComponentManagerBuilder.generateComponentManager(vs, adapter);

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        SupervisorGUIPanel p = new SupervisorResult(adapter, componentManager);

        layout.replace(currentPanel, p);
        currentPanel = p;

        lblTitle.setText(data.getName());
    }

    @Override
    public void startConfigMenu() {
        config = new ConfigGUI(adapter);
        config.setVisible(true);
        this.setVisible(false);
    }

    @Override
    public void loadConfigData() {
        config.loadConfigData();
    }

    @Override
    public String[] getVoters() {
        return config.getVoters();
    }

    @Override
    public ElectionDataIF getElectionData() {
        return config.getElectionData();
    }

    @Override
    public String getImportPath() {
        return currentPanel.getImportPath();
    }

    @Override
    public String getExportPath() {
        return config.getExportPath();
    }

    @Override
    public void showConfigIssues() {
        config.showConfigIssues();
    }

    @Override
    public String getPassword() {
        return currentPanel.getPassword();
    }

    @Override
    public String getUsername() {
        return currentPanel.getUsername();
    }

    @Override
    public void updateResult() {
        currentPanel.updateResults(adapter.getResults(), adapter.getWinner());
    }

    @Override
    public void exit() {

    }

    @Override
    public String getAuthenticationPath() {
        return currentPanel.getCertPath();
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void electionOver() {
        showResults();
    }
}