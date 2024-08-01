package com.devsu.clienteservice.mapper;

import com.devsu.clienteservice.dto.ClienteDTO;
import com.devsu.clienteservice.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOs(List<Cliente> clientes);
    @Mapping(target = "clienteId", ignore = true)
    void updateClienteFromDTO(ClienteDTO clienteDTO, @MappingTarget Cliente cliente);


}
