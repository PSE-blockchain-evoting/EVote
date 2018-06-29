package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.CandidateList;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;

import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * CandidatePanel lets the user configure the Candidates with their respective Description.
 */
public class CandidatePanel extends ConfigPanel {

    private CandidateList candidates;
    private JButton btnAdd;

    public CandidatePanel(JPanel container, VerticalTabs vt) {
        super(container, vt);
    }

    @Override
    protected void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("res/AdminConfig");

        JFrame parent = (JFrame)getTopLevelAncestor();
        candidates = CandidateList.createCandidateList(parent);
        candidates.addNewEntry();

        btnAdd = new JButton(lang.getString("btnCandidateAddText"));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                candidates.addNewEntry();
            }
        });
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout gl = new GroupLayout(container);

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                    .addGroup(gl.createParallelGroup()
                        .addComponent(candidates, 0, 1, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                    )
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                    .addComponent(candidates, 0, 1, Short.MAX_VALUE)
                    .addComponent(btnAdd)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );
        return gl;
    }
}
