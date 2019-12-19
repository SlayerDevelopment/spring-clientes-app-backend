package com.slayer.api.main.service;

import java.util.List;

import com.slayer.api.main.entities.Cliente;

public interface ClienteService {

	List<Cliente> listarClientes();
	Cliente mostrarCliente(Long id);
	Cliente guardarCliente(Cliente cliente);
	Cliente actualizarCliente(Cliente cliente, Long id);
	void eliminarCliente(Long id);
}
