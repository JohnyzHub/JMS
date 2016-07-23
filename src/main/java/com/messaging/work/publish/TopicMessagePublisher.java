package com.messaging.work.publish;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.messaging.work.listener.CustomListener;

public class TopicMessagePublisher{

	private static ConnectionFactory connectionFactory;

	private static Topic jmsTopic;

	private static JMSContext jmsContext;

	public TopicMessagePublisher() {
		publish();
	}

	private void setupResources() throws NamingException {
		InitialContext iContext = new InitialContext();
		connectionFactory = (ConnectionFactory)iContext.lookup("jms/MyConnectionFactory");
		jmsTopic = (Topic) iContext.lookup("jms/myTopic");
	}

	private void setupDestination() {
		jmsContext = connectionFactory.createContext();
		jmsContext.setClientID("MyClientID1");
		jmsContext.setExceptionListener(new CustomListener());
	}


	private void publishMessages(String msg) throws JMSException {
		if (msg == null || msg.isEmpty()) {
			return;
		}
		TextMessage message = jmsContext.createTextMessage(msg);
		message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
		jmsContext.createProducer().setAsync(new CustomListener()).send(jmsTopic, message);
	}

	private void publish() {

		try {
			this.setupResources();
			this.setupDestination();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {
		try {
			new TopicMessagePublisher().publishMessages("Message from Publisher");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}