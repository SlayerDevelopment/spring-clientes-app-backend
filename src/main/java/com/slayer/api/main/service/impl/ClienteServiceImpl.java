package com.slayer.api.main.service.impl;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slayer.api.main.daos.ClienteDao;
import com.slayer.api.main.entities.Cliente;
import com.slayer.api.main.service.ClienteService;
import com.slayer.api.main.util.LogAbstract;

@Service
public class ClienteServiceImpl extends LogAbstract implements ClienteService {

	@Autowired
	private ClienteDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente mostrarCliente(Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente guardarCliente(Cliente cliente) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return dao.save(cliente);
	}

	@Override
	@Transactional
	public void eliminarCliente(Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public ResponseEntity<?> actualizarCliente(Cliente cliente, Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente clienteActualizado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clienteActualizado = mostrarCliente(id);
		} catch (Exception e) {
			response.put("mensaje", "El cliente con el id: ".concat(id.toString()).concat(" " + "no existe"));
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		try {
			clienteActualizado.setEmail(cliente.getEmail());
			clienteActualizado.setApellidos(cliente.getApellidos());
			clienteActualizado.setNombres(cliente.getNombres());
			dao.save(clienteActualizado);
		} catch (Exception e) {
			response.put("mensaje", "Error al realizar la actualizacion al cliente con id: " + cliente.getId());
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Exito al realizar la actualizacion");
		response.put("cliente", clienteActualizado);
		return new ResponseEntity<Map<String, Object>>(response, OK);
	}

}
