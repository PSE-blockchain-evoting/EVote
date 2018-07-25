package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * SupervisorAuthentication is the panel that is shown in the beginning.
 * It presents a form for entering a certificate.
 */
public class SupervisorAuthentication extends SupervisorGUIPanel {

    private JLabel lblPasswordAuth;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txfUsername;
    private JPasswordField pfPassword;
    private JButton btnConfirmPassword;

    private JLabel lblCertAuth;
    private JTextField txfPath;
    private JButton btnSearch;
    private JButton btnConfirmCert;
    private JFileChooser fcSearch;

    private GroupLayout layout;

    /**
     * Creates an instance of SupervisorAuthentication.
     *
     * @param adapter The Adapter to the Control and Model interfaces
     */
    public SupervisorAuthentication(SupervisorAdapter adapter) {
        super(adapter);
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");

        Font f = (Font) UIManager.get("General.font");
        lblPasswordAuth = new JLabel(lang.getString("lblPasswordAuthText") + ":");
        lblPasswordAuth.setFont(f);
        lblUsername = new JLabel(lang.getString("lblUsernameText") + ":");
        lblUsername.setFont(f);
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        txfUsername = new JTextField();
        txfUsername.setFont(f);
        lblPassword = new JLabel(lang.getString("lblPasswordText") + ":");
        lblPassword.setFont(f);
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        pfPassword = new JPasswordField();
        pfPassword.setFont(f);
        btnConfirmPassword = new JButton(lang.getString("btnConfirmPasswordText"));
        btnConfirmPassword.addActionListener(adapter.getFirstAuthenticationListener());
        btnConfirmPassword.setFont(f);

        lblCertAuth = new JLabel(lang.getString("lblCertAuthText") + ":");
        lblCertAuth.setFont(f);
        fcSearch = new JFileChooser();
        fcSearch.setFont(f);
        txfPath = new JTextField();
        txfPath.setFont(f);
        btnSearch = new JButton(lang.getString("btnSearchCertText"));
        btnSearch.setFont(f);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int ret = fcSearch.showOpenDialog(btnSearch);
                if (ret == JFileChooser.APPROVE_OPTION && fcSearch.getSelectedFile().exists()) {
                    String path = fcSearch.getSelectedFile().getPath();
                    txfPath.setText(path);
                }
            }
        });
        btnConfirmCert = new JButton(lang.getString("btnConfirmCertText"));
        btnConfirmCert.setFont(f);
        btnConfirmCert.addActionListener(adapter.getAuthenticationListener());
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblPasswordAuth)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblUsername)
                                        .addComponent(txfUsername)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPassword)
                                        .addComponent(pfPassword)
                                )
                                .addComponent(btnConfirmPassword)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblCertAuth)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txfPath)
                                        .addComponent(btnSearch)
                                )
                                .addComponent(btnConfirmCert)
                        )
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 10, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, 30)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPasswordAuth)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblUsername)
                                .addComponent(txfUsername)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblPassword)
                                .addComponent(pfPassword)
                        )
                        .addComponent(btnConfirmPassword)
                )
                .addGap(10, 30, 100)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCertAuth)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(txfPath)
                                .addComponent(btnSearch)

                        )
                        .addComponent(btnConfirmCert)
                )
        );

        layout.linkSize(lblUsername, lblPassword);
        layout.linkSize(SwingConstants.VERTICAL, lblPassword, lblUsername, txfUsername, pfPassword);
        layout.linkSize(SwingConstants.VERTICAL, btnSearch, txfPath);
        this.setLayout(layout);
    }

    @Override
    public String getCertPath() {
        return txfPath.getText();
    }

    @Override
    public String getUsername() {
        return txfUsername.getText();
    }

    @Override
    public String getPassword() {
        char[] ch = pfPassword.getPassword();
        return new String(ch);
    }
}
