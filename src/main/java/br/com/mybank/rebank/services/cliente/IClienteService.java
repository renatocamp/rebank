package br.com.mybank.rebank.services.cliente;

import br.com.mybank.rebank.dto.ClienteDTO;

public interface IClienteService {
	
	public Integer cadastrarCliente(ClienteDTO novo);
	public Integer alterarDados(ClienteDTO cliente);

}
