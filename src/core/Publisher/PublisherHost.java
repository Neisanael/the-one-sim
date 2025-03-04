package core.Publisher;

import core.DTNHost;
import core.MovementListener;
import core.MessageListener;
import core.NAKT.Util.PropertyEncryptionUtil;
import core.NetworkInterface;
import core.ModuleCommunicationBus;
import core.Message;
import movement.MovementModel;
import routing.MessageRouter;
import core.Topic.util.PropertyPSGuardUtil;

import java.util.List;

public class PublisherHost extends DTNHost {

    /**
     * Creates a new DTNHost.
     *
     * @param msgLs        Message listeners
     * @param movLs        Movement listeners
     * @param groupId      GroupID of this host
     * @param interf       List of NetworkInterfaces for the class
     * @param comBus       Module communication bus object
     * @param mmProto      Prototype of the movement model of this host
     * @param mRouterProto Prototype of the message router of this host
     */
    public PublisherHost(List<MessageListener> msgLs, List<MovementListener> movLs, String groupId, List<NetworkInterface> interf, ModuleCommunicationBus comBus, MovementModel mmProto, MessageRouter mRouterProto) {
        super(msgLs, movLs, groupId, interf, comBus, mmProto, mRouterProto);
    }

    public void createNewMessage(Message m) {
        m.addProperty("topic", PropertyPSGuardUtil.propertyTopic());
        m.addProperty("numeric", PropertyPSGuardUtil.propertyPublisher());
        super.getRouter().createNewMessage(m);
    }

    public void receiveKeyEncryption(Message m){
        PropertyEncryptionUtil.processKeyEncryption(m);
    }


}
