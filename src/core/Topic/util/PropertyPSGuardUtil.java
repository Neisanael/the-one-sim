package core.Topic.util;

import core.Message;
import core.Topic.NumericAttr;
import core.Topic.Topic;

import java.util.Random;

public class PropertyPSGuardUtil {
    static Random rand;

    public static Topic<String> propertyTopic(){
        String[] listTopicDummy = {"lorem", "ipsum", "dolor", "sit", "amet"};
        rand = new Random();
        int randomIndex = rand.nextInt(listTopicDummy.length);
        String topicDummy = listTopicDummy[randomIndex];
        return new Topic(topicDummy);
    }

    public static NumericAttr<Integer> propertyPublisher() {
        int randomNumeric = rand.nextInt(30) + 1;
        return new NumericAttr<Integer>(randomNumeric);
    }
/*
    public NumericAttr propertySubscriber() {
        int randomNumeric1 = rand.nextInt(30) + 1;
        int randomNumeric2 = rand.nextInt(30) + 1;
    }*/

    public static String processTopicValue(Message m) {
        Object property = m.getProperty("topic");
        if (property instanceof Topic<?>) {
            return checkCastTopic((Topic<?>) property);
        } else {
            throw new ClassCastException("The 'topic' property is not of type Topic");
        }
    }

    private static String checkCastTopic(Topic<?> topic) {
        if (topic.getName() instanceof String) {
            return (String) topic.getName();
        } else {
            return null;
        }

    }

    public static int[] processNumericValue(Message m) {
        Object property = m.getProperty("numeric");
        if (property instanceof NumericAttr<?>) {
            return checkCastNumericValue((NumericAttr<?>) property);
        } else {
            throw new ClassCastException("The 'numeric' property is not of type NumericAttr");
        }
    }

    private static int[] checkCastNumericValue(NumericAttr<?> numeric) {
        if(numeric.getValue1() instanceof Integer) {
            if(numeric.getValue2() instanceof Integer) {
                return new int[]{(int) numeric.getValue1(), (int) numeric.getValue2()};
            } else {
                return new int[]{(int) numeric.getValue1()};
            }
        } else {
           return null;
        }
    }


}
