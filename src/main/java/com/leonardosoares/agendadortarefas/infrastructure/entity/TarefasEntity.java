package com.leonardosoares.agendadortarefas.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardosoares.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("tarefa")
public class TarefasEntity {
    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape =  JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape =  JsonFormat.Shape.STRING)
    private LocalDate dataEvento;
    private String emailUsuario;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape =  JsonFormat.Shape.STRING)
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
