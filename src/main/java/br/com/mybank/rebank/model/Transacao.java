package br.com.mybank.rebank.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tbl_transacao")
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "num_seq")
	private Long numSeq;
	
	@Column(name = "data_hora", nullable = false)
	private LocalDateTime dataHora;
	
	@Column(name = "tipo")
	private Integer tipo;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "saldoInicial")
	private Double saldoInicial;
	
	@Column(name = "saldo_final")
	private Double saldofinal;
	
	@Column(name = "num_doc", length = 100)
	private String numDocumento;
	
	@Column(name = "descricao", length = 255)
	private String descricao;
	
	
	@ManyToOne
	@JoinColumn(name = "tbl_conta_numero_conta")
	private Conta conta;


	public Long getNumSeq() {
		return numSeq;
	}


	public void setNumSeq(Long numSeq) {
		this.numSeq = numSeq;
	}


	public LocalDateTime getDataHora() {
		return dataHora;
	}


	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}


	public Integer getTipo() {
		return tipo;
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Double getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public Double getSaldofinal() {
		return saldofinal;
	}


	public void setSaldofinal(Double saldofinal) {
		this.saldofinal = saldofinal;
	}


	public String getNumDocumento() {
		return numDocumento;
	}


	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Conta getConta() {
		return conta;
	}


	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	

}
