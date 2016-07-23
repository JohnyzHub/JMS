/**
 * 
 */
package com.messaging.work.publish;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
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

	private void publish() throws NamingException {
		this.setupResources();
		this.publishMessages();
	}


	public void publishMessages() {
		JMSContext jmsContext = connectionFactory.createContext();
		jmsContext.setExceptionListener(new CustomListener());
		Message myMessage = jmsContext.createTextMessage("This is Latest2 message from jms/myQueue");
		int x = 3;
		if (x==2) {
			throw new IllegalArgumentException("Not a valid message");
		}
		jmsContext.createProducer().setAsync(new CustomListener()).send(jmsQueue, myMessage);
		while(true) {}
	}

	public static void main(String args[]) {
		try {
			new QueueMessageSender().publish();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}