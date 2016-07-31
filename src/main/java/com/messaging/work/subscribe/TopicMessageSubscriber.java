package com.messaging.work.subscribe;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.messaging.work.listener.CustomListener;

public class TopicMessageSubscriber {

	private static ConnectionFactory connectionFactory;

	private static Topic jmsTopic;

	private static JMSContext jmsContext;

	public TopicMessageSubscriber() {
		this.subscribe();
	}

	private void setupResources() throws NamingException {
		InitialContext iContext = new InitialContext();
		connectionFactory = (ConnectionFactory)iContext.lookup("jms/MyConnectionFactory");
		jmsTopic = (Topic) iContext.lookup("jms/myTopic");
	}

	private void setupDestination() {
		jmsContext = connectionFactory.createContext();
		jmsContext.setClientID("MyClientID2");
		jmsContext.setExceptionListener(new CustomListener());
	}

	public void subscribeMessages() throws JMSException {
		jmsContext.createDurableConsumer(jmsTopic, "MyDurableSubscrb").setMessageListener(new CustomListener());
		while(true) {}
		/**
		jmsContext.createConsumer(jmsTopic).setMessageListener(new CustomListener());
		Message message = jmsContext.createConsumer(jmsTopic).receive();
		if (message != null && message instanceof TextMessage) {
			System.out.println("Message Received : " + ((TextMessage)message).getText());
		}
		 */
	}

	private void subscribe() {
		try {
			this.setupResources();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setupDestination();
	}


	public static void main(String[] args) {
		try {
			new TopicMessageSubscriber().subscribeMessages();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
