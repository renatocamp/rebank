package br.com.mybank.rebank.services.conta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import br.com.mybank.rebank.dto.ContaDTO;
import br.com.mybank.rebank.model.Cliente;
import br.com.mybank.rebank.model.Conta;
import br.com.mybank.rebank.repositories.ClienteRepository;
import br.com.mybank.rebank.repositories.ContaRepository;

public class ContaServiceImpl implements IContaService{
	
	@Value("${rebank.banknumber}")
	private Integer numeroBanco;
	
	
	@Autowired
	private ContaRepository contaRepo;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Integer cadastrarNovaConta(ContaDTO nova) {
		
		Cliente cliente = clienteRepository.findById(nova.idCliente()).orElse(null);
		
		if(cliente == null) {
			return null;
		}
		Conta conta = nova.toConta();
		conta.setNumeroBanco(numeroBanco);
		System.out.println(numeroBanco);
		
		return contaRepo.save(conta).getNumeroConta();
	}

	@Override
	public List<Conta> recuperarPeloCliente(Cliente cliente) {
		
		return contaRepo.findByCliente(cliente);
	}

	@Override
	public Conta recuperarPeloNumero(Integer numeroConta) {
		
		return contaRepo.findById(numeroConta).orElse(null);
	}

}
	