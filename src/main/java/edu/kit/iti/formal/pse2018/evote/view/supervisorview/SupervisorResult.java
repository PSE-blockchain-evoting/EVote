package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;

public class SupervisorResult extends SupervisorGUIPanel {

    private JLabel lblWinner;
    private Diagram chart;
    private ExtendableList table;

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
        lblWinner = new JLabel("");

        initComponents();
        buildLayout();
    }

    private void initComponents() {

    }

    private void buildLayout() {
        layout = new GroupLayout(this);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(table)
                .addGroup(layout.createParallelGroup()
                        .addComponent(chart)
                        .addComponent(lblWinner)
                )
        );

        layout.setVerticalGroup(layout.createParallelGroup()
                .addComponent(table)
                .addGroup(layout.createSequentialGroup()
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
