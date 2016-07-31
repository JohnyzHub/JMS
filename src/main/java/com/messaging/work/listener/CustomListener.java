/**
 * 
 */
package com.messaging.work.listener;

import java.util.Enumeration;

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
			Enumeration<String> propNames = message.getPropertyNames();
			while(propNames.hasMoreElements()) {
				String propName = propNames.nextElement();
				Object propObj = message.getObjectProperty(propName);
				if (propObj != null) {
					System.out.println("Property Name : " + propName);
					System.out.println("Property Value : " + propObj);
				}
			}
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



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
