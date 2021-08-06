package br.com.senai.api.assembler;

import br.com.senai.api.model.UsuarioDTO;
import br.com.senai.api.model.input.RoleUsuarioInputDTO;
import br.com.senai.api.model.input.UsuarioInputDOT;
import br.com.senai.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsuarioAssembler {
    ModelMapper modelMapper;

        public UsuarioDTO toModel(Usuario usuario){
            return modelMapper.map(usuario, UsuarioDTO.class);
        }

        public Usuario toEntity(UsuarioInputDOT usuarioInputDTO){
            return modelMapper.map(usuarioInputDTO, Usuario.class);
        }
    }


