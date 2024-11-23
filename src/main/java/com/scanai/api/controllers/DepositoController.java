package com.scanai.api.controllers;

import com.scanai.api.domain.deposito.Deposito;
import com.scanai.api.domain.deposito.dto.*;
import com.scanai.api.repositories.DepositoRepository;
import com.scanai.api.services.DepositoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private DepositoService depositoService;

    @PostMapping("/register")
    public ResponseEntity<DadosDetalhamentoDeposito> register(@RequestBody @Valid DadosCadastroDeposito data, UriComponentsBuilder uriBuilder){
        Deposito newDeposito = depositoService.register(data);
        var uri = uriBuilder.path("deposito/register/{id}").buildAndExpand(newDeposito.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDeposito(newDeposito));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDeposito> update(@RequestBody @Valid DadosAtualizarDeposito data){
        Deposito deposito = depositoService.update(data);
        return ResponseEntity.ok().body(new DadosDetalhamentoDeposito(deposito));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosListagemDeposito>> getAll(){
        return ResponseEntity.ok().body(depositoRepository.findAllByValidTrue().stream().map(DadosListagemDeposito::new).toList());
    }

    @PutMapping("/softDelete/{id}")
    @Transactional
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        depositoService.softDelete(depositoRepository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activate/{id}")
    @Transactional
    public ResponseEntity<?>activate(@PathVariable Long id){
        depositoService.activate(depositoRepository.getReferenceById(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getDepositoWithIdWithInformations/{id}")
    public ResponseEntity<DadosInformacoesDepositos> getDepositoWithIdWithInformations(@PathVariable Long id){
        return ResponseEntity.ok().body(depositoService.getDepositoWithIdWithInformations(id));

    }

    @GetMapping("/getAllDepositosWithInformations")
    public ResponseEntity<List<DadosInformacoesDepositos>> getAllDepositosWithInformations(){
        return ResponseEntity.ok().body(depositoService.getAllDepositosWithInformations());
    }

}
