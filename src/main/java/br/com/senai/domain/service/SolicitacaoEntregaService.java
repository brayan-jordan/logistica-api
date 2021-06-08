package br.com.senai.domain.service;

import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.StatusEntrega;
import br.com.senai.domain.repository.EntregaRopository;
import br.com.senai.domain.repository.PessoaRepository;
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

    public Entrega solicitar(Entrega entrega){
        Pessoa pessoa = pessoaService.buscar(entrega.getPessoa().getId());
        entrega.setPessoa(pessoa);

        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRopository.save(entrega);
    }

    public List<Entrega> listar(){
        return entregaRopository.findAll();
    }

    public ResponseEntity<Entrega> buscar(Long entregaId){
        return entregaRopository.findById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
