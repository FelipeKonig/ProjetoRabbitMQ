
package br.edu.ifsc.ProjetoRabbitMQ.RabbitMQ;

import com.rabbitmq.client.*;

import br.edu.ifsc.ProjetoRabbitMQ.Controller.MenuController;

import java.io.IOException;

public class ReceiverRow extends Thread {

	private String QUEUE_NAME;
	private MenuController menu;

	public void defineReceiver(String name, MenuController menu) {
		this.QUEUE_NAME = name;
		this.menu = menu;
	}

	@Override
	public void run() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri("amqps://vfxhepxm:4WVTy61M2VDTpq8hsQ-KFxqfn-eOUYB3@jackal.rmq.cloudamqp.com/vfxhepxm");

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println("Aguardando mensagens da fila " + QUEUE_NAME);

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" Mensagem: " + message);
					String number[] = message.split(": ");
					menu.addMessage(message, number[0]);
					menu.updateListContact(number[0], true);
				}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
