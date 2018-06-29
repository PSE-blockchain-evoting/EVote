package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

/**
 * DescriptionDialog is a JDialog which will allow a User to add a description about something.
 */
public class DescriptionDialog extends JDialog {

    private JLabel lblDescription;
    private JTextArea txaDescription;
    private JButton btnConfirm;
    private LinkedList<ActionListener> listener;

    /**
     * Creates a JDialog which will allow the User to input a Description about something.
     * @param parent The parent JFrame.
     */
    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    public DescriptionDialog(JFrame parent) {
        super(parent);

        ResourceBundle lang = ResourceBundle.getBundle("res/AdminConfig");

        this.setTitle(lang.getString("DescriptionTitle"));
        this.setModal(true);
        this.setSize(200,300);
        this.setLocationRelativeTo(null);

        listener = new LinkedList<>();

        lblDescription = new JLabel();
        txaDescription = new JTextArea();
        txaDescription.setColumns(10);
        txaDescription.setRows(1);
        txaDescription.setLineWrap(true);
        txaDescription.setWrapStyleWord(true);

        btnConfirm = new JButton(lang.getString("btnConfirmText"));
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                notifyListener(actionEvent);
            }
        });

        GroupLayout gl = new GroupLayout(this.getContentPane());
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lblDescription)
                                .addComponent(txaDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConfirm)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 10)
                        .addComponent(lblDescription)
                        .addComponent(txaDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirm)
        );
        this.getContentPane().setLayout(gl);
    }

    /**
     * Show or hide DescriptionDialog.
     * @param name The name of the description.
     * @param b If true is shows the DescriptionDialog, if false it hides it.
     */
    public void setVisible(String name, boolean b) {
        ResourceBundle lang = ResourceBundle.getBundle("res/AdminConfig");
        lblDescription.setText(lang.getString("lblDescriptionAboutCandidateText") + name + ":");
        super.setVisible(b);
    }

    public void addActionListener(ActionListener al) {
        listener.add(al);
    }

    public void removeActionListener(ActionListener al) {
        listener.remove(al);
    }

    private void notifyListener(ActionEvent ae) {
        for (ActionListener al : listener) {
            al.actionPerformed(ae);
        }
    }

    public String getDescription() {
        return txaDescription.getText();
    }
}
