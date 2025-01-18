package br.com.mybank.rebank.cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.mybank.rebank.dto.ContaDTO;
import br.com.mybank.rebank.services.conta.IContaService;

@SpringBootTest
public class ContaServiceTestes {
	
//	@Mock
	@Autowired
	private IContaService contaService;
	
	public ContaDTO contaValida;
	public ContaDTO contaInvalida;
	
	@BeforeEach
	public void setup() {
		
		contaValida = new ContaDTO(1, 1, 100.0, 0.0, 10);
		contaInvalida = new ContaDTO(1, 1, 100.0, 0.0, 2);
		
//		Mockito.when(contaService.cadastrarNovaConta(contaValida)).thenReturn(1);
//		Mockito.when(contaService.cadastrarNovaConta(contaInvalida)).thenReturn(null);
	}
	
	@Test
	public void deveCadastrarContaComClienteExistente() {
		assertNotNull(contaService.cadastrarNovaConta(contaValida));
	}
	
	public void deveRejeitarContaComClienteInvalido() {
		assertEquals(contaService.cadastrarNovaConta(contaInvalida), null);
	}

}
