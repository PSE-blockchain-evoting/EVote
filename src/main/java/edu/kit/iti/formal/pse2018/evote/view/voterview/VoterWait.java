package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import java.awt.Font;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

public class VoterWait extends VoterGUIPanel {

    private VoterVSComponentManager manager;

    private JButton btnExit;
    private ExtendableList form;
    private JLabel lblOwnVote;
    private JLabel lblElectionEnd;

    private GroupLayout layout;

    /**
     * Creates an instance of VoterWait.
     *
     * @param adapter the context of the UI.
     * @param manager The componentManager for this election.
     */
    public VoterWait(VoterAdapter adapter, VoterVSComponentManager manager) {
        super(adapter);
        this.manager = manager;

        manager.setVote(adapter.getOwnVote());
        form = manager.createVotingForm();
        manager.setEditable(false);

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        Font f = (Font) UIManager.get("General.font");
        btnExit = new JButton(lang.getString("btnExitText"));
        btnExit.setFont(f);
        btnExit.addActionListener(adapter.getLogoutListener());

        lblOwnVote = new JLabel(lang.getString("lblOwnVoteText"));
        lblOwnVote.setFont(f);

        Date end = adapter.getElectionData().getEndDate();
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.setTime(end);
        DateFormat dt = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        lblElectionEnd = new JLabel(lang.getString("lblElectionEndText")
                + " " + dt.format(c.getTime()));
        lblElectionEnd.setFont(f);
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblElectionEnd)
                                .addComponent(lblOwnVote)
                                .addComponent(form)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                .addComponent(lblElectionEnd)
                .addGap(0, 10, 10)
                .addComponent(lblOwnVote)
                .addComponent(form)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                .addComponent(btnExit)
        );

        this.setLayout(layout);
    }
}
