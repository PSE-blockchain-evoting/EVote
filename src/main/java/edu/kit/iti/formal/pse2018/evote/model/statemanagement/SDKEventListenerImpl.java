package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;

import java.util.Date;

public class SDKEventListenerImpl implements SDKEventListener {
    private int backoff;
    private Date lastEvent;
    private boolean hasEnded;
    private ElectionStatusListener electionStatusListener;

    @Override
    public void onElectionEnd() {

    }

    @Override
    public void onElectionRunning() {

    }

    public void run() {
        //TODO: IMPLEMENT
    }
}
