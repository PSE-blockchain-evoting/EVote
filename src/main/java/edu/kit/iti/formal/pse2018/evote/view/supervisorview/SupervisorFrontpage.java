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

package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle;

/**
 * SupervisorFrontpage is the panel that is shown to the supervisor
 * when he is authenticated. It gives the Supervisor the choice of
 * configuring a new Election or importing a already configured one.
 */
public class SupervisorFrontpage extends SupervisorGUIPanel {

    private JButton btnImport;
    private JFileChooser fcImportPath;
    private JButton btnNew;

    private GroupLayout layout;

    /**
     * Creates an instance of SupervisorFrontage.
     *
     * @param adapter the adapter to the controller and model interface
     */
    public SupervisorFrontpage(SupervisorAdapter adapter) {
        super(adapter);

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        fcImportPath = new JFileChooser();
        btnImport = new JButton(lang.getString("btnImportText"));
        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int res = fcImportPath.showOpenDialog(SupervisorFrontpage.this);
                if (res == JFileChooser.APPROVE_OPTION && fcImportPath.getSelectedFile().exists()) {
                    adapter.getImportConfigListener().actionPerformed(actionEvent);
                }
            }
        });
        btnNew = new JButton(lang.getString("btnNewText"));
        btnNew.addActionListener(adapter.getNewConfigListener());
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

    @Override
    public String getImportPath() {
        return fcImportPath.getSelectedFile().getAbsolutePath();
    }
}
