package test;

import core.Coord;
import core.DTNHost;
import core.MessageListener;
import core.MovementListener;
import core.NetworkInterface;
import core.Publisher.PublisherHost;
import core.Settings;
import junit.framework.TestCase;
import movement.MovementModel;
import movement.Path;

import org.junit.Assert;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import routing.MessageRouter;
import routing.PassiveRouter;

import java.util.ArrayList;

public class PublisherHostTest {
    //==========================================================================//
    // Setup/cleanup
    //==========================================================================//
    @BeforeClass
    public static void setUpBeforeClass()
            throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass()
            throws Exception {

    }
    //==========================================================================//


    //==========================================================================//
    // Tests
    //==========================================================================//
    /**
     * Tests the case where the DTNHost has no interfaces configured.
     *
     * @throws Exception
     */
    @Test
    public void testNoInterfaces()
            throws Exception {
        final DTNHost host = new PublisherHost(
                new ArrayList<MessageListener>(),
                new ArrayList<MovementListener>(),
                "",
                new ArrayList<NetworkInterface>(),
                null,
                makeMovementModel(),
                makeMessageRouter());

        // Tests
        Assert.assertFalse("Radio reported as active.", host.isRadioActive());
    }

    private static MovementModel makeMovementModel() {
        return new MovementModel() {
            @Override
            public Path getPath() {
                return null;
            }

            @Override
            public Coord getInitialLocation() {
                return null;
            }

            @Override
            public MovementModel replicate() {
                return makeMovementModel();
            }
        };
    }

    private static MessageRouter makeMessageRouter() {
        return new PassiveRouter(new Settings());
    }
    //==========================================================================//


    //==========================================================================//
    // Private
    //==========================================================================//

    //==========================================================================//
}
