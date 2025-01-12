package br.com.mybank.rebank.cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.mybank.rebank.dto.ClienteDTO;
import br.com.mybank.rebank.services.cliente.IClienteService;

@SpringBootTest
public class ClienteServiceTestes {
	
	private ClienteDTO reqValida;
	private ClienteDTO reqInvalida;
	private Long idValidio;
	
	@Mock
	private IClienteService service;
	
	@BeforeEach
	public void setup() {
		
		reqValida = new ClienteDTO("Cliente Validio", "renato@gmail.com", "12345678900", "19987654321", "rebankTeste");
		reqInvalida = new ClienteDTO("Cliente Invalido", null, null, null, null);
		idValidio = (long) 10;
		
		Mockito.when(service.cadastrarCliente(reqValida)).thenReturn(idValidio);
		Mockito.when(service.cadastrarCliente(reqInvalida)).thenReturn(null);
	}
	
	@Test
	public void deveCadastrarClienteValido() {
		assertEquals(service.cadastrarCliente(reqValida), idValidio);
	}
	
	@Test
	public void deveRejeitarClienteInvalido() {
		assertEquals(service.cadastrarCliente(reqInvalida), null);
	}

}
