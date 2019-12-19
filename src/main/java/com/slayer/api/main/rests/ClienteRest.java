package com.slayer.api.main.rests;

import static com.slayer.api.main.util.ClientesAppConstantes.ROOT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.slayer.api.main.entities.Cliente;
import com.slayer.api.main.service.ClienteService;
import com.slayer.api.main.util.LogAbstract;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(ROOT + "/clientes")
public class ClienteRest extends LogAbstract{

	@Autowired
	private ClienteService service;

	@GetMapping
	@ResponseStatus(OK)
	public List<Cliente> listarClientes() {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return service.listarClientes();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public Cliente mostrarCliente(@PathVariable Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return service.mostrarCliente(id);
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return service.guardarCliente(cliente);
	}
	
	@PutMapping
	@ResponseStatus(CREATED)
	public Cliente actualizarCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return service.actualizarCliente(cliente, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void eliminarCliente(@PathVariable Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		service.eliminarCliente(id);		
	}

}
