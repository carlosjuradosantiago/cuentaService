package com.devsu.clienteservice;

import com.devsu.clienteservice.dto.ClienteDTO;
import com.devsu.clienteservice.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long createdClienteId;

    @BeforeEach
    public void setup() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Carlos Jurado");
        clienteDTO.setGenero("Masculino");
        clienteDTO.setEdad(30);
        clienteDTO.setIdentificacion("0987654321");
        clienteDTO.setDireccion("Av. Siempre Viva 742");
        clienteDTO.setTelefono("1234567890");
        clienteDTO.setContraseña("password");
        clienteDTO.setEstado(true);


        MvcResult result = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos Jurado"))
                .andReturn();


        String responseContent = result.getResponse().getContentAsString();
        ClienteDTO createdCliente = objectMapper.readValue(responseContent, ClienteDTO.class);
        createdClienteId = createdCliente.getId();
    }

    @AfterEach
    public void cleanUp() {
        clienteRepository.deleteById(createdClienteId);
    }

    @Test
    public void testGetClienteById() throws Exception {
        mockMvc.perform(get("/clientes/" + createdClienteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos Jurado"));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Carlos Jurado Updated");
        clienteDTO.setGenero("Masculino");
        clienteDTO.setEdad(31);
        clienteDTO.setIdentificacion("0987654321");
        clienteDTO.setDireccion("Av. Siempre Viva 742");
        clienteDTO.setTelefono("1234567890");
        clienteDTO.setContraseña("newpassword");
        clienteDTO.setEstado(true);

        mockMvc.perform(put("/clientes/" + createdClienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos Jurado Updated"));

        mockMvc.perform(get("/clientes/" + createdClienteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos Jurado Updated"))
                .andExpect(jsonPath("$.edad").value(31));
    }

    @Test
    public void testPatchCliente() throws Exception {
        mockMvc.perform(patch("/clientes/" + createdClienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"edad\": 35}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.edad").value(35));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/clientes/" + createdClienteId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/clientes/" + createdClienteId))
                .andExpect(status().isNotFound());
    }
}
