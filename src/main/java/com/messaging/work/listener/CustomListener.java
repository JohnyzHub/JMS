/**
 * 
 */
package com.messaging.work.listener;

import javax.jms.CompletionListener;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author johnybasha
 *
 */
public class CustomListener implements MessageListener, ExceptionListener, CompletionListener{

	public void onMessage(Message message) {
		System.out.println("Received Message : - " + message);
		try {
			message.acknowledge();
			System.out.println("Message Acknowledged ");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onException(JMSException exception) {
		System.out.println("Exception Occured : " + exception.getMessage());

	}

	@Override
	public void onCompletion(Message message) {
		System.out.println("Message Received : Complete : " + message);


	}

	@Override
	public void onException(Message message, Exception exception) {
		System.out.println("Exception Occured : " + message + " , :: "+ exception.getMessage());

	}

}
