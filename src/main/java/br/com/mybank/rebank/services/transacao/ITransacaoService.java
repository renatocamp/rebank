package br.com.mybank.rebank.services.transacao;

import java.time.LocalDateTime;
import java.util.List;

import br.com.mybank.rebank.dto.ExtratoDTO;
import br.com.mybank.rebank.dto.PagamentoDTO;
import br.com.mybank.rebank.dto.TransferenciaDTO;
import br.com.mybank.rebank.model.Conta;
import br.com.mybank.rebank.model.Transacao;

public interface ITransacaoService {
	
	public boolean efetuarPagamento(PagamentoDTO pagamento);
	
	public boolean efetuarTransferencia(TransferenciaDTO transferencia);
	
	public List<Transacao> getExtrato(ExtratoDTO extrato);

}
