package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;

import java.awt.LayoutManager2;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class TimespanPanel extends ConfigPanel {

    private JLabel lblStart;
    private JLabel lblEnd;
    private JButton btnImmediately;
    private JTextField txfStartTime;
    private JTextField txfEndTime;
    private JTextField txfStartDate;
    private JTextField txfEndDate;
    private JComboBox<String> cbxExtraCond;

    public TimespanPanel(JPanel container, VerticalTabs vt) {
        super(container, vt);
    }

    @Override
    protected void initComponents() {

        ResourceBundle lang = ResourceBundle.getBundle("res/AdminConfig");

        lblStart = new JLabel(lang.getString("lblStartText"));
        lblEnd = new JLabel(lang.getString("lblEndText"));
        btnImmediately = new JButton(lang.getString("btnImmediateText"));

        txfStartTime = new JTextField();
        txfEndTime = new JTextField();
        txfStartDate = new JTextField();
        txfEndDate = new JTextField();

        cbxExtraCond = new JComboBox<>();
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout gl = new GroupLayout(container);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                    .addGroup(gl.createParallelGroup()
                        .addGroup(gl.createSequentialGroup()
                            .addComponent(lblStart)
                            .addComponent(txfStartDate)
                            .addComponent(txfStartTime)
                            .addComponent(btnImmediately)
                        )
                        .addGroup(gl.createSequentialGroup()
                            .addComponent(lblEnd)
                            .addComponent(txfEndDate)
                            .addComponent(txfEndTime)
                        )
                        .addComponent(cbxExtraCond)
                    )
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)

        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                    .addGroup(gl.createParallelGroup()
                            .addComponent(lblStart)
                            .addComponent(txfStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfStartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImmediately, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGroup(gl.createParallelGroup()
                            .addComponent(lblEnd)
                            .addComponent(txfEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfEndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                    .addComponent(cbxExtraCond)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );


        return gl;
    }
}
