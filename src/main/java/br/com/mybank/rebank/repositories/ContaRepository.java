package br.com.mybank.rebank.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.mybank.rebank.model.Cliente;
import br.com.mybank.rebank.model.Conta;

public interface ContaRepository extends CrudRepository<Conta, Integer>{
	
	public List<Conta> findByCliente(Cliente cliente);

}
