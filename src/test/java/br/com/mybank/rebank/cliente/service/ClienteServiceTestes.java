package br.com.mybank.rebank.cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.mybank.rebank.dto.ClienteDTO;
import br.com.mybank.rebank.services.cliente.IClienteService;
import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class ClienteServiceTestes {
	
	private ClienteDTO reqValida;
	private ClienteDTO reqInvalida;
	private Integer idValido;
	private ClienteDTO reqCpfDuplicado;
	private ClienteDTO reqTelefoneDuplicado;
	private ClienteDTO reqEmailDuplicado;
	
	//@Mock
	@Autowired
	private IClienteService service;
	
	@BeforeEach
	public void setup() {
		
		reqValida = new ClienteDTO("Cliente Validio", "renato@gmail.com", "12345678900", "19987654321", "rebankTeste");
		reqInvalida = new ClienteDTO("Cliente Invalido", null, null, null, null);
		reqCpfDuplicado = new ClienteDTO("Cliente Validio", "renato@outroemail.com", "00987654321", "19987654321", "12345");
		reqTelefoneDuplicado = new ClienteDTO("Cliente Validio", "renato@qualqueremail.com", "11987654123", "19987654321", "12345");
		reqEmailDuplicado = new ClienteDTO("Cliente Validio", "renato@gmail.com", "12345678900", "19987654321", "rebankTeste");
		idValido = 10;
		
		/*
		Mockito.when(service.cadastrarCliente(reqValida)).thenReturn(idValido);
		Mockito.when(service.cadastrarCliente(reqInvalida)).thenThrow(new ConstraintViolationException(null));
		Mockito.when(service.cadastrarCliente(reqCpfDuplicado)).thenReturn(null);
		Mockito.when(service.cadastrarCliente(reqEmailDuplicado)).thenReturn(null);
		Mockito.when(service.cadastrarCliente(reqTelefoneDuplicado)).thenReturn(null);
		
		*/
	}
	
	@Test
	public void deveCadastrarClienteValido() {
		assertNotNull(service.cadastrarCliente(reqValida));
	}
	
	@Test
	public void deveRejeitarClienteInvalido() {
		assertThrows(DataIntegrityViolationException.class, () -> {
			service.cadastrarCliente(reqInvalida);
		});
	}
	
	@Test
	public void deveRejeitarClienteCpfDuplicado() {
		assertEquals(service.cadastrarCliente(reqCpfDuplicado), null);
	}
	
	@Test
	public void deveRejeitarClienteTelefoneDuplicado() {
		assertEquals(service.cadastrarCliente(reqTelefoneDuplicado), null);
	}
	
	@Test
	public void deveRejeitarClienteEmailDuplicado() {
		assertEquals(service.cadastrarCliente(reqEmailDuplicado), null);
	}

}
