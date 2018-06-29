package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.PieChart;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;

import java.awt.LayoutManager2;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * FinishPanel displays all the data entered in the ConfigPanels before.
 */
public class FinishPanel extends ConfigPanel {

    ExtendableList candidates;
    ExtendableList voters;
    JTextArea txaDescription;
    JLabel lblTimeFrame;
    JLabel lblDescription;
    JLabel lblCandidates;

    PieChart chart;

    public FinishPanel(JPanel container, VerticalTabs vt) {
        super(container, vt);
    }

    @Override
    protected void initComponents() {
        chart = new PieChart();
        chart.setData(PieChart.TEST_DATA);
        chart.setColors(PieChart.TEST_COLOR);
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
