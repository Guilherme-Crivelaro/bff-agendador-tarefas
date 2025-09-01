package com.Guilherme.bffagendadortarefas.infrastructure.client;


import com.Guilherme.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.Guilherme.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization")String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                            @RequestHeader("Authorization")String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atuzalizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization")String token);
    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization")String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);

}