package br.com.mybank.rebank.services.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mybank.rebank.dto.ClienteDTO;
import br.com.mybank.rebank.model.Cliente;
import br.com.mybank.rebank.repositories.ClienteRepository;
import jakarta.validation.Valid;


@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private ClienteRepository repo;

	@Override
	public Integer cadastrarCliente(@Valid ClienteDTO novo) {
		
		Cliente teste = repo.findByEmailOrCpfOrTelefone(novo.email(), novo.cpf(), novo.telefone());
		
		if(teste != null) {
			return null;
		}
		
		return repo.save(novo.toCliente()).getIdCliente();
	}

	@Override
	public Integer alterarDados(@Valid ClienteDTO cliente) {

		return repo.save(cliente.toCliente()).getIdCliente();
	}

}
