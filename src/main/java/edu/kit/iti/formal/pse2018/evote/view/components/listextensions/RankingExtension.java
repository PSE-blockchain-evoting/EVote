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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;

public class RankingExtension extends ComponentExtension<JComboBox<String>> {

    private int selections;
    private String noSelection = "-";
    private boolean editable = true;

    /**
     * ComponentExtension is a specialized ListExtension which automatically adds a Type Component
     * to Entries of the list and manages them.
     *
     * @param next The next ListExtension in the chain of ListExtensions. Can be null.
     * @param font The font to be used in the JComboBox'.
     */
    public RankingExtension(ListExtension next, Font font, int selections) {
        super(next, font);
        this.selections = selections;
    }

    @Override
    protected JComboBox createNewType() {
        JComboBox<String> cbx = new JComboBox<>();
        cbx.setMaximumSize(new Dimension(30, 1000));
        int[] notSelected = getNotSelectedRanks();
        Arrays.sort(notSelected);
        cbx.addItem(noSelection);
        for (int i : notSelected) {
            cbx.addItem(i + "");
        }
        cbx.setSelectedIndex(0);
        cbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateSelections();
            }
        });
        cbx.setFont(font);

        return cbx;
    }

    private void updateSelections() {
        for (JComboBox<String> cbx : components) {
            for (ActionListener al : cbx.getActionListeners()) {
                cbx.removeActionListener(al);
            }
        }

        int[] notSelected = getNotSelectedRanks();
        Arrays.sort(notSelected);

        for (JComboBox<String> cbx : components) {
            String s = (String) cbx.getSelectedItem();
            cbx.removeAllItems();

            if (!s.equals(noSelection)) {
                cbx.addItem(s);
            }

            cbx.addItem(noSelection);
            for (int i : notSelected) {
                cbx.addItem(i + "");
            }

            cbx.setSelectedIndex(0);
        }

        for (JComboBox<String> cbx : components) {
            cbx.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    updateSelections();
                }
            });
        }
    }

    private int[] getNotSelectedRanks() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < selections + 1; i++) {
            list.add(i);
        }

        for (JComboBox<String> cbx : components) {
            if (!cbx.getSelectedItem().equals(noSelection)) {
                int rm = Integer.parseInt((String) cbx.getSelectedItem());
                System.out.println("removing = " + rm);
                list.remove((Integer) rm);
            }
        }

        int[] l = new int[list.size()];
        for (int i = 0; i < l.length; i++) {
            l[i] = list.get(i);
        }
        return l;
    }

    /**
     * Enable or disable the JComboboxes.
     *
     * @param b Enable/Disable.
     */
    public void setEditable(boolean b) {
        for (JComboBox<String> cbx : components) {
            cbx.setEnabled(b);
        }
    }

    /**
     * Sets a Ranking in the Comboboxes.
     * A ranking is an int Array that has the rank for each selection.
     * If a row is not selected the ranking array contains 0 at that index.
     *
     * @param rank The ranks for each row.
     */
    public void setRanking(int[] rank) {
        assert (rank.length >= selections);

        for (int i = 0; i < selections; i++) {
            JComboBox<String> cbx = components.get(i);
            if (rank[i] == 0) {
                cbx.setSelectedItem(noSelection);
            } else {
                String s = rank[i] + "";
                cbx.addItem(s);
                cbx.setSelectedItem(s);
            }
        }
        updateSelections();
    }

    /**
     * Extracts the ranking in the Comboboxes.
     *
     * @return The ranking in form of the int array, described in setRanking javadoc.
     */
    public int[] getRanking() {
        int[] rank = new int[selections];

        for (int i = 0; i < selections; i++) {
            JComboBox<String> cbx = components.get(i);
            if (cbx.getSelectedItem().equals(noSelection)) {
                rank[i] = 0;
            } else {
                rank[i] = Integer.parseInt((String) cbx.getSelectedItem());
            }
        }

        return rank;
    }
}
