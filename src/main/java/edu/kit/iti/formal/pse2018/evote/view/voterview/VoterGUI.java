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

package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.control.VoterViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.control.votercontrol.VoterControl;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.statemanagement.VoterElection;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;
import edu.kit.iti.formal.pse2018.evote.view.components.ImagePanel;

import java.awt.Color;
import java.awt.Component;
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
 * VoterGUI is the user interface for the Voter in an election.
 */
public class VoterGUI extends JFrame implements VoterControlToViewIF {

    private VoterAdapter adapter;

    private VoterVSComponentManager componentManager;

    private JLabel lblTitle;

    private JPanel pnlLogo;
    private VoterGUIPanel currentPanel;

    private GroupLayout layout;

    private int titleBarHeight = 125;
    private int titleBarHeightMin = 50;
    private int logoWidth = 150;
    private int logoWidthMin = 50;

    /**
     * Creates an instance of VoterGUI.
     */
    public VoterGUI() {
        ElectionStatusListener listener = new VoterElectionEndListenerImpl(this);
        VoterElection model = null;
        try {
            model = new VoterElection(listener);
        } catch (NetworkException | NetworkConfigException e) {
            e.printStackTrace();
            System.exit(1);
        }
        VoterViewToControlIF control = new VoterControl(this, model);
        adapter = new VoterAdapter(control, model);

        initComponents();

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
        try {
            pnlLogo = new ImagePanel(ImageIO.read(getClass().getResourceAsStream("/logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
            pnlLogo = new JPanel();
            pnlLogo.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
    }

    private void buildLayout() {
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
     * Shows the authentication panel.
     */
    public void showAuthentication() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        currentPanel = new VoterAuthentication(adapter);
        buildLayout();
        lblTitle.setText(lang.getString("AuthenticationTitle"));
    }

    @Override
    public void showWait() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ElectionDataIF data = adapter.getElectionData();
        componentManager = VoterVSComponentManagerBuilder.generateComponentManager(data.getVotingSystem(), adapter);
        componentManager.enableColors(false);
        currentPanel = new VoterWait(adapter, componentManager);
        buildLayout();
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        lblTitle.setText(lang.getString("WaitTitle"));
    }

    @Override
    public void showChoice() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        ElectionDataIF data = adapter.getElectionData();
        componentManager = VoterVSComponentManagerBuilder.generateComponentManager(data.getVotingSystem(), adapter);
        componentManager.enableColors(false);
        currentPanel = new VoterChoice(adapter, componentManager);
        buildLayout();
        lblTitle.setText(data.getName());
    }

    @Override
    public void showResults() {
        for (Component c : this.getContentPane().getComponents()) {
            this.getContentPane().remove(c);
        }

        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        componentManager = VoterVSComponentManagerBuilder.generateComponentManager(
                adapter.getElectionData().getVotingSystem(), adapter);
        componentManager.enableColors(true);
        currentPanel = new VoterResult(adapter, componentManager);
        buildLayout();
        lblTitle.setText(adapter.getElectionData().getName());
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public String getVote() {
        return currentPanel.getVote();
    }


    @Override
    public String getAuthenticationPath() {
        return currentPanel.getAuthenticationPath();
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
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        showSuccess(lang.getString("electionOver"));
        showResults();
    }
}
