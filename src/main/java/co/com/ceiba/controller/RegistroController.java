package co.com.ceiba.controller;

import java.net.URI;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.VigilanteException;

@RestController
public class RegistroController {

	@Autowired
	private Vigilante vigilante;
	
	@PostMapping("/ingresos")
	public ResponseEntity<Void> agregar(@RequestBody Ingreso nuevoIngreso) throws VigilanteException {
		Ingreso ingreso = vigilante.registrarIngreso(nuevoIngreso);

		if (ingreso == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ingreso.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/pagos/{placa}/fecha/{fecha}")
	public double indicarValorPorPagar(@PathVariable String placa, @PathVariable long fecha) throws VigilanteException {
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.setTimeInMillis(fecha);
		return this.vigilante.indicarValorPorPagar(placa, fechaSalida);
	}
}
