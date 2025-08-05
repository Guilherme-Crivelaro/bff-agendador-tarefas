package com.Guilherme.bffagendadortarefas.controller;


import com.Guilherme.bffagendadortarefas.business.UsuarioService;
import com.Guilherme.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.Guilherme.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.Guilherme.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.Guilherme.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "usuario", description = "Cadastro e login e usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuario", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.saveUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuario", description = "Login do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado")
    @ApiResponse(responseCode = "401", description = "Credenciais Invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }


    @GetMapping
    @Operation(summary = "Buscar dados de Usuario por Email", description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }


    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuario por id", description = "Deleta usuario")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization")String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de Usuario", description = "Atualizar dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário Não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                   @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza enderaço de Usuario", description = "Atualiza enderaço de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<EnderecoDTOResponse> atuzalizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader("Authorization")String token){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de Usuario", description = "Atualiza telefone de usuario")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salvar endereço Usuario", description = "Salva endereço de usuario")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario Não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salvar Telefone Usuario", description = "Salva telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

}

























