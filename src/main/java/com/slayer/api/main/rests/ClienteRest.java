package com.slayer.api.main.rests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slayer.api.main.entities.Cliente;
import com.slayer.api.main.service.ClienteService;

import static com.slayer.api.main.util.ClientesAppConstantes.ROOT;

import java.util.List;;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(ROOT + "/clientes")
public class ClienteRest {

	@Autowired
	private ClienteService service;

	@GetMapping
	public List<Cliente> listarClientes() {
		return service.listarClientes();
	}

}
