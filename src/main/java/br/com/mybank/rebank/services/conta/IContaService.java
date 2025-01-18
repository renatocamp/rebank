package br.com.mybank.rebank.services.conta;

import java.util.List;

import br.com.mybank.rebank.dto.ContaDTO;
import br.com.mybank.rebank.model.Cliente;
import br.com.mybank.rebank.model.Conta;

public interface IContaService {
	
	public Integer cadastrarNovaConta(ContaDTO nova);
	public List<Conta> recuperarPeloCliente(Cliente cliente);
	public Conta recuperarPeloNumero(Integer numeroConta);

}
