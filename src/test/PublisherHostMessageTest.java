package test;

import core.DTNHost;
import core.Message;
import core.SimClock;
import core.Topic.NumericAttr;
import core.Topic.Topic;
import core.Topic.util.PropertyPSGuardUtil;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class PublisherHostMessageTest {

    private Message msg;
    private DTNHost from;
    private DTNHost to;
    private SimClock sc;

    @Before
    public void setUp() throws Exception {
        sc = SimClock.getInstance();
        sc.setTime(10);

        msg = new Message(from, to, "M", 100);
        msg.setTtl(10);

    }

    @Test
    public void testGetTtl() {
        Assert.assertEquals(10, msg.getTtl());

        sc.advance(50);
        Assert.assertEquals(9, msg.getTtl());

        sc.advance(120);
        Assert.assertEquals(7, msg.getTtl());

        sc.advance(180);
        Assert.assertEquals(4, msg.getTtl());

        sc.advance(240);
        Assert.assertEquals(0, msg.getTtl());


    }

    @Test
    public void testAddTopic() {
/*        Topic<String> topic = new Topic<String>("test");
        NumericAttr<Integer> numeric = new NumericAttr<Integer>(20);*/

    }

    @Test
    public void testAddPropertyNumeric(){
        String[] listTopicDummy = {"lorem", "ipsum", "dolor", "sit", "amet"};
        Topic<String> topic1 = PropertyPSGuardUtil.propertyTopic();
        Topic<String> topic2 = PropertyPSGuardUtil.propertyTopic();
        boolean containsValue1 = Arrays.asList(listTopicDummy).contains(topic1.getName());
        boolean containsValue2 = Arrays.asList(listTopicDummy).contains(topic2.getName());
        Assert.assertTrue("The array should contain the value: " + topic1.getName(), containsValue1);
        Assert.assertTrue("The array should contain the value: " + topic2.getName(), containsValue2);

        NumericAttr<Integer> numeric1 = PropertyPSGuardUtil.propertyPublisher();
        NumericAttr<Integer> numeric2 = PropertyPSGuardUtil.propertyPublisher();

        String value1 = String.valueOf(numeric1.getValue1());
        String value2 = String.valueOf(numeric2.getValue1());

        msg.addProperty(topic1.getName(), value1);
        msg.addProperty(topic2.getName(), value2);

        Assert.assertEquals("The Value should be the same", value1, msg.getProperty(topic1.getName()));

/*        assertEquals(value1, msg.getProperty("foo"));
        assertEquals(value2, msg.getProperty("bar"));*/
    }

}
