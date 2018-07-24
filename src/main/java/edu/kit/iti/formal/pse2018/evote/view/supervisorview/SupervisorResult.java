package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

public class SupervisorResult extends SupervisorGUIPanel {

    private JLabel lblWinner;
    private ExtendableList table;

    private JToggleButton btnInfo;
    private JScrollPane spInfo;
    private InformationPanel pnlInfo;
    private Diagram chart;
    private JButton btnExit;
    private JButton btnEndElection;

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
        Font f = (Font) UIManager.get("General.font");
        ResourceBundle viewLang = ResourceBundle.getBundle("View");
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        btnInfo = new JToggleButton(viewLang.getString("btnInfoText"));
        btnInfo.setFont(f);
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

        btnExit = new JButton(lang.getString("btnExitText"));
        btnExit.setFont(f);
        btnExit.addActionListener(adapter.getLogoutListener());
        btnEndElection = new JButton(lang.getString("btnEndElectionText"));
        btnEndElection.setFont(f);
        btnEndElection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = JOptionPane.showConfirmDialog(SupervisorResult.this,
                        lang.getString("DestroyElectionWarning"),
                        lang.getString("DestroyElectionTitle"),
                        JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    adapter.getFinishElectionListener().actionPerformed(actionEvent);
                }
            }
        });
    }

    private void buildLayout() {
        layout = new GroupLayout(this);

        //layout.setAutoCreateContainerGaps(true);
        //layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(table)
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnInfo)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                )
                                .addComponent(chart, 0, 0, Short.MAX_VALUE)
                                .addComponent(lblWinner)
                        )
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEndElection)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                                .addComponent(table)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                                .addComponent(btnEndElection)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInfo)
                                .addComponent(chart)
                                .addComponent(lblWinner)
                        )
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(btnEndElection)
                        .addComponent(btnExit)
                )
        );

        this.setLayout(layout);
    }

    @Override
    public void updateResults(int[] results, String winner) {
        manager.updateComponents(results);
    }
}
