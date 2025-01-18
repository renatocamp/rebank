package br.com.mybank.rebank.dto;

import br.com.mybank.rebank.model.Cliente;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ClienteDTO(@NotNull String nome, 
						 @NotNull String email, 
						 @NotNull String cpf, 
						 @NotNull @Min(11) String telefone, 
						 @NotNull @Min(8) String senha) {
	
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
