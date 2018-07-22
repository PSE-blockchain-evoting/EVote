package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;

import java.util.Random;

public class SDKEventListenerImpl extends Thread implements SDKEventListener {

    public static double BACKOFF_AVG = (5 * 60 * 1000);
    public static double DEVIATION = 60 * 1000;

    protected long backoff;
    protected long lastEvent;
    protected boolean hasEnded;
    protected Election election;
    protected ElectionStatusListener electionStatusListener;

    /**
     * Creates an instance of SDKEventListenerImpl. This thread will repeatedly cause
     * a check, whether the Election is over by the SDKConnection.
     *
     * @param election The Election context.
     */
    public SDKEventListenerImpl(Election election) throws NetworkException, NetworkConfigException {
        hasEnded = false;
        Random r = new Random();
        backoff = Math.round(r.nextGaussian() * DEVIATION + BACKOFF_AVG);

        this.election = election;
        election.checkElectionOver();
    }

    public void setElectionStatusListener(ElectionStatusListener electionStatusListener) {
        this.electionStatusListener = electionStatusListener;
    }

    @Override
    public void onElectionEnd() {
        lastEvent = System.currentTimeMillis();
        hasEnded = true;
        if (electionStatusListener != null) {
            electionStatusListener.electionOver();
        }
    }

    @Override
    public void onElectionRunning() {
        lastEvent = System.currentTimeMillis();
        assert (!hasEnded);

        if (electionStatusListener != null) {
            electionStatusListener.electionUpdate();
        }
    }

    @Override
    public void run() {
        while (!hasEnded) {
            long cur = System.currentTimeMillis();
            long diff = cur - lastEvent;
            if (diff > backoff) {
                lastEvent = cur;
                try {
                    election.checkElectionOver();
                    election.reloadVotes();
                } catch (NetworkException | NetworkConfigException
                        | LoadVoteException | WrongCandidateNameException e) {
                    e.printStackTrace();
                    break;
                }
            }

            long sleep = backoff + lastEvent - cur;
            assert (sleep > 0);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
