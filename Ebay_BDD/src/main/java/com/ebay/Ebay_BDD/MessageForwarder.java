package com.ebay.Ebay_BDD;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class MessageForwarder implements MessageListener {

	private static final String QUEUE_NAME = "TEST.OUTPUT";
	private String JMS_SERVER_URL = "vm://localhost:61616";
	private ConnectionFactory connectionFactory;
	private Session session;
	private Connection connection;
	private Destination destination;

	@Override
	public void onMessage(Message received) {
		try {
			connectionFactory = new ActiveMQConnectionFactory(JMS_SERVER_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
            // Create a MessageProducer from the Session to the Topic or Queue
    		destination = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            if (received instanceof ObjectMessage) {
	            // Create a messages
            	Trade executedTrade = (Trade) ((ObjectMessage)received).getObject();
            	executedTrade.setExecuted(true);
	             ObjectMessage message = session.createObjectMessage(executedTrade);
 
		        // Tell the producer to send the message
		        System.out.println("Sent message: "+ message.getObject().toString());
		        producer.send(message);
            }
		} catch (JMSException exception ) {
			System.out.print(exception.getMessage());
		}
		
	}

}
