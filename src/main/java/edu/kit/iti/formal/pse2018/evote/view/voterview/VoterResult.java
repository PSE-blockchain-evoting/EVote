package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.InformationPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

public class VoterResult extends VoterGUIPanel {

    private VoterVSComponentManager manager;

    private Diagram chart;
    private ExtendableList form;

    private JButton btnExit;
    private JToggleButton btnInfos;
    private InformationPanel pnlInfos;
    private JScrollPane spInfos;
    private JLabel lblWinner;

    private GroupLayout layout;

    /**
     * Creates an instance of VoterResult.
     *
     * @param adapter The context of the UI.
     * @param manager The componentManager for the VotingSystem.
     */
    public VoterResult(VoterAdapter adapter, VoterVSComponentManager manager) {
        super(adapter);
        this.manager = manager;

        chart = manager.createResultDiagram();
        String vote = adapter.getOwnVote();
        if (vote != null) {
            manager.setVote(adapter.getOwnVote());
        }
        manager.setEditable(false);
        form = manager.createVotingForm();

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        Font f = (Font) UIManager.get("general.font");
        btnExit = new JButton(lang.getString("btnExitText"));
        btnExit.setFont(f);
        btnExit.addActionListener(adapter.getLogoutListener());

        pnlInfos = new InformationPanel(adapter.getElectionData());
        spInfos = new JScrollPane(pnlInfos);

        btnInfos = new JToggleButton(lang.getString("btnInfoText"));
        btnInfos.setFont(f);
        btnInfos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (btnInfos.isSelected()) {
                    layout.replace(chart, spInfos);
                } else {
                    layout.replace(spInfos, chart);
                }
            }
        });

        lblWinner = new JLabel(lang.getString("lblWinnerText") + ": " + adapter.getWinner());
        lblWinner.setFont((Font) UIManager.get("Vote.font"));
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblWinner)
                                .addComponent(form)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnInfos)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                )
                                .addComponent(chart, 0, 0, Short.MAX_VALUE)
                        )
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                                .addComponent(lblWinner)
                                .addComponent(form)
                                .addGap(0,0,Short.MAX_VALUE)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInfos)
                                .addComponent(chart)
                        )
                )
                .addComponent(btnExit)
        );

        //layout.setHonorsVisibility(true);
        this.setLayout(layout);
    }
}
