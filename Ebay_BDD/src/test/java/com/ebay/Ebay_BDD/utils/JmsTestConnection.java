package com.ebay.Ebay_BDD.utils;

import javax.jms.*;
import java.io.Serializable;
import java.util.List;

public class JmsTestConnection {
	private Session session;
	private Connection connection;
	private ConnectionFactory connectionFactory;

	public JmsTestConnection(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public Queue createQueue(String queueName) throws JMSException {
		return session.createQueue(queueName);
	}

	public void sendMessage(Serializable object, MessageProducer producer) {
		try {
			// Create a messages
			ObjectMessage message = session.createObjectMessage(object);
			producer.send(message);
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

	public void closeSession() throws JMSException {
		session.close();
		connection.close();
	}

	public void startServerSession() throws JMSException {
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
	}

	public MessageProducer createInputProducer(Queue inputDestination)
			throws JMSException {
		MessageProducer producer = session.createProducer(inputDestination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		return producer;
	}

	public MessageConsumer createOutputReceiver(String outputQueue)
			throws JMSException {
		Queue outputDestination = session.createQueue(outputQueue);
		MessageConsumer outputConsumer = session
				.createConsumer(outputDestination);

		return outputConsumer;
	}

	public void attachListenerToOutputConsumer(MessageListener testListener,
			MessageConsumer outputConsumer) throws JMSException {
		outputConsumer.setMessageListener(testListener);
	}

	public <T> MessageListener createTestListener(
			final List<T> receivedCollection) {

		MessageListener testListener = new MessageListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onMessage(Message received) {
				if (received instanceof ObjectMessage) {
					try {
						receivedCollection.add((T) ((ObjectMessage) received)
								.getObject());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		};
		return testListener;
	}

	public Session getSession() {
		return session;
	}

}
