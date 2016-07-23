# Messaging-Standalone
JMS 2x for Queue and Topic with listeners.

------------------------------------------------------------------------------
Procedure to create resources in Glassfish 4.1.1
------------------------------------------------------------------------------

          asadmin> create-jms-resource --restype javax.jms.ConnectionFactory --description "connection factory for durable subscriptions" --property ClientId=MyID jms/DurableConnectionFactory
         
          asadmin> create-jms-resource --restype javax.jms.Queue --property Name=MyQueue jms/MyQueue
          
          asadmin> create-jms-resource --restype javax.jms.Topic --property Name=MyTopic jms/MyTopic
          
          asadmin> delete-jms-resource jms/Queue
          
          The list of these added resources appear in domain.xml file under glassfish/domains/domain-name/config
          
------------------------------------------------------------------------------



------------------------------------------------------------------------------
Test Results : 

         Initial Context : 
               {java.naming.factory.initial=com.sun.enterprise.naming.impl.SerialInitContextFactory, 
                java.naming.factory.state=com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl, 
               java.naming.factory.url.pkgs=com.sun.enterprise.naming}

Queu - Message Sent : 

             Jul 20, 2016 12:17:20 PM org.hibernate.validator.internal.util.Version <clinit>
             INFO: HV000001: Hibernate Validator 5.0.0.Final
             Jul 20, 2016 12:17:20 PM com.sun.messaging.jms.ra.ResourceAdapter start
            INFO: MQJMSRA_RA1101: GlassFish MQ JMS Resource Adapter: Version:  5.1  (Build 9-b) Compile:  July 29 2014 1229
            Jul 20, 2016 12:17:20 PM com.sun.messaging.jms.ra.ResourceAdapter start
            INFO: MQJMSRA_RA1101: GlassFish MQ JMS Resource Adapter starting: broker is REMOTE, connection mode is TCP
            Jul 20, 2016 12:17:20 PM com.sun.messaging.jms.ra.ResourceAdapter start
            INFO: MQJMSRA_RA1101: GlassFish MQ JMS Resource Adapter Started:REMOTE
            Message Sent : 
            Text:	This is Fresh message from jms/myQueue
            Class:			com.sun.messaging.jmq.jmsclient.TextMessageImpl
            getJMSMessageID():	ID:9-192.168.0.105(d4:63:50:2f:d1:aa)-50058-1469031441614
            getJMSTimestamp():	1469031441614
            getJMSCorrelationID():	null
            JMSReplyTo:		null
            JMSDestination:		myQueue
            getJMSDeliveryMode():	PERSISTENT
            getJMSRedelivered():	false
            getJMSType():		null
            getJMSExpiration():	0
            getJMSDeliveryTime():	0
            getJMSPriority():	4
            Properties:		null


Queu - Message Received : 


            Jul 20, 2016 12:20:36 PM org.hibernate.validator.internal.util.Version <clinit>
            INFO: HV000001: Hibernate Validator 5.0.0.Final
            Jul 20, 2016 12:20:36 PM com.sun.messaging.jms.ra.ResourceAdapter start
            INFO: MQJMSRA_RA1101: GlassFish MQ JMS Resource Adapter: Version:  5.1  (Build 9-b) Compile:  July 29 2014 1229
            Jul 20, 2016 12:20:36 PM com.sun.messaging.jms.ra.ResourceAdapter start
            INFO: MQJMSRA_RA1101: GlassFish MQ JMS Resource Adapter starting: broker is REMOTE, connection mode is TCP
            Jul 20, 2016 12:20:36 PM com.sun.messaging.jms.ra.ResourceAdapter start
            INFO: MQJMSRA_RA1101: GlassFish MQ JMS Resource Adapter Started:REMOTE
            Message Received : 
            Text:	This is Fresh message from jms/myQueue
            Class:			com.sun.messaging.jmq.jmsclient.TextMessageImpl
            getJMSMessageID():	ID:9-192.168.0.105(d4:63:50:2f:d1:aa)-50058-1469031441614
            getJMSTimestamp():	1469031441614
            getJMSCorrelationID():	null
            JMSReplyTo:		null
            JMSDestination:		myQueue
            getJMSDeliveryMode():	PERSISTENT
            getJMSRedelivered():	false
            getJMSType():		null
            getJMSExpiration():	0
            getJMSDeliveryTime():	0
            getJMSPriority():	4
            Properties:		{JMSXDeliveryCount=1}
