package com.Guilherme.bffagendadortarefas.controller;


import com.Guilherme.bffagendadortarefas.business.TarefasService;
import com.Guilherme.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.Guilherme.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.Guilherme.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.Guilherme.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra Tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefa de usuario", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaDeListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email de usuario",
            description = "Busca tarefas cadastradas por usuario")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por id", description = "Deleta tarefas cadastradas por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletadas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletaTarefaPorID(@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }


    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da Tarefa alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Dados da Tarefa alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor ")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));

    }

}
