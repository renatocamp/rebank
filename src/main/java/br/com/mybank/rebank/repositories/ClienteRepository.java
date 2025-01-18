package br.com.mybank.rebank.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.mybank.rebank.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
	
	public Cliente findByEmailOrCpfOrTelefone(String email, String cpf, String Telefone);

}
