package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.PieChart;
import edu.kit.iti.formal.pse2018.evote.view.components.StackedBarChart;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorAdapter;

import java.awt.Color;
import java.awt.LayoutManager2;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * FinishPanel displays all the data entered in the ConfigPanels before.
 */
public class FinishPanel extends ConfigPanel {

    private SupervisorAdapter adapter;

    private ExtendableList candidates;
    private ExtendableList voters;
    private JTextArea txaDescription;
    private JLabel lblTimeFrame;
    private JLabel lblDescription;
    private JLabel lblCandidates;


    public FinishPanel(JPanel container, ConfigGUI gui, VerticalTabs vt, SupervisorAdapter adapter) {
        super(container, gui, vt, adapter);
    }

    @Override
    protected void initComponents() {
        candidates = new ExtendableList(null);

        lblCandidates = new JLabel("Kandidat");
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout gl = new GroupLayout(container);

        gl.setHorizontalGroup(gl.createParallelGroup()
            .addComponent(chart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(chart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        return gl;
    }
}
