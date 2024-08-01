package com.devsu.clienteservice.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String genero;

    @Min(value = 0, message = "Edad must be greater than or equal to 0")
    private int edad;

    private String identificacion;
    private String direccion;
    private String telefono;
    private String contraseña;
    private boolean estado;
}
