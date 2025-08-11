package com.Guilherme.bffagendadortarefas.business;

import com.Guilherme.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.Guilherme.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.Guilherme.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefasDaProximaHora(){
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefasDTOResponse> listaDeTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas "+listaDeTarefas);
        listaDeTarefas.forEach(tarefa -> {emailService.enviaEmail(tarefa);
            log.info("email enviado para o usuario"+tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);});
        log.info("finalizada a busca e notificação de tarefas");
    }


    public String login(LoginRequestDTO login){
        return usuarioService.loginUsuario(login);
    }

    public LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
