package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.control.VoterViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.control.votercontrol.VoterControl;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.statemanagement.VoterElection;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * VoterGUI is the user interface for the Voter in an election.
 */
public class VoterGUI extends JFrame implements VoterControlToViewIF {

    private VoterAdapter adapter;

    private VoterVSComponentManager componentManager;

    private JLabel lblTitle;

    private JPanel pnlLogo;
    private VoterGUIPanel currentPanel;

    private GroupLayout layout;

    private int titleBarHeight = 100;
    private int titleBarHeightMin = 50;
    private int logoWidth = 150;
    private int logoWidthMin = 50;

    /**
     * Creates an instance of VoterGUI.
     */
    public VoterGUI() {
        ElectionStatusListener listener = new VoterElectionEndListenerImpl(this);
        VoterElection model = new VoterElection(listener);
        VoterViewToControlIF control = new VoterControl(this, model);
        adapter = new VoterAdapter(control, model);

        initComponents();

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
        pnlLogo = new JPanel();
        pnlLogo.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    }

    private void buildLayout() {
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
     * Shows the authentication panel.
     */
    public void showAuthentication() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        currentPanel = new VoterAuthentication(adapter);
        buildLayout();
        lblTitle.setText(lang.getString("AuthenticationTitle"));
    }

    @Override
    public void showWait() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        currentPanel = new VoterWait(adapter);
        buildLayout();
        lblTitle.setText(lang.getString("WaitTitle"));
    }

    @Override
    public void showChoice() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        currentPanel = new VoterChoice(adapter);
        buildLayout();
        lblTitle.setText(lang.getString("ChoiceTitle"));
    }

    @Override
    public void showResults() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        currentPanel = new VoterResult(adapter);
        buildLayout();
        lblTitle.setText(lang.getString("ResultTitle"));
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public String getVote() {
        return null;
    }


    @Override
    public String getAuthenticationPath() {
        return null;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showWarning(String message) {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void electionOver() {

    }
}
