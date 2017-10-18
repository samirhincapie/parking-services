package co.com.ceiba.test.controller.unitaria;

import static org.mockito.Matchers.any;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.com.ceiba.controller.RegistroController;
import co.com.ceiba.controller.Vigilante;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.service.RegistroService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RegistroController.class, secure = false)
public class RegistroControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RegistroService registroService;

	@MockBean
	private Vigilante vigilante;

	Calendar fecha = Calendar.getInstance();
	Ingreso mockIngreso = new Ingreso(fecha, new Moto("AAA00A", 125));

	
	String exampleIngresoJson = "{'fecha':"+fecha.getTimeInMillis()+",'vehiculo':{'placa':'AAA00A','cilindraje':125}}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(
				registroService.consultarIngreso(any(Vehiculo.class))
				).thenReturn(mockIngreso);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/registros/AAA00A").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("INI::< " + result.getResponse()+">::FIN");
		String expected = "{'id':0,'fecha':"+fecha.getTimeInMillis()+",'vehiculo':{'placa':'AAA00A','cilindraje':125}}";
		System.out.println("INI::< "+ expected+ " >::< " + result.getResponse().getContentAsString()+">::FIN");
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
