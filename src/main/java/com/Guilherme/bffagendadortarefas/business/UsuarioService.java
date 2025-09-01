package com.Guilherme.bffagendadortarefas.business;


import com.Guilherme.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.Guilherme.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import com.Guilherme.bffagendadortarefas.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;



    public UsuarioDTOResponse saveUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
       return usuarioClient.atualizaDadosUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long id, EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.atuzalizaEndereco(enderecoDTO, id, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long id, TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.atualizaTelefone(telefoneDTO, id, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO){
        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO){
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }

    public ViaCepDTOResponse buscarEnderecoPorCep(String cep){
        return usuarioClient.buscarDadosCep(cep);
    }

}
