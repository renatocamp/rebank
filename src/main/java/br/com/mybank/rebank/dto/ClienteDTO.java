package br.com.mybank.rebank.dto;

import br.com.mybank.rebank.model.Cliente;

public record ClienteDTO(String nome, String email, String cpf, String telefone, String senha) {
	
	//CONVERSOR
	public Cliente toCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setSenha(senha);
		
		return cliente;
	}

}
