package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;

public class SupervisorResult extends SupervisorGUIPanel {

    private JLabel lblWinner;
    private ExtendableList table;

    private JToggleButton btnInfo;
    private JScrollPane spInfo;
    private InformationPanel pnlInfo;
    private Diagram chart;

    private GroupLayout layout;

    private SupervisorVSComponentManager manager;

    /**
     * Creates an instance of SupervisorResult.
     *
     * @param adapter The Adapter to the Control and Model interfaces.
     * @param manager The ComponentManager for creating the correct diagram and table.
     */
    public SupervisorResult(SupervisorAdapter adapter, SupervisorVSComponentManager manager) {
        super(adapter);
        this.manager = manager;
        chart = manager.createResultDiagram();
        table = manager.createResultTable();
        table.setBorder(null);
        lblWinner = new JLabel("");

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle viewLang = ResourceBundle.getBundle("View");
        btnInfo = new JToggleButton(viewLang.getString("btnInfoText"));
        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (btnInfo.isSelected()) {
                    layout.replace(chart, spInfo);
                } else {
                    layout.replace(spInfo, chart);
                }
            }
        });
        pnlInfo = new InformationPanel(adapter.getElectionData());
        spInfo = new JScrollPane(pnlInfo);
    }

    private void buildLayout() {
        layout = new GroupLayout(this);

        //layout.setAutoCreateContainerGaps(true);
        //layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        //.addGap(10, 10, 100)
                        .addComponent(table)
                        //.addGap(10, 10, 100)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, 10)
                )
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                                .addComponent(btnInfo)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        )
                        .addComponent(chart, 0, 0, Short.MAX_VALUE)
                        .addComponent(lblWinner)
                )
        );

        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        .addComponent(table)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInfo)
                        .addComponent(chart)
                        .addComponent(lblWinner)
                )
        );

        this.setLayout(layout);
    }

    @Override
    public void updateResults(int[] results, String winner) {
        manager.updateComponents(results);
    }
}
