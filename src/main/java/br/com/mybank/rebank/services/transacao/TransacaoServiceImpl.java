package br.com.mybank.rebank.services.transacao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mybank.rebank.dto.ExtratoDTO;
import br.com.mybank.rebank.dto.PagamentoDTO;
import br.com.mybank.rebank.dto.TransferenciaDTO;
import br.com.mybank.rebank.exceptions.InvalidAccountException;
import br.com.mybank.rebank.exceptions.InvalidDateIntervalException;
import br.com.mybank.rebank.exceptions.NotEnoughBalanceException;
import br.com.mybank.rebank.model.Conta;
import br.com.mybank.rebank.model.Transacao;
import br.com.mybank.rebank.repositories.ContaRepository;
import br.com.mybank.rebank.repositories.TransacaoRepository;


@Service
public class TransacaoServiceImpl implements ITransacaoService{
	
	@Autowired
	private TransacaoRepository transacaoRepo;
	
	@Autowired
	private ContaRepository contaRepository;

	@Override
	public boolean efetuarPagamento(PagamentoDTO pagamento) {
		
		Conta conta = contaRepository.findById(pagamento.numeroConta()).orElse(null);
		if(conta == null) {
			throw new InvalidAccountException("Account " + pagamento.numeroConta()+ "does not exist!");
		}
		
		if(conta.getSaldo() + conta.getLimite() < pagamento.valor()) {
			throw new NotEnoughBalanceException("Not Enough Balance available for Account #" + pagamento.numeroConta());
		}
		Transacao transacao = new Transacao();
		
		Double saldoInicial, saldoFinal;
		saldoInicial = conta.getSaldo();
		saldoFinal = conta.getSaldo() - pagamento.valor();
		
		transacao.setConta(conta);
		transacao.setValor(pagamento.valor());
		transacao.setSaldoInicial(saldoInicial);
		transacao.setDataHora(pagamento.dataHora());
		transacao.setDescricao(pagamento.descricao());
		transacao.setNumDocumento(pagamento.numDoc());
		transacao.setTipo(-1);
		transacao.setSaldofinal(saldoFinal);
		
		transacaoRepo.save(transacao);
		
		conta.setSaldo(saldoFinal);
		
		contaRepository.save(conta);
		
		return true;
	}

	@Override
	public boolean efetuarTransferencia(TransferenciaDTO transferencia) {
		
		Conta contaOrigem;
		Conta contaDestino;
		
		contaOrigem = contaRepository.findById(transferencia.contaOrigem()).orElse(null);
		contaDestino = contaRepository.findById(transferencia.contaDestino()).orElse(null);
		
		if(contaOrigem == null ||contaDestino == null) {
			throw new InvalidAccountException("Invalid Source or Destination Account!");
		}
		
		if(contaOrigem.getSaldo() + contaOrigem.getLimite() < transferencia.valor()) {
			throw new NotEnoughBalanceException("Not Enough Balance for Account #" + contaOrigem.getNumeroConta());
		}
		
		Transacao transacaoDebito, transacaoCredito;
		
		Double saldoInicialOrigem, saldoInicialDestino, saldoFinalOrigem, saldoFinalDestino;
		
		saldoInicialOrigem = contaOrigem.getSaldo();
		saldoFinalOrigem = contaOrigem.getSaldo() - transferencia.valor();
		
		saldoInicialDestino = contaDestino.getSaldo();
		saldoFinalDestino = contaDestino.getSaldo() + transferencia.valor();
		
		transacaoDebito = new Transacao();
		transacaoDebito.setConta(contaOrigem);
		transacaoDebito.setDataHora(transferencia.dataHora());
		transacaoDebito.setValor(transferencia.valor());
		transacaoDebito.setSaldoInicial(saldoInicialOrigem);
		transacaoDebito.setSaldofinal(saldoFinalOrigem);
		transacaoDebito.setTipo(-1);
		transacaoDebito.setDescricao(transferencia.descricao());
		transacaoDebito.setNumDocumento(UUID.randomUUID().toString());
		
		transacaoCredito = new Transacao();
		transacaoCredito.setConta(contaDestino);
		transacaoCredito.setDataHora(transferencia.dataHora());
		transacaoCredito.setValor(transferencia.valor());
		transacaoCredito.setSaldoInicial(saldoInicialDestino);
		transacaoCredito.setSaldofinal(saldoFinalDestino);
		transacaoCredito.setTipo(1);
		transacaoCredito.setDescricao(transferencia.descricao());
		transacaoCredito.setNumDocumento(UUID.randomUUID().toString());
		
		transacaoRepo.save(transacaoDebito);
		transacaoRepo.save(transacaoCredito);
		
		contaOrigem.setSaldo(saldoFinalOrigem);
		contaDestino.setSaldo(saldoFinalDestino);
		
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
		
		return true;
	}

	@Override
	public List<Transacao> getExtrato(ExtratoDTO extrato) {
		
		Conta conta = contaRepository.findById(extrato.numeroConta()).orElse(null);
		
		if(conta == null) {
			throw new InvalidAccountException("Invalid Account number #"+ extrato.numeroConta());
		}
		
		if(extrato.inicio().isAfter(extrato.fim())) {
			throw new InvalidDateIntervalException("Invalid Date Interval");
		}
		return transacaoRepo.findByContaAndDataHoraBetween(conta, extrato.inicio(), extrato.fim());
	}

}
