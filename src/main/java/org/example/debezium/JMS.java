package org.example.debezium;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;

public class JMS {
    public static final String SERVER =  "failover://tcp://localhost:61616";
    static {
        System.out.println(SERVER);
    }

    public static void send(String server,String subject, String content) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // JMS messages are sent and received using a Session. We will
        // create here a non-transactional session object. If you want
        // to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(content);
        producer.send(message);
        connection.close();
    }
    public static void send(String server, String subject, String content, Consumer<String> consumer)   {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // JMS messages are sent and received using a Session. We will
            // create here a non-transactional session object. If you want
            // to use transactions you should set the first parameter to 'true'
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(content);
            producer.send(message);
            connection.close();
            consumer.accept("OK");
        }catch (Exception e)
        {
            consumer.accept(e.getMessage());
        }
    }



    public static List<String> browse(String server,String subject) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(subject);
        QueueBrowser queueBrowser = session.createBrowser(queue);
        Enumeration<ActiveMQTextMessage> enumeration = queueBrowser.getEnumeration();
        List<String> strings = new ArrayList<String>();
        while (enumeration.hasMoreElements()) {
            strings.add(enumeration.nextElement().getText());
        }
        connection.close();
        return strings;
    }


    public static String receive(String server,String subject) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageConsumer consumer = session.createConsumer(destination);
        TextMessage textMessage = (TextMessage) consumer.receive();
        String result = textMessage.getText();
        connection.close();
        return result;
    }
}
