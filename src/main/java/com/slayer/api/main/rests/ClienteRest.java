package com.slayer.api.main.rests;

import static com.slayer.api.main.util.ClientesAppConstantes.ROOT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class ClienteRest extends LogAbstract {

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
	public ResponseEntity<?> mostrarCliente(@PathVariable("id") Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente clienteBuscado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clienteBuscado = service.mostrarCliente(id);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, NOT_FOUND);
		}
		if (clienteBuscado == null) {
			response.put("mensaje", "El cliente con el id: ".concat(id.toString()).concat(" " + "no existe"));
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cliente>(clienteBuscado, OK);
	}

	@PostMapping
	public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente clienteNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clienteNuevo = service.guardarCliente(cliente);
		} catch (Exception e) {
			response.put("mensaje", "Error al realizar la insercion");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Exito al realizar la insercion");
		response.put("cliente", clienteNuevo);
		return new ResponseEntity<Map<String, Object>>(response, CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return service.actualizarCliente(cliente, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
		LOG.info("Ejecutando el siguiente Metodo de la Clase: " + getClass().getName() + " - "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Map<String, Object> response = new HashMap<>();
		try {
			service.eliminarCliente(id);
		} catch (Exception e) {
			response.put("mensaje", "Error al eliminar el registro");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Exito al eliminar el registro");
		return new ResponseEntity<Map<String, Object>>(response, OK);
	}

}
