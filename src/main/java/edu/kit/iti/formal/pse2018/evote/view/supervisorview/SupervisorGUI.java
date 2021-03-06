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

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol.SupervisorControl;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.statemanagement.SupervisorElection;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;
import edu.kit.iti.formal.pse2018.evote.view.components.ImagePanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * This class creates the Main GUI for the supervisor.
 */
public class SupervisorGUI extends JFrame implements SupervisorControlToViewIF {

    private SupervisorAdapter adapter;

    private SupervisorVSComponentManager componentManager;

    private ConfigGUI config;

    private JLabel lblTitle;

    private JPanel pnlLogo;
    private SupervisorGUIPanel currentPanel;

    private GroupLayout layout;

    private int titleBarHeight = 100;
    private int titleBarHeightMin = 50;
    private int logoWidth = 150;
    private int logoWidthMin = 50;

    /**
     * Creates a new instance of SupervisorGUI.
     */
    public SupervisorGUI() {
        ElectionStatusListener listener = new SupervisorElectionEndListenerImpl(this);
        SupervisorElection model = null;
        try {
            model = new SupervisorElection(listener);
        } catch (NetworkException | NetworkConfigException e) {
            e.printStackTrace();
            System.exit(1);
        }
        SupervisorViewToControlIF control = new SupervisorControl(this, model);
        adapter = new SupervisorAdapter(control, model);

        showAuthentication();

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    private void initComponents() {
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont((Font) UIManager.get("Title.font"));

        pnlLogo = new JPanel();
        try {
            pnlLogo = new ImagePanel(ImageIO.read(getClass().getResourceAsStream("/logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
            pnlLogo.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }

        layout = new GroupLayout(this.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(pnlLogo, titleBarHeightMin, titleBarHeight, titleBarHeight)
                        .addComponent(lblTitle, titleBarHeightMin, titleBarHeight, titleBarHeight)
                )
                .addComponent(currentPanel)
        );

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlLogo, logoWidthMin, logoWidth, logoWidth)
                        .addComponent(lblTitle, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
                .addComponent(currentPanel)
        );
        this.getContentPane().setLayout(layout);
    }

    /**
     * Shows the Authentication Panel in the SupervisorGUI.
     */
    public void showAuthentication() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");

        currentPanel = new SupervisorAuthentication(adapter);
        initComponents();
        lblTitle.setText(lang.getString("supervisorAuthenticationTitle"));
    }

    @Override
    public void showFrontpage() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");

        currentPanel = new SupervisorFrontpage(adapter);
        initComponents();
        lblTitle.setText(lang.getString("supervisorFrontpageTitle"));
    }

    @Override
    public void showResults() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ElectionDataIF data = adapter.getElectionData();
        VotingSystemType vs = data.getVotingSystem();
        componentManager = SupervisorVSComponentManagerBuilder.generateComponentManager(vs, adapter);

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        SupervisorGUIPanel p = new SupervisorResult(adapter, componentManager);

        currentPanel = p;
        initComponents();
        lblTitle.setText(data.getName());
        currentPanel.updateResults(adapter.getResults(), adapter.getWinner());
    }

    @Override
    public void startConfigMenu() {
        if (config == null) {
            config = new ConfigGUI(adapter);
            config.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            config.setSize(1200, 800);
        }
        config.setVisible(true);
    }

    @Override
    public void loadConfigData() {
        if (config == null) {
            config = new ConfigGUI(adapter);
            config.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            config.setSize(1200, 800);
        }
        config.loadConfigData();
    }

    @Override
    public String[] getVoters() {
        return config.getVoters();
    }

    @Override
    public ElectionDataIF getElectionData() {
        return config.getElectionData();
    }

    @Override
    public String getImportPath() {
        return currentPanel.getImportPath();
    }

    @Override
    public String getExportPath() {
        return config.getExportPath();
    }

    @Override
    public void showConfigIssues() {
        config.showConfigIssues();
    }

    @Override
    public String getPassword() {
        return currentPanel.getPassword();
    }

    @Override
    public String getUsername() {
        return currentPanel.getUsername();
    }

    @Override
    public void updateResult() {
        currentPanel.updateResults(adapter.getResults(), adapter.getWinner());
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public String getAuthenticationPath() {
        return currentPanel.getCertPath();
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void electionOver() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        showSuccess(lang.getString("electionOver"));
        showResults();
    }
}
