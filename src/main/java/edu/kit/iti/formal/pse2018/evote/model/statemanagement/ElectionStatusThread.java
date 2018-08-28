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

package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;

import java.util.Date;
import java.util.Random;

public class ElectionStatusThread extends Thread {

    public static double BACKOFF_AVG = (15 * 1000);
    public static double DEVIATION = 1000;

    protected long backoff;
    protected long lastEvent;
    protected boolean hasEnded;
    protected Election election;
    protected ElectionStatusListener electionStatusListener;

    /**
     * Creates an instance of ElectionStatusThread. This thread will repeatedly cause
     * a check, whether the Election is over by the SDKConnection.
     *
     * @param election The Election context.
     */
    public ElectionStatusThread(Election election) throws NetworkException, NetworkConfigException {
        hasEnded = false;
        Random r = new Random();
        backoff = Math.round(r.nextGaussian() * DEVIATION + BACKOFF_AVG);

        this.election = election;
    }

    public void setElectionStatusListener(ElectionStatusListener electionStatusListener) {
        this.electionStatusListener = electionStatusListener;
    }

    private void dispatchElectionCheck() {
        System.out.print(new Date() + ": Checkinging Election Over... ");
        try {
            election.reloadVotes();
            if (election.checkElectionOver()) {
                System.out.println("True");
                electionStatusListener.electionOver();
                hasEnded = true;
            } else {
                System.out.println("False");
                electionStatusListener.electionUpdate();
            }
        } catch (NetworkException | NetworkConfigException | WrongCandidateNameException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!hasEnded) {
            long cur = System.currentTimeMillis();
            long diff = cur - lastEvent;
            if (diff > backoff) {
                lastEvent = cur;
                dispatchElectionCheck();
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

    @Override
    public synchronized void start() {
        System.out.println("Starting ElectionStatusThread");
        super.start();
    }
}
