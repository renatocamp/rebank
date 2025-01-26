package br.com.mybank.rebank.dto;

import java.time.LocalDateTime;

public record ExtratoDTO(Integer numeroConta, LocalDateTime inicio, LocalDateTime fim) {

}
