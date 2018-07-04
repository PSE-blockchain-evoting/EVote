package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorAdapter;

import java.awt.LayoutManager2;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 * GeneralConfigPanel lets the user enter general information about the election.
 * This includes Name, Description and Voting System.
 */
public class GeneralConfigPanel extends ConfigPanel {

    protected JLabel lblName;
    protected JLabel lblVotingSystem;
    protected JLabel lblDescription;
    protected JTextField txfName;
    protected JComboBox<String> cbxVotingSystem;
    protected JTextArea txaDescription;


    public GeneralConfigPanel(JPanel container, ConfigGUI gui, VerticalTabs vt, SupervisorAdapter adapter) {
        super(container, gui, vt, adapter);
    }

    protected void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");

        lblName = new JLabel(lang.getString("lblNameText") + ":");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVotingSystem = new JLabel(lang.getString("lblVotingSystemText") + ":");
        lblVotingSystem.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescription = new JLabel(lang.getString("lblDescriptionText") + ":");
        lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        txfName = new JTextField();
        cbxVotingSystem = new JComboBox<>();
        txaDescription = new JTextArea();
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout generalLayout = new GroupLayout(container);

        generalLayout.setAutoCreateContainerGaps(true);
        generalLayout.setAutoCreateGaps(true);

        generalLayout.setHorizontalGroup(
            generalLayout.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                    .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addGroup(generalLayout.createSequentialGroup()
                                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txfName, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            )
                    .addGroup(generalLayout.createSequentialGroup()
                            .addComponent(lblVotingSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxVotingSystem, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    )
                    .addGroup(generalLayout.createSequentialGroup()
                            .addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txaDescription, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    )
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 200)
         );

        generalLayout.setVerticalGroup(
                generalLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addGroup(generalLayout.createSequentialGroup()
                                .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblVotingSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxVotingSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txaDescription, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );

        generalLayout.linkSize(SwingConstants.HORIZONTAL, lblName, lblDescription, lblVotingSystem);
        return generalLayout;
    }
}
