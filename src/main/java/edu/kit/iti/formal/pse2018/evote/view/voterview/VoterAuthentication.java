/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
                if (res == JFileChooser.APPROVE_OPTION && fcSearch.getSelectedFile().exists()) {
                    String path = fcSearch.getSelectedFile().getAbsolutePath();
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
