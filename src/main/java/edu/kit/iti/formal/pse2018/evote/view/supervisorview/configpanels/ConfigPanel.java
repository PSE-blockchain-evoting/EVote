package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ActiveListener;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorAdapter;

import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * ConfigPanel is the generalization of the different ConfigPanel required for configuring an election.
 * It is meant to be used in combination with VerticalTabs.
 */
public abstract class ConfigPanel extends JPanel implements ActiveListener {

    protected SupervisorAdapter adapter;
    protected ConfigGUI gui;

    protected VerticalTabs vt;

    protected JPanel pnlMain;
    protected JPanel pnlButtons;
    protected JButton btnCancel;
    protected JButton btnContinue;

    protected LayoutManager2 layout;

    /**
     * Creates an instance of ConfigPanel.
     *
     * @param container The Container in which to place the components of this ConfigPanel
     * @param vt        The VerticalTabs in which this Panel in placed.
     */
    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    public ConfigPanel(JPanel container, ConfigGUI gui, VerticalTabs vt, SupervisorAdapter adapter) {
        this.gui = gui;
        this.adapter = adapter;
        this.vt = vt;

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");
        btnCancel = new JButton(lang.getString("btnCancelText"));
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gui.setVisible(false);
            }
        });
        btnContinue = new JButton(lang.getString("btnContinueText"));
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vt.nextTab();
            }
        });

        pnlMain = new JPanel();
        pnlButtons = new JPanel();
        pnlButtons.add(btnCancel);
        pnlButtons.add(btnContinue);

        GroupLayout containerLayout = new GroupLayout(container);

        containerLayout.setHorizontalGroup(
                containerLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addGroup(containerLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
        );

        containerLayout.setVerticalGroup(
                containerLayout.createSequentialGroup()
                        .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        container.setLayout(containerLayout);


        initComponents();

        layout = buildLayout(pnlMain);
        pnlMain.setLayout(layout);

    }

    @Override
    public void onActive(){

    }

    @Override
    public void onDeactivate(){

    }

    protected abstract void initComponents();

    protected abstract LayoutManager2 buildLayout(JPanel container);
}
