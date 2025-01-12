package br.com.mybank.rebank.services.cliente;

import br.com.mybank.rebank.dto.ClienteDTO;

public interface IClienteService {
	
	public Long cadastrarCliente(ClienteDTO novo);
	public Long alterarDados(ClienteDTO cliente);

}
