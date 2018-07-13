package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.utils.CandidatePercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.TimeOnlyCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VoterPercentileCondition;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorAdapter;

import java.awt.Font;
import java.awt.LayoutManager2;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXDatePicker;

public class TimespanPanel extends ConfigPanel {

    private JLabel lblStart;
    private JLabel lblEnd;
    private JLabel lblFiller;
    private JLabel lblPercentage;
    private JLabel lblPercentSign;
    private JButton btnImmediately;
    private JSpinner spnStartTime;
    private JSpinner spnEndTime;
    private JXDatePicker dpStartDate;
    private JXDatePicker dpEndDate;
    private JSpinner spnPercentage;
    private JComboBox<String> cbxExtraCond;

    public TimespanPanel(JPanel container, ConfigGUI gui, VerticalTabs vt, SupervisorAdapter adapter) {
        super(container, gui, vt, adapter);
    }

    @Override
    protected void initComponents() {

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");

        lblStart = new JLabel(lang.getString("lblStartText"));
        lblStart.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEnd = new JLabel(lang.getString("lblEndText"));
        lblEnd.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFiller = new JLabel();
        lblPercentage = new JLabel(lang.getString("lblPercentageText"));
        lblPercentage.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPercentSign = new JLabel("%");
        btnImmediately = new JButton(lang.getString("btnImmediateText"));

        spnStartTime = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnStartTime, "HH:mm");
        spnStartTime.setEditor(timeEditor);
        spnStartTime.setValue(new Date()); // will only show the current time
        spnEndTime = new JSpinner(new SpinnerDateModel());
        timeEditor = new JSpinner.DateEditor(spnEndTime, "HH:mm");
        spnEndTime.setEditor(timeEditor);
        spnEndTime.setValue(new Date()); // will only show the current time

        dpStartDate = new JXDatePicker();
        dpEndDate = new JXDatePicker();

        spnPercentage = new JSpinner(new SpinnerNumberModel());

        cbxExtraCond = new JComboBox<>();
        cbxExtraCond.addItem(lang.getString("TIME_ONLY_CONDITION"));
        cbxExtraCond.addItem(lang.getString("VOTER_PERCENTILE_CONDITION"));
        cbxExtraCond.addItem(lang.getString("CANDIDATE_PERCENTILE_CONDITION"));

        Font f = (Font) UIManager.get("General.font");
        lblStart.setFont(f);
        lblEnd.setFont(f);
        lblPercentage.setFont(f);
        lblPercentSign.setFont(f);
        btnImmediately.setFont(f);
        dpStartDate.setFont(f);
        dpEndDate.setFont(f);
        spnStartTime.setFont(f);
        spnEndTime.setFont(f);
        spnPercentage.setFont(f);
        cbxExtraCond.setFont(f);
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout gl = new GroupLayout(container);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(gl.createParallelGroup()
                                .addGroup(gl.createSequentialGroup()
                                        .addComponent(lblStart)
                                        .addComponent(dpStartDate)
                                        .addComponent(spnStartTime)
                                        .addComponent(btnImmediately)
                                )
                                .addGroup(gl.createSequentialGroup()
                                        .addComponent(lblEnd)
                                        .addComponent(dpEndDate)
                                        .addComponent(spnEndTime)
                                )
                                .addGroup(gl.createSequentialGroup()
                                        .addComponent(lblFiller)
                                        .addComponent(cbxExtraCond, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(gl.createSequentialGroup()
                                        .addComponent(lblPercentage)
                                        .addComponent(spnPercentage, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPercentSign)
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(lblStart)
                                .addComponent(dpStartDate)
                                .addComponent(spnStartTime)
                                .addComponent(btnImmediately)
                        )
                        .addGroup(gl.createParallelGroup()
                                .addComponent(lblEnd)
                                .addComponent(dpEndDate)
                                .addComponent(spnEndTime)
                        )
                        .addGroup(gl.createParallelGroup()
                                .addComponent(lblFiller)
                                .addComponent(cbxExtraCond, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(gl.createParallelGroup()
                                .addComponent(lblPercentage)
                                .addComponent(spnPercentage, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPercentSign)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );

        gl.linkSize(lblStart, lblEnd, lblFiller, lblPercentage);
        gl.linkSize(dpStartDate, dpEndDate);
        gl.linkSize(spnStartTime, spnEndTime);
        return gl;
    }

    /**
     * Gets the starting Time and Date entered in the UI.
     *
     * @return the starting Time and Date.
     */
    public Date getStartDate() {
        Date date = dpStartDate.getDate();
        Date time = (Date) spnStartTime.getModel().getValue();

        return combineDates(time, date);
    }

    public void setStartDate(Date start) {
        dpStartDate.setDate(start);
        spnStartTime.setValue(start);
    }

    /**
     * Gets the ending Time and Date entered in the UI.
     * c*
     *
     * @return the ending Time and Date.
     */
    public Date getEndDate() {
        Date date = dpEndDate.getDate();
        Date time = (Date) spnEndTime.getModel().getValue();

        return combineDates(time, date);
    }

    public void setEndDate(Date end) {
        dpEndDate.setDate(end);
        spnEndTime.setValue(end);
    }

    private Date combineDates(Date time, Date date) {
        if (time == null || date == null) {
            return null;
        }
        Calendar cdate = Calendar.getInstance();
        cdate.setTime(date);
        Calendar ctime = Calendar.getInstance();
        ctime.setTime(time);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, cdate.get(Calendar.YEAR));
        c.set(Calendar.MONTH, cdate.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, cdate.get(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, cdate.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, cdate.get(Calendar.MINUTE));
        return c.getTime();
    }

    /**
     * Gets the Selected ElectionEndCondition entered in the UI.
     *
     * @return The Selected ElectionEndCondition.
     */
    public ElectionEndCondition getEndCondition() {
        int index = cbxExtraCond.getSelectedIndex();
        switch (index) {
            case 0:
                return new TimeOnlyCondition();
            case 1:
                return new VoterPercentileCondition((int)spnPercentage.getModel().getValue());
            case 2:
                return new CandidatePercentileCondition((int)spnPercentage.getModel().getValue());
            default:
                throw new IllegalStateException("Unknown Selection: " + index);
        }
    }

    /**
     * Set the ElectionEndCondition.
     *
     * @param cond the ElectionEndCondition to show in the UI.
     */
    public void setEndCondition(ElectionEndCondition cond) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");
        if (cond instanceof TimeOnlyCondition) {
            cbxExtraCond.setSelectedItem(lang.getString("TIME_ONLY_CONDITION"));
        } else if (cond instanceof VoterPercentileCondition) {
            cbxExtraCond.setSelectedItem(lang.getString("VOTER_PERCENTILE_CONDITION"));
        } else if (cond instanceof CandidatePercentileCondition) {
            cbxExtraCond.setSelectedItem(lang.getString("CANDIDATE_PERCENTILE_CONDITION"));
        } else {
            throw new IllegalArgumentException("Unknown ElectionEndCondition instance");
        }
    }
}
