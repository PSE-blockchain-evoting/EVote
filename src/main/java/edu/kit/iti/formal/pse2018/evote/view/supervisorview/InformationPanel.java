package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class InformationPanel extends JPanel {
    private JLabel lblName;
    private JLabel lblVotingSystem;
    private JLabel lblTimeSpan;
    private JLabel lblDescription;
    private JTextArea txaDescription;

    private GroupLayout layout;

    /**
     * Creates an instance of Informationpanel.
     *
     * @param data the data that is shown.
     */
    public InformationPanel(ElectionDataIF data) {
        initComponents(data);
        buildLayout();
    }

    private void initComponents(ElectionDataIF data) {
        Font f = (Font) UIManager.get("General.font");
        ResourceBundle lang = ResourceBundle.getBundle("View");
        lblName = new JLabel(lang.getString("lblNameText") + ": " + data.getName());
        lblName.setFont(f);
        lblVotingSystem = new JLabel(lang.getString("lblVotingSystemText") + ": "
                + lang.getString(data.getVotingSystem().name()));
        lblVotingSystem.setFont(f);

        SimpleDateFormat spf = new SimpleDateFormat("dd.MM.yyyy, hh:mm");
        String startString = spf.format(data.getStartDate());
        String endString = spf.format(data.getEndDate());
        lblTimeSpan = new JLabel(lang.getString("lblTimespanPrefix") + ": "
                + startString + " " + lang.getString("lblTimespanMiddle")
                + " " + endString);
        lblTimeSpan.setFont(f);

        lblDescription = new JLabel(lang.getString("lblDescriptionText") + ":");
        lblDescription.setFont(f);
        txaDescription = new JTextArea(data.getDescription());
        txaDescription.setFont(f);
        txaDescription.setEditable(false);
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(lblName)
                .addComponent(lblVotingSystem)
                .addComponent(lblTimeSpan)
                .addComponent(lblDescription)
                .addComponent(txaDescription, 0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblName)
                .addComponent(lblVotingSystem)
                .addComponent(lblTimeSpan)
                .addComponent(lblDescription)
                .addComponent(txaDescription)
        );
        this.setLayout(layout);
    }
}
