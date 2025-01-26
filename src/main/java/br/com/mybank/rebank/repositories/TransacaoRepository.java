package br.com.mybank.rebank.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.mybank.rebank.model.Conta;
import br.com.mybank.rebank.model.Transacao;

public interface TransacaoRepository extends CrudRepository<Transacao, Long>{
	
	public List<Transacao> findByContaAndDataHoraBetween(Conta conta, LocalDateTime inicio, LocalDateTime fim);

}
