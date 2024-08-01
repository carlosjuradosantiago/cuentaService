package com.devsu.clienteservice.service;

import com.devsu.clienteservice.dto.ClienteDTO;
import com.devsu.clienteservice.entity.Cliente;
import com.devsu.clienteservice.mapper.ClienteMapper;
import com.devsu.clienteservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOs(clientes);
    }

    public ClienteDTO getClienteById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(clienteMapper::toDTO).orElseThrow(() -> new IllegalArgumentException("Cliente not found with id " + id));
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(savedCliente);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> existingCliente = clienteRepository.findById(id);
        if (existingCliente.isPresent()) {
            Cliente cliente = existingCliente.get();
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setGenero(clienteDTO.getGenero());
            cliente.setEdad(clienteDTO.getEdad());
            cliente.setIdentificacion(clienteDTO.getIdentificacion());
            cliente.setDireccion(clienteDTO.getDireccion());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setContraseña(clienteDTO.getContraseña());
            cliente.setEstado(clienteDTO.isEstado());

            Cliente updatedCliente = clienteRepository.save(cliente);
            return clienteMapper.toDTO(updatedCliente);
        }
        throw new IllegalArgumentException("Cliente not found with id " + id);
    }

    public ClienteDTO patchCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> existingCliente = clienteRepository.findById(id);
        if (existingCliente.isPresent()) {
            Cliente cliente = existingCliente.get();
            if (clienteDTO.getNombre() != null) cliente.setNombre(clienteDTO.getNombre());
            if (clienteDTO.getGenero() != null) cliente.setGenero(clienteDTO.getGenero());
            if (clienteDTO.getEdad() != 0) cliente.setEdad(clienteDTO.getEdad());
            if (clienteDTO.getIdentificacion() != null) cliente.setIdentificacion(clienteDTO.getIdentificacion());
            if (clienteDTO.getDireccion() != null) cliente.setDireccion(clienteDTO.getDireccion());
            if (clienteDTO.getTelefono() != null) cliente.setTelefono(clienteDTO.getTelefono());
            if (clienteDTO.getContraseña() != null) cliente.setContraseña(clienteDTO.getContraseña());
            cliente.setEstado(clienteDTO.isEstado());

            Cliente updatedCliente = clienteRepository.save(cliente);
            return clienteMapper.toDTO(updatedCliente);
        }
        throw new IllegalArgumentException("Cliente not found with id " + id);
    }

    public void deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cliente not found with id " + id);
        }
    }
}
