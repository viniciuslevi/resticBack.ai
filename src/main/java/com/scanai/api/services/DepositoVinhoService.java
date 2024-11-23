package com.scanai.api.services;

import com.scanai.api.domain.depositovinho.Depositovinho;
import com.scanai.api.domain.depositovinho.dto.DadosCadastroDepositoVinho;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.repositories.DepositoVinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DepositoVinhoService {

    @Autowired
    DepositoVinhoRepository depositoVinhoRepository;

    @Autowired
    DepositoRepository depositoRepository;

    public Depositovinho register(DadosCadastroDepositoVinho data) {
        if(depositoRepository.existsVinhoAtivo(data.fkdeposito()) != null || depositoRepository.existsPeDeCubaAtivo(data.fkdeposito()) != null || depositoRepository.existsMostroAtivo(data.fkdeposito()) != null){
            throw new DataIntegrityViolationException("Impossível inserir, o deposito já contém outro produto ativo");
        }
        var newDepositovinho = new Depositovinho(data);
        depositoVinhoRepository.save(newDepositovinho);
        return newDepositovinho;
    }
}
