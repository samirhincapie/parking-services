package co.com.ceiba.test.controller.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.com.ceiba.controller.RegistroController;
import co.com.ceiba.controller.Vigilante;
import co.com.ceiba.model.Ingreso;
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
	
	@Before
	public void setup(){
	}


	@Test
	public void vigilanteIndicaValorPorPagarIT() throws Exception {
		//Arrange
		String esperado = String.valueOf(5000d);
		String mockFechaLong = String.valueOf(1000l);
		String mockPlaca = "AAA00A";
		
		when(vigilante.indicarValorPorPagar(anyString(), any(Calendar.class)))
		.thenReturn(5000d);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/pagos/"+mockPlaca+"/fecha/"+mockFechaLong)
				.accept(MediaType.APPLICATION_JSON);
		
		
		//Act
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		//Assert
		JSONAssert.assertEquals(esperado, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void Test1() throws Exception {
		//Arrange
		String exampleIngresoJson = "{'fecha':1000, 'vehiculo':{'placa':'AAA000'}}";
		
		Ingreso mockIngreso = mock(Ingreso.class);

		when(this.vigilante.registrarIngreso(mockIngreso))
		.thenReturn(mockIngreso);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/ingresos")
				.accept(MediaType.APPLICATION_JSON).content(exampleIngresoJson)
				.contentType(MediaType.APPLICATION_JSON);

		//Act
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		//Assert
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/ingresos/1", response
				.getHeader(HttpHeaders.LOCATION));
	}
}
