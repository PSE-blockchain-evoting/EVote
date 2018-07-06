package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle;

/**
 * SupervisorFrontpage is the panel that is shown to the supervisor
 * when he is authenticated. It gives the Supervisor the choice of
 * configuring a new Election or importing a already configured one.
 */
public class SupervisorFrontpage extends SupervisorGUIPanel {

    private JButton btnImport;
    private JButton btnNew;

    private GroupLayout layout;

    /**
     * Creates an instance of SupervisorFrontage.
     * @param adapter the adapter to the controller and model interface
     */
    public SupervisorFrontpage(SupervisorAdapter adapter) {
        super(adapter);

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        btnImport = new JButton(lang.getString("btnImportText"));
        btnNew = new JButton(lang.getString("btnNewText"));

    }

    private void buildLayout() {
        layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
                .addComponent(btnNew)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
            )
            .addGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
                .addComponent(btnImport)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
                .addComponent(btnNew)
                .addGap(10, 100, 100)
                .addComponent(btnImport)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
        );

        this.setLayout(layout);
    }
}
