/**
 * 
 */
package com.messaging.work.subscribe;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.messaging.work.listener.CustomListener;


/**
 * @author johnybasha
 *
 */
public class QueueMessageReceiver {

	private static ConnectionFactory connectionFactory;

	private static Queue jmsQueue;

	private void setupResources() throws NamingException {

		InitialContext iContext = new InitialContext();
		connectionFactory = (ConnectionFactory)iContext.lookup("jms/MyConnectionFactory");
		jmsQueue = (Queue) iContext.lookup("jms/myQueue");
	}


	private void receiveMessage() {
		JMSContext jmsContext = connectionFactory.createContext();
		jmsContext.setExceptionListener(new CustomListener());
		jmsContext.createConsumer(jmsQueue).setMessageListener(new CustomListener());
		while(true){}

/**
		Message message = (Message)jmsContext.createConsumer(jmsQueue).receive();
		if (message != null) {
			System.out.println("Message Received : " + message);
			try {
				message.acknowledge();

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
*/
	}

	private void receive() throws NamingException {
		this.setupResources();
		this.receiveMessage();
	}

	public static void main(String args[]) {
		try {
			new QueueMessageReceiver().receive();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}