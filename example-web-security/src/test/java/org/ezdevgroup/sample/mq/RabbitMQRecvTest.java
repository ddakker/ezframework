package org.ezdevgroup.sample.mq;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RabbitMQRecvTest {
	private final static String QUEUE_NAME = "log";
	
	@Before
	public void before() {
	}
	
	
	/*@Test
	public void test1_Send() {
		Connection connection = null;
		Channel channel = null;
		
		try {
			ConnectionFactory factory = new ConnectionFactory();
			System.out.println("factory: " + factory);
			factory.setHost("222.231.44.162");
			factory.setUsername("admin");
			factory.setPassword("1230987");
			factory.setVirtualHost("/");
			connection = factory.newConnection();
			System.out.println("connection: " + connection);
			channel = connection.createChannel();
			System.out.println("channel: " + channel);
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String message = "Hello World!";
		    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		    System.out.println(" [x] Sent '" + message + "'");
		    
		    channel.close();
		    connection.close();
		} catch (IOException e) {
			System.err.println("e: " + e);
		}
	}*/
	
	@Test
	public void test2_Recv() {
		Connection connection = null;
		Channel channel = null;
		
		try {
			ConnectionFactory factory = new ConnectionFactory();
			System.out.println("factory: " + factory);
			factory.setUsername("ezwel");
			factory.setPassword("ezwel");
			factory.setVirtualHost("log");
			
			Address[] addrArr = new Address[]{ new Address("222.231.44.162", 5672), new Address("222.231.44.162", 5673), new Address("222.231.44.175", 5673), new Address("222.231.44.175", 5673)};
			ExecutorService es = Executors.newFixedThreadPool(20);
			connection = factory.newConnection(es, addrArr);
			System.out.println("connection: " + connection);
			channel = connection.createChannel();
			
			System.out.println("channel: " + channel);
			
		    QueueingConsumer consumer = new QueueingConsumer(channel);
		    channel.basicConsume(QUEUE_NAME, true, consumer);

		    while (true) {
		      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		      String message = new String(delivery.getBody());
		      System.out.println(" [x] Received22 '" + message + "'");
		    }
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
		
		
	}
}
