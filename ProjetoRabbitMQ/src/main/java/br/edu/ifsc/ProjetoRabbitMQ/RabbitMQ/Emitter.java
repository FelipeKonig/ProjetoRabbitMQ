package br.edu.ifsc.ProjetoRabbitMQ.RabbitMQ;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emitter extends Thread {

	private String queueOrExchangeName;
	private String message;

	public void writeMessage(String msg) {
		this.message = msg;
	}

	public void defineReceiverName(String name) {
		this.queueOrExchangeName = name;
	}

	@Override
	public void run() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri("amqps://vfxhepxm:4WVTy61M2VDTpq8hsQ-KFxqfn-eOUYB3@jackal.rmq.cloudamqp.com/vfxhepxm");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			if (queueOrExchangeName.contentEquals("444")) {
				channel.exchangeDeclare(queueOrExchangeName, BuiltinExchangeType.FANOUT);
				channel.basicPublish(queueOrExchangeName, "", null, message.getBytes("UTF-8"));
				System.out.println(" Mensagem enviada para o grupo " + queueOrExchangeName);
			} else {
				channel.queueDeclare(queueOrExchangeName, false, false, false, null);
				channel.basicPublish("", queueOrExchangeName, null, message.getBytes("UTF-8"));
				System.out.println("Mensagem enviada para a fila " + queueOrExchangeName);
			}

			channel.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
