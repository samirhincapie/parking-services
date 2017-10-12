package co.com.ceiba.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.com.ceiba.model.Ingreso;

@RestController
public class RegistroController {

	@Autowired
	private Vigilante vigilante;
	
	@PostMapping("/registros")
	public ResponseEntity<Void> agregar(@RequestBody Ingreso nuevoIngreso) throws Exception {
		Ingreso ingreso = vigilante.registrarIngreso(nuevoIngreso);

		if (ingreso == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ingreso.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
