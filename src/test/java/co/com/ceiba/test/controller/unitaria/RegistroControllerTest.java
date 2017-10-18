package co.com.ceiba.test.controller.unitaria;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
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


	@Test
	public void retrieveDetailsForCourse() throws Exception {
		//Arrange
		String esperadoJson = String.valueOf(5000d);
		String mockFechaLong = String.valueOf(1000l);
		String mockPlaca = "AAA00A";
		
		when(vigilante.indicarValorPorPagar(anyString(), any(Calendar.class)))
		.thenReturn(5000d);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				//.get("/pagos/AAA00A/fecha/125")
				.get("/pagos/"+mockPlaca+"/fecha/"+mockFechaLong)
				.accept(MediaType.APPLICATION_JSON);
		
		System.out.println(requestBuilder);
		
		//Act
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		
		//Assert
		JSONAssert.assertEquals(esperadoJson, result.getResponse()
				.getContentAsString(), false);
	}
}
