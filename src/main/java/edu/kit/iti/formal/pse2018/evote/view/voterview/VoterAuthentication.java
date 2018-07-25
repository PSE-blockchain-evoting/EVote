package edu.kit.iti.formal.pse2018.evote.view.voterview;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class VoterAuthentication extends VoterGUIPanel {

    private JLabel lblCertAuth;
    private JTextField txfPath;
    private JButton btnSearch;
    private JButton btnConfirmCert;
    private JFileChooser fcSearch;
    private JButton btnExit;

    private GroupLayout layout;

    /**
     * Creates an instance of VoterAuthentication.
     *
     * @param adapter The context of the GUI.
     */
    public VoterAuthentication(VoterAdapter adapter) {
        super(adapter);
        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        Font f = (Font) UIManager.get("General.font");

        lblCertAuth = new JLabel(lang.getString("lblCertAuthText"));
        lblCertAuth.setFont(f);
        txfPath = new JTextField();
        txfPath.setFont(f);
        btnSearch = new JButton(lang.getString("btnSearchText"));
        btnSearch.setFont(f);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = fcSearch.showOpenDialog(VoterAuthentication.this);
                String path = fcSearch.getSelectedFile().getAbsolutePath();
                if (res == JFileChooser.APPROVE_OPTION) {
                    txfPath.setText(path);
                }
            }
        });
        btnConfirmCert = new JButton(lang.getString("btnConfirmCertText"));
        btnConfirmCert.addActionListener(adapter.getAuthenticationListener());
        btnConfirmCert.setFont(f);

        fcSearch = new JFileChooser();

        btnExit = new JButton(lang.getString("btnExitText"));
        btnExit.setFont(f);
        btnExit.addActionListener(adapter.getLogoutListener());
    }

    private void buildLayout() {
        layout = new GroupLayout(this);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblCertAuth)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txfPath)
                                        .addComponent(btnSearch)
                                )
                                .addComponent(btnConfirmCert)
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
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCertAuth)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(txfPath)
                                .addComponent(btnSearch)

                        )
                        .addComponent(btnConfirmCert)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                .addComponent(btnExit)
        );

        layout.linkSize(SwingConstants.VERTICAL, btnSearch, txfPath);

        this.setLayout(layout);
    }

    @Override
    public String getAuthenticationPath() {
        return txfPath.getText();
    }
}
