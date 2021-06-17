package br.com.senai.api.model.input;

import br.com.senai.domain.model.Destinatario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {

    @Valid
    @NotNull
    private ClienteInput pessoa;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;

}