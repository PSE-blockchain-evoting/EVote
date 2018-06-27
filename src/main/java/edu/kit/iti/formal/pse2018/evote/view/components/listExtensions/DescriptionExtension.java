package edu.kit.iti.formal.pse2018.evote.view.components.listExtensions;

import com.evoting.DescriptionDialog;
import edu.kit.iti.formal.pse2018.evote.view.components.ListExtension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class DescriptionExtension extends ComponentExtension<JButton>{

    private LinkedList<DescriptionDialog> dds;
    private JFrame parent;

    public DescriptionExtension(ListExtension next, JFrame parent) {
        super(next);
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
        dds.add(dd);

        ResourceBundle lang = ResourceBundle.getBundle("res/AdminConfig");
        JButton btnNew = new JButton(lang.getString("btnDescriptionText"));
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
}
