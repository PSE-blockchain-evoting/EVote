package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.NumberedExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextExtension;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorAdapter;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * FinishPanel displays all the data entered in the ConfigPanels before.
 */
public class FinishPanel extends ConfigPanel {

    private JLabel lblTimeFrame;
    private JLabel lblDescription;
    private JScrollPane spDescription;
    private JTextArea txaDescription;
    private JLabel lblVotingSystem;
    private JLabel lblCandidates;
    private TextExtension teCandidate;
    private ExtendableList candidates;
    private JLabel lblVoters;
    private ExtendableList voters;
    private TextExtension teVoters;

    private JFileChooser jfcExport;

    private JLabel lblIssues;

    private JButton btnExport;

    public FinishPanel(JPanel container, ConfigGUI gui, VerticalTabs vt, SupervisorAdapter adapter) {
        super(container, gui, vt, adapter);
    }

    @Override
    protected void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");

        lblTimeFrame = new JLabel();
        lblVotingSystem = new JLabel(lang.getString("lblVotingSystemPrefix") + ":" + "hardcoded");

        Font f = (Font) UIManager.get("General.font");
        lblCandidates = new JLabel(lang.getString("lblCandidateText") + ":");
        teCandidate = new TextExtension(null, f, null);
        NumberedExtension n = new NumberedExtension(teCandidate, f);
        candidates = new ExtendableList(n);
        n.setList(candidates);

        lblVoters = new JLabel(lang.getString("lblVoterText") + ":");
        teVoters = new TextExtension(null, f, null);
        n = new NumberedExtension(teVoters, f);
        voters = new ExtendableList(n);
        n.setList(voters);

        lblDescription = new JLabel(lang.getString("lblDescriptionText") + ":");
        txaDescription = new JTextArea();
        txaDescription.setEditable(false);
        txaDescription.setLineWrap(true);
        spDescription = new JScrollPane(txaDescription);

        lblIssues = new JLabel("<html>Here are all the issues <p> Issue1 </p></html>");
        lblIssues.setForeground(Color.RED);
        lblIssues.setVerticalAlignment(SwingConstants.NORTH);

        btnExport = new JButton(lang.getString("btnExportText"));
        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jfcExport.showOpenDialog(null);
                adapter.getExportConfigListener().actionPerformed(actionEvent);
            }
        });
        btnContinue.setText(lang.getString("btnConfirmConfigText"));
        for (ActionListener l : btnContinue.getActionListeners()) {
            btnContinue.removeActionListener(l);
        }
        btnContinue.addActionListener(adapter.getStartElectionListener());

        jfcExport = new JFileChooser();

        lblTimeFrame.setFont(f);
        lblVotingSystem.setFont(f);
        lblCandidates.setFont(f);
        lblVoters.setFont(f);
        lblVotingSystem.setFont(f);
        lblDescription.setFont(f);
        txaDescription.setFont(f);
        lblIssues.setFont(f);
        btnExport.setFont(f);
        btnContinue.setFont(f);
        jfcExport.setFont(f);
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout gl = new GroupLayout(container);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(lblTimeFrame)
                        .addComponent(lblDescription)
                        .addComponent(spDescription, 0, 0, Short.MAX_VALUE)
                        .addComponent(lblCandidates)
                        .addComponent(candidates, 0, 0, Short.MAX_VALUE)
                        .addComponent(lblVoters)
                        .addComponent(voters, 0, 0, Short.MAX_VALUE)
                )
                .addGroup(gl.createParallelGroup()
                        .addComponent(lblVotingSystem)
                        .addComponent(lblIssues)
                        .addGroup(gl.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnExport)
                        )
                )

        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addComponent(lblTimeFrame)
                        .addGap(15)
                        .addComponent(lblDescription)
                        .addComponent(spDescription, 0, 0, Short.MAX_VALUE)
                        .addGap(15)
                        .addComponent(lblCandidates)
                        .addComponent(candidates, 0, 0, Short.MAX_VALUE)
                        .addGap(15)
                        .addComponent(lblVoters)
                        .addComponent(voters, 0, 0, Short.MAX_VALUE)
                )
                .addGroup(gl.createSequentialGroup()
                        .addComponent(lblVotingSystem)
                        .addGap(10, 50, 100)
                        .addComponent(lblIssues, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExport)
                )
        );
        return gl;
    }

    private void updateData() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");
        ResourceBundle viewLang = ResourceBundle.getBundle("View");

        ElectionDataIF data = gui.getElectionData();
        Date startDate = data.getStartDate();
        Date endDate = data.getEndDate();
        String start = startDate != null ? startDate.toString() : "________";
        String end = endDate != null ? endDate.toString() : "________";

        lblTimeFrame.setText(lang.getString("lblTimespanPrefix") + start
                + lang.getString("lblTimespanMiddle") + " " + end);
        lblVotingSystem.setText(lang.getString("lblVotingSystemPrefix") + " "
                + viewLang.getString(data.getVotingSystem().name()));
        lblCandidates.setText(lang.getString("lblCandidateText"));

        lblVoters.setText(lang.getString("lblVoterText"));
        lblDescription.setText(lang.getString("lblDescriptionText"));
        txaDescription.setText(data.getDescription());

        txaDescription.setText(data.getDescription());

        String[] cand = data.getCandidates();
        for (int i = 0; i < cand.length; i++) {
            teCandidate.setText(i, cand[i]);
        }

        String[] vot = gui.getVoters();
        for (int i = 0; i < vot.length; i++) {
            teVoters.setText(i, vot[i]);
        }
    }

    @Override
    public void onActive() {
        updateData();
        adapter.getConfirmedConfigListener().actionPerformed(null);
    }

    public String getExportPath() {
        return jfcExport.getSelectedFile().getAbsolutePath();
    }

    public void setConfigIssuesText(String text) {
        lblIssues.setText(text);
    }
}
