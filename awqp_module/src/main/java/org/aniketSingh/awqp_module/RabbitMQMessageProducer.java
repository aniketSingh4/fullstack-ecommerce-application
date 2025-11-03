package org.aniketSingh.awqp_module;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer 
{
	@Autowired
    private AmqpTemplate amqpTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(RabbitMQMessageProducer.class);


    public void publish(Object payload, String exchange, String routingKey) 
    {
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    }

}
