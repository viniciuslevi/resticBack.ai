package com.scanai.api.domain.deposito.dto;

import com.scanai.api.domain.deposito.Deposito;

public record DadosDetalhamentoDeposito(Long id, String tipo, String numero, boolean valid) {
    public DadosDetalhamentoDeposito(Deposito deposito) {
        this(
                deposito.getId(), deposito.getTipodeposito(), deposito.getNumerodeposito(), deposito.isValid()
        );
    }
}
