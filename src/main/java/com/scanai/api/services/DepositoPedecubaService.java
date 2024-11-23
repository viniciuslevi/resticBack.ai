package com.scanai.api.services;

import com.scanai.api.domain.depositopedecuba.Depositopedecuba;
import com.scanai.api.domain.depositopedecuba.dto.DadosCadastroDepositoPeDeCuba;
import com.scanai.api.repositories.DepositoPedecubaRepository;
import com.scanai.api.repositories.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DepositoPedecubaService {

    @Autowired
    DepositoPedecubaRepository depositoPedecubaRepository;

    @Autowired
    DepositoRepository depositoRepository;

    public Depositopedecuba register(DadosCadastroDepositoPeDeCuba data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            System.out.println("AQUI");
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }

        var newDepositopedecuba = new Depositopedecuba(data);
        depositoPedecubaRepository.save(newDepositopedecuba);
        return newDepositopedecuba;
    }
}
