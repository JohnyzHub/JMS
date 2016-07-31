/**
 * 
 */
package com.messaging.work.publish;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.messaging.work.listener.CustomListener;

/**
 * @author johnybasha
 *
 */
public class QueueMessageSender {

	private static ConnectionFactory connectionFactory;

	private static Queue jmsQueue;

	private void setupResources() throws NamingException {

		InitialContext iContext = new InitialContext();
		System.out.println("Initial Context : " + iContext.getEnvironment());
		connectionFactory = (ConnectionFactory)iContext.lookup("jms/MyConnectionFactory");

		jmsQueue = (Queue) iContext.lookup("jms/myQueue");
	}

	private void publish() throws NamingException, JMSException {
		this.setupResources();
		this.publishMessages();
	}


	public void publishMessages() throws JMSException {
		JMSContext jmsContext = connectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE);
		jmsContext.setExceptionListener(new CustomListener());
		Message myMessage1 = jmsContext.createTextMessage("This is Latest1 message from jms/myQueue");
		myMessage1.setStringProperty("SYMBOL", "CIRCLE");
		Message myMessage2 = jmsContext.createTextMessage("This is Latest2 message from jms/myQueue");
		myMessage2.setStringProperty("SYMBOL", "SQUARE");
		int x = 3;
		if (x==2) {
			throw new IllegalArgumentException("Not a valid message");
		}
		JMSProducer queueProducer = jmsContext.createProducer().setAsync(new CustomListener());
		queueProducer.send(jmsQueue, myMessage1);
		queueProducer.send(jmsQueue, myMessage2);
		while(true) {}
	}

	public static void main(String args[]) {
		try {
			new QueueMessageSender().publish();
		} catch (NamingException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}