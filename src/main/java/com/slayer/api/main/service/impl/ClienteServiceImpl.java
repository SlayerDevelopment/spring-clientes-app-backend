package com.slayer.api.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slayer.api.main.daos.ClienteDao;
import com.slayer.api.main.entities.Cliente;
import com.slayer.api.main.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		return dao.findAll();
	}

}
