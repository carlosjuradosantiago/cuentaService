package com.devsu.clienteservice;

import com.devsu.clienteservice.dto.ClienteDTO;
import com.devsu.clienteservice.entity.Cliente;
import com.devsu.clienteservice.mapper.ClienteMapper;
import com.devsu.clienteservice.repository.ClienteRepository;
import com.devsu.clienteservice.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteMapper clienteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Carlos Jurado");

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNombre("Carlos Jurado");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO result = clienteService.getClienteById(1L);
        assertNotNull(result);
        assertEquals("Carlos Jurado", result.getNombre());
    }

    @Test
    void testCreateCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Carlos Jurado");

        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos Jurado");

        when(clienteMapper.toEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO result = clienteService.createCliente(clienteDTO);
        assertNotNull(result);
        assertEquals("Carlos Jurado", result.getNombre());
    }

    @Test
    void testUpdateCliente() {
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Carlos Jurado Updated");

        Cliente existingCliente = new Cliente();
        existingCliente.setId(id);
        existingCliente.setNombre("Carlos Jurado");

        Cliente updatedCliente = new Cliente();
        updatedCliente.setId(id);
        updatedCliente.setNombre("Carlos Jurado Updated");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(existingCliente));
        when(clienteMapper.toEntity(clienteDTO)).thenReturn(updatedCliente);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(updatedCliente);
        when(clienteMapper.toDTO(updatedCliente)).thenReturn(clienteDTO);

        ClienteDTO result = clienteService.updateCliente(id, clienteDTO);
        assertNotNull(result);
        assertEquals("Carlos Jurado Updated", result.getNombre());
    }

    @Test
    void testDeleteCliente() {
        Long id = 1L;
        doNothing().when(clienteRepository).deleteById(id);

        clienteService.deleteCliente(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }
}
