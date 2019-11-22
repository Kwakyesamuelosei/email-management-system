package io.turntabl.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RedisMessageSubscriber {

    public static List<Client> clients = new ArrayList<>();

    public void messageListener(Client client) {
//        System.out.println("Get Message Listener: " + client);
        clients.add(client);
//        System.out.println("Get All client : " + clients);

    }
    @ApiOperation("Get All Notification Published By Customer Service")
    @GetMapping("/v1/api/notifications")
    public List<Client> getNotifications() {
        return clients;
    }
}
