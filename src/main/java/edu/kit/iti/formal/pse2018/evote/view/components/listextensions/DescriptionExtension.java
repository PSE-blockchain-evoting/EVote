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

package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import edu.kit.iti.formal.pse2018.evote.view.components.DescriptionDialog;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * DescriptionExtension adds a Button which will open a DescriptionDialog,
 * where a description can be added for that row.
 */
public class DescriptionExtension extends ComponentExtension<JButton> {

    private LinkedList<DescriptionDialog> dds;
    private JFrame parent;
    private String tableButtonText = "";
    private boolean editable = true;

    /**
     * Creates a DescriptionExtension.
     *
     * @param next   The next ListExtension in the chain.
     * @param parent The JFrame parent for the used JDialog Description Windows.
     */
    public DescriptionExtension(ListExtension next, Font font, JFrame parent) {
        super(next, font);
        this.parent = parent;
        dds = new LinkedList<>();
    }

    @Override
    protected void removeData(int i) {
        super.removeData(i);
        dds.remove(i);
    }

    @Override
    protected JButton createNewType() {
        DescriptionDialog dd = new DescriptionDialog(parent);
        dd.setEditable(editable);
        dds.add(dd);

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");
        JButton btnNew = new JButton(tableButtonText);
        btnNew.setFont(font);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dd.setVisible(true);
            }
        });

        dd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnNew.setToolTipText(dd.getDescription());
            }
        });

        return btnNew;
    }

    /**
     * Set the description of an Entry.
     *
     * @param i    The index of the Entry.
     * @param desc The new description-
     */
    public void setDescription(int i, String desc) {
        while (i >= components.size()) {
            list.addNewEntry();
        }
        dds.get(i).setDescription(desc);
    }

    /**
     * Access the Description of an Entry.
     *
     * @param i the index of the Entry.
     * @return The description of the Entry.
     */
    public String getDescription(int i) {
        return dds.get(i).getDescription();
    }


    /**
     * Allow editing of the Descriptions.
     *
     * @param b enable/disable.
     */
    public void setEditable(boolean b) {
        editable = b;
        for (DescriptionDialog dd : dds) {
            dd.setEditable(b);
        }
    }

    /**
     * Allows editing of the button Text in the Table.
     *
     * @param s The new String of the buttons.
     */
    public void setButtonText(String s) {
        tableButtonText = s;
        for (JButton btn : components) {
            btn.setText(s);
        }
    }
}
