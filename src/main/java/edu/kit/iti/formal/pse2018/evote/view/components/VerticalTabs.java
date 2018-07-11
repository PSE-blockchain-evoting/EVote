package edu.kit.iti.formal.pse2018.evote.view.components;

import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ActiveListener;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * VerticalTabs is a JComponent which provides a Tab menu. The Tabs are drawn on the right side. Each tab
 * has a corresponding JPanel which will be drawn if the respective Tab is pressed/selected.
 */
public class VerticalTabs extends JPanel {

    private GroupLayout mainLayout;

    private JPanel nav;                //Navigation side bar. Contains all tabs
    private GroupLayout navLayout;
    private int navWidth = 150;
    private int navWidthMin = 150;

    private JPanel tabPanelContainer;
    private CardLayout tabLayout;

    private int tabcount;
    private JToggleButton[] tabs;
    private JPanel[] panels;
    private String[] tabName;
    private int tabHeight = 100;

    private ActiveListener[] listener;

    private int selected = 0;

    /**
     * Creates the VerticalTabs Component. This Component creates Layout with tabs on left side,
     * which all have unique JPanel.
     * The respective Tab JPanel can be requested by getTabPanel().
     * @param tabcount The amount of tabs the layout has to create.
     */
    public VerticalTabs(int tabcount, int navWidthMin, int navWidth) {
        this.navWidth = navWidth;
        this.navWidthMin = navWidthMin;
        assert (tabcount > 0);

        this.listener = listener;

        this.tabcount = tabcount;

        nav = new JPanel();

        tabs = new JToggleButton[tabcount];
        panels = new JPanel[tabcount];
        tabName = new String[tabcount];

        for (int i = 0; i < tabcount; i++) {
            tabs[i] = new JToggleButton("Tab " + i);
            tabs[i].addActionListener(new TabListener(i, this));

            panels[i] = new JPanel();
            tabName[i] = Integer.toString(i);
        }

        //Configuring Tab Panel Container
        tabLayout = new CardLayout();
        tabPanelContainer = new JPanel(tabLayout);

        for (int i = 0; i < tabcount; i++) {
            tabPanelContainer.add(panels[i], tabName[i]);
        }

        //Configuring Main Layout
        mainLayout = new GroupLayout(this);
        mainLayout.setHorizontalGroup(
                mainLayout.createSequentialGroup()
                    .addComponent(nav, navWidthMin, navWidth, navWidth)
                    .addComponent(tabPanelContainer)
        );
        mainLayout.setVerticalGroup(
               mainLayout.createParallelGroup()
                    .addComponent(nav, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabPanelContainer)
        );
        this.setLayout(mainLayout);

        //Configuring navigationbar Layout
        navLayout = new GroupLayout(nav);
        GroupLayout.ParallelGroup hori = navLayout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        for (int i = 0; i < tabcount; i++) {
            hori = hori.addComponent(tabs[i], 0, navWidth, navWidth);
        }
        navLayout.setHorizontalGroup(hori);

        GroupLayout.SequentialGroup vert = navLayout.createSequentialGroup();
        for (int i = 0; i < tabcount; i++) {
            vert.addComponent(tabs[i], 0, tabHeight, Short.MAX_VALUE);
        }
        navLayout.setVerticalGroup(vert);
        nav.setLayout(navLayout);

        setSelected(0);
    }

    /**
     * Sets a tab as selected.
     * @param i the tab to select.
     */
    public void setSelected(int i) {
        System.out.println("Selecting: " + i);
        assert (0 <= i && i < tabcount);

        if (listener != null) {
            listener[selected].onDeactivate();
            listener[i].onActive();
        }
        selected = i;

        for (JToggleButton tb : tabs) {
            tb.setSelected(false);
        }

        tabLayout.show(tabPanelContainer, tabName[i]);
        tabs[i].setSelected(true);
    }

    public int getSelected() {
        return selected;
    }

    public void setTabText(int tab, String text) {
        tabs[tab].setText(text);
    }

    public String getTabText(int tab) {
        return tabs[tab].getText();
    }

    public JPanel getTabPanel(int tab) {
        return panels[tab];
    }

    public int getTabCount() {
        return tabcount;
    }

    public void nextTab() {
        setSelected((selected + 1) % tabcount);
    }

    public void setActiveListener(ActiveListener[] listener) {
        assert (listener.length >= tabcount);
        this.listener = listener;
    }

    /**
     * Set the font of the tab buttons.
     * @param f The font of choice.
     */
    public void setTabFont(Font f) {
        for (JToggleButton b : tabs) {
            b.setFont(f);
        }
    }
}
