package com.leonardosoares.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardosoares.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTO {
    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape =  JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape =  JsonFormat.Shape.STRING)
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape =  JsonFormat.Shape.STRING)
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
