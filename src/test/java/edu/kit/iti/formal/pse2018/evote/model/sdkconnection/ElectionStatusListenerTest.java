package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.ElectionStatusListener;

import org.hyperledger.fabric.sdk.ChaincodeEvent;
import org.hyperledger.fabric.sdk.Channel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ElectionStatusListener.class)
public class ElectionStatusListenerTest {

    @Test
    public void received_end() {
        SDKEventListener eventListener = mock(SDKEventListener.class);
        Channel channel = mock(Channel.class);
        ElectionStatusListener electionStatusListener = new ElectionStatusListener(eventListener, channel);
        ChaincodeEvent event = mock(ChaincodeEvent.class);
        when(event.getPayload()).thenReturn(new byte[]{1});
        electionStatusListener.received("", null, event);
        verify(eventListener, times(1)).onElectionEnd();
    }

    @Test
    public void received_running() {
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