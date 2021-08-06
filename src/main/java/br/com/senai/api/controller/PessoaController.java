package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.assembler.UsuarioAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.RoleUsuarioDTO;
import br.com.senai.api.model.UsuarioDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.api.model.input.UsuarioInputDOT;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.RoleUsuario;
import br.com.senai.domain.model.Usuario;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;
    private UsuarioAssembler usuarioAssembler;

    @GetMapping
    public List<PessoaDTO> listar(){
        return pessoaAssembler.toCollectionModel(pessoaService.listar());
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaDTO> listarPorNome(@PathVariable String pessoaNome){
        return pessoaService.buscarNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<PessoaDTO> listarNomeContaining(@PathVariable String nomeContaining){
        return pessoaService.listarContaining(nomeContaining);
    }

    @GetMapping("{pessoaId}")
    public ResponseEntity<PessoaDTO> buscarId(@PathVariable Long pessoaId){
        return pessoaService.buscarId(pessoaId);
    }

    @PostMapping
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        novaPessoa.getUsuario().setSenha(new BCryptPasswordEncoder()
                .encode(pessoaInputDTO.getUsuario().getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);
        return pessoaAssembler.toModel(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long pessoaId,
                                            @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa pessoa1 = pessoaAssembler.toEntity(pessoaInputDTO);
        pessoaService.editar(pessoaId,pessoa1);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa1));
    }

        @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.notFound().build();
        }
        pessoaService.deletar(pessoaId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/usuario")
    public PessoaDTO cadUsuario(@Valid @RequestBody UsuarioInputDOT usuarioInputDOT) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInputDOT);
        Pessoa newPessoa = new Pessoa();
        newPessoa.setUsuario(usuario);
        newPessoa.setNome("Teste");
        newPessoa.setTelefone("(47)77777-7777");

        newPessoa.getUsuario().setSenha(new BCryptPasswordEncoder()
                .encode(usuarioInputDOT.getSenha()));

        Pessoa pessoa = pessoaService.cadastrar(newPessoa);

        return pessoaAssembler.toModel(pessoa);
    }


}
