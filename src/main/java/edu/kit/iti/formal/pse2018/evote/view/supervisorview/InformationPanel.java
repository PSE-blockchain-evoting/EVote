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

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class InformationPanel extends JPanel {
    private JLabel lblName;
    private JLabel lblVotingSystem;
    private JLabel lblTimeSpan;
    private JLabel lblDescription;
    private JTextArea txaDescription;

    private GroupLayout layout;

    /**
     * Creates an instance of Informationpanel.
     *
     * @param data the data that is shown.
     */
    public InformationPanel(ElectionDataIF data) {
        initComponents(data);
        buildLayout();
    }

    private void initComponents(ElectionDataIF data) {
        Font f = (Font) UIManager.get("General.font");
        ResourceBundle lang = ResourceBundle.getBundle("View");
        lblName = new JLabel(lang.getString("lblNameText") + ": " + data.getName());
        lblName.setFont(f);
        lblVotingSystem = new JLabel(lang.getString("lblVotingSystemText") + ": "
                + lang.getString(data.getVotingSystem().name()));
        lblVotingSystem.setFont(f);

        SimpleDateFormat spf = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        String startString = spf.format(data.getStartDate());
        String endString = spf.format(data.getEndDate());
        lblTimeSpan = new JLabel(lang.getString("lblTimespanPrefix") + ": "
                + startString + " " + lang.getString("lblTimespanMiddle")
                + " " + endString);
        lblTimeSpan.setFont(f);

        lblDescription = new JLabel(lang.getString("lblDescriptionText") + ":");
        lblDescription.setFont(f);
        txaDescription = new JTextArea(data.getDescription());
        txaDescription.setFont(f);
        txaDescription.setEditable(false);
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(lblName)
                .addComponent(lblVotingSystem)
                .addComponent(lblTimeSpan)
                .addComponent(lblDescription)
                .addComponent(txaDescription, 0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblName)
                .addComponent(lblVotingSystem)
                .addComponent(lblTimeSpan)
                .addComponent(lblDescription)
                .addComponent(txaDescription)
        );
        this.setLayout(layout);
    }
}
