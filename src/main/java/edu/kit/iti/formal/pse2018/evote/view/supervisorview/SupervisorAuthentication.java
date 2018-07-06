package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 * SupervisorAuthentication is the panel that is shown in the beginning.
 * It presents a form for entering a certificate.
 */
public class SupervisorAuthentication extends SupervisorGUIPanel {

    private JTextField txfPath;
    private JButton btnSearch;
    private JButton btnConfirm;

    private GroupLayout layout;

    /**
     * Creates an instance of SupervisorAuthentication.
     * @param adapter The Adapter to the Control and Model interfaces
     */
    public SupervisorAuthentication(SupervisorAdapter adapter) {
        super(adapter);
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        txfPath = new JTextField();
        btnSearch = new JButton(lang.getString("btnSearchCertText"));
        btnConfirm = new JButton(lang.getString("btnConfirmCertText"));
    }

    private void buildLayout() {
        layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txfPath)
                .addComponent(btnSearch)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
            .addGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirm)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup()
                        .addComponent(txfPath, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch)
                )
                .addGap(10, 100, 100)
                .addComponent(btnConfirm)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        this.setLayout(layout);
    }

    public String getImportPath() {
        return null;
    }
}
