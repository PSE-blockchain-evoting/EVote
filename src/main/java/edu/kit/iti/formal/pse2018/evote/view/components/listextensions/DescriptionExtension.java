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
        dds.add(dd);

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");
        JButton btnNew = new JButton(lang.getString("btnDescriptionText"));
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
}
