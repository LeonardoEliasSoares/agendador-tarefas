package com.leonardosoares.agendadortarefas.business;

import com.leonardosoares.agendadortarefas.business.dto.TarefasDTO;
import com.leonardosoares.agendadortarefas.business.mapper.TarefaConverter;
import com.leonardosoares.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.leonardosoares.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.leonardosoares.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.leonardosoares.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity tarefasEntity = tarefaConverter.paraTarefasEntity(dto);

        return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));

    }

    public List<TarefasDTO> listarTarefasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }


}
