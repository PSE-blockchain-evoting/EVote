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

package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;

import org.hyperledger.fabric.sdk.ChaincodeEvent;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ElectionStatusListener.class)
public class ElectionStatusListenerTest {

    @Test
    public void received_end() throws InvalidArgumentException {
        SDKEventListener eventListener = mock(SDKEventListener.class);
        Channel channel = mock(Channel.class);
        ElectionStatusListener electionStatusListener = new ElectionStatusListener(eventListener, channel);
        ChaincodeEvent event = mock(ChaincodeEvent.class);
        when(event.getPayload()).thenReturn(new byte[]{1});
        electionStatusListener.received("", null, event);
        verify(eventListener, times(1)).onElectionEnd();
    }

    @Test
    public void received_running() throws InvalidArgumentException {
        SDKEventListener eventListener = mock(SDKEventListener.class);
        Channel channel = mock(Channel.class);
        ElectionStatusListener electionStatusListener = new ElectionStatusListener(eventListener, channel);
        ChaincodeEvent event = mock(ChaincodeEvent.class);
        when(event.getPayload()).thenReturn(new byte[]{0});
        electionStatusListener.received("", null, event);
        verify(eventListener, times(1)).onElectionRunning();
    }

    /*
    @Test(expected = IllegalArgumentException.class)
    public void invalid_resource() {
        SDKEventListener eventListener = mock(SDKEventListener.class);
        Channel channel = mock(Channel.class);
        ResourceBundle bundle = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getKeys() {
                return Collections.emptyEnumeration();
            }
        };
        PowerMockito.mockStatic(ResourceBundle.class);
        when(ResourceBundle.getBundle("config")).thenReturn(bundle);
        ElectionStatusListener electionStatusListener = new ElectionStatusListener(eventListener, channel);
    }
    */
}