package com.powerledger.powerplant.controller;

import com.powerledger.powerplant.component.ServiceResponse;
import com.powerledger.powerplant.config.RabbitMQConfig;
import com.powerledger.powerplant.dto.BatteryDTO;
import com.powerledger.powerplant.exception.DataNotFoundException;
import com.powerledger.powerplant.service.BatteryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/battery")
@RequiredArgsConstructor
@Slf4j
public class BatteryController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    private final BatteryService batteryService;

    @PostMapping("/add")
    public ResponseEntity<ServiceResponse> createBatteries(@Valid @RequestBody List<BatteryDTO> batteryDTOs) {
        try {
            batteryService.createBatteries(batteryDTOs);
            return new ResponseEntity<>(new ServiceResponse("Data insertion successful", ""), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exception happened on creation");
            return new ResponseEntity<>(new ServiceResponse("Unable to save input", ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-batteries")
    public ResponseEntity<ServiceResponse> getBatteriesByPostCodeRange(@RequestParam @NotBlank String lowerRange, @RequestParam @NotBlank String upperRange) {
        try{
            Map<String, Object> data = batteryService.getListByRange(lowerRange, upperRange);
            return new ResponseEntity<>(new ServiceResponse("data found", data), HttpStatus.OK);
        } catch (DataNotFoundException dnfe) {
            throw dnfe;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ServiceResponse("Unable to get Battery information", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // implementation of rabbitmq listener
    @RabbitListener(queues = RabbitMQConfig.RPC_MESSAGE_QUEUE)
    public void process(Message message) {
        byte[] body = message.getBody();
        log.info("server "+new String(body));
        //This is the message to be returned by the server
        String publisherMessage = new String(body);

        // to resend to the publisher again for rpc call
        /*Message build = MessageBuilder.withBody(("I am the rpc server, I received the message from the client：" + new String(body)).getBytes()).build();
        final String correlationId = message.getMessageProperties().getCorrelationId();
        rabbitTemplate.convertAndSend(RabbitMqConfig.RPC_REPLY_EXCHANGE, RabbitMqConfig.RPC_REPLY_MESSAGE_QUEUE, build, m -> {
            m.getMessageProperties().setCorrelationId(correlationId);
            return m;
        });*/
    }

}
