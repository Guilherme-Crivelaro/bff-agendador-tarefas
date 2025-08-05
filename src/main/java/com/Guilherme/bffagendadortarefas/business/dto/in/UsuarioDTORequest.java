package com.Guilherme.bffagendadortarefas.business.dto.in;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {

    private String nome;
    private List<EnderecoDTORequest> enderecos;
    private List<TelefoneDTORequest> telefones;

}
