package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.components.VoterList;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;

import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class VoterPanel extends ConfigPanel {

    private VoterList vl;
    private JButton btnAdd;

    public VoterPanel(JPanel container, ConfigGUI gui, VerticalTabs vt) {
        super(container, gui, vt);
    }

    @Override
    protected void initComponents() {
        vl = VoterList.createVoterList();
        vl.addNewEntry();

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");
        btnAdd = new JButton(lang.getString("btnVoterAddText"));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vl.addNewEntry();
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
                                .addComponent(vl, 0, 1, Short.MAX_VALUE)
                                .addComponent(btnAdd)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addComponent(vl, 0, 1, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );
        return gl;
    }
}
