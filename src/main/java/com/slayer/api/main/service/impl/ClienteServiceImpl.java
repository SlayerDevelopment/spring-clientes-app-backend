package com.slayer.api.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Cliente actualizarCliente(Cliente cliente, Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente clienteBuscado = mostrarCliente(id);
		clienteBuscado.setEmail(cliente.getEmail());
		clienteBuscado.setApellidos(cliente.getApellidos());
		clienteBuscado.setNombres(cliente.getNombres());
		return dao.save(cliente);
	}

}
