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

import java.util.ArrayList;
import java.util.List;


@RestController
@Service
public class RedisMessageSubscriber implements MessageListener {

    List<Client> clients = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        Gson gson = new Gson();
        Client client = gson.fromJson(message.toString(), Client.class);
        Client client1 = new Client(
                client.getClient_name(),
                client.getClient_address(),
                client.getClient_telephone(),
                client.getClient_email()
        );
        clients.add(client1);
    }

    @ApiOperation("Get All Notification Published By Customer Service")
    @GetMapping("/v1/api/notifications")
    public List<Client> getNotifications() {
        return clients;
    }
}
