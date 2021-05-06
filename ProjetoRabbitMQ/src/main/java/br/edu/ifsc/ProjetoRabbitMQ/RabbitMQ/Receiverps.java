package br.edu.ifsc.ProjetoRabbitMQ.RabbitMQ;

import com.rabbitmq.client.*;

import br.edu.ifsc.ProjetoRabbitMQ.Controller.MenuController;

import java.io.IOException;

public class Receiverps extends Thread {

	private static final String EXCHANGE_NAME = "444";
	private MenuController menu;

	public void saveMenu(MenuController menu) {
		this.menu = menu;
	}

	@Override
	public void run() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri("amqps://vfxhepxm:4WVTy61M2VDTpq8hsQ-KFxqfn-eOUYB3@jackal.rmq.cloudamqp.com/vfxhepxm");
			
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, EXCHANGE_NAME, "");

			System.out.println(" Aguardando mensagens do grupo " + EXCHANGE_NAME);

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" Mensagem: " + message);
					menu.addMessage(message, "444");
					menu.updateListContact("444", true);
				}
			};
			channel.basicConsume(queueName, true, consumer);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
