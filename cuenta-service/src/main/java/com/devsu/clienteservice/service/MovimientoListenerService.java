package com.devsu.clienteservice.service;

import com.devsu.clienteservice.config.RabbitMQConfig;
import com.devsu.clienteservice.dto.MovimientoDTO;
import com.devsu.clienteservice.repository.ClienteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoListenerService {

    @Autowired
    private ClienteRepository clienteRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleMovimiento(MovimientoDTO movimiento) {

    }
}
