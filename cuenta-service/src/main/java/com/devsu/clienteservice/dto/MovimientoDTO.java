package com.devsu.clienteservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoDTO {

    private Long id;

    private Double monto;

    private String tipo;

    private Long cuentaId;

    private LocalDateTime fecha;
}
