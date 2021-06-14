package br.com.senai.domain.service;

import br.com.senai.api.assembler.EntregaAssembler;
import br.com.senai.api.model.EntregaModel;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.StatusEntrega;
import br.com.senai.domain.repository.EntregaRopository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private PessoaService pessoaService;
    private EntregaRopository entregaRopository;
    private EntregaAssembler entregaAssembler;

    public Entrega solicitar(Entrega entrega){
        Pessoa pessoa = pessoaService.buscar(entrega.getPessoa().getId());
        entrega.setPessoa(pessoa);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());
        return entregaRopository.save(entrega);
    }

    public List<EntregaModel> listar(){

        return entregaAssembler.toColletionModel(entregaRopository.findAll());
    }

    public ResponseEntity<EntregaModel> buscar(Long entregaId){
        return entregaRopository.findById(entregaId)
                .map(entrega -> {
             return ResponseEntity.ok(entregaAssembler.toModel(entrega));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
