package io.turntabl.email;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class EmailApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmailApplication.class, args);


		Jedis jedis = null;

		/* Creating Jedis object for connecting with redis server */


		jedis = new Jedis(System.getenv("REDIS_URL"));

		JedisPubSub jedisPubSub = new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("Channel " + channel + " has sent a message : " + message );
				Gson gson = new Gson();
				Client client = gson.fromJson(message, Client.class);

//				System.out.println("Convert Client | " + client);
				RedisMessageSubscriber redisMessageSubscriber = new RedisMessageSubscriber();
				redisMessageSubscriber.messageListener(client);
			}

			@Override
			public void onSubscribe(String channel, int subscribedChannels) {
				System.out.println("Client is Subscribed to channel : "+ channel);
				System.out.println("Client is Subscribed to "+ subscribedChannels + " no. of channels");
			}
		};
		/* Subscribing to channel C1 and C2 */
		jedis.subscribe(jedisPubSub, "customers");

	}

}
