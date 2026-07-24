package com.leonardosoares.agendadortarefas.business;

import com.leonardosoares.agendadortarefas.business.dto.TarefasDTO;
import com.leonardosoares.agendadortarefas.business.mapper.TarefaConverter;
import com.leonardosoares.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.leonardosoares.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.leonardosoares.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.leonardosoares.agendadortarefas.infrastructure.exceptions.ResourceExceptions;
import com.leonardosoares.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.leonardosoares.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity tarefasEntity = tarefaConverter.paraTarefasEntity(dto);

        return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));

    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return tarefaConverter.paraTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return tarefaConverter.paraTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicio, dataFim));
    }

    public TarefasDTO updateTarefa(String id, TarefasDTO dto) {
        try {

            TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceExceptions("Id não encontrado " + id)
            );

            tarefaUpdateConverter.tarefaUpdateConverter(dto, tarefasEntity);
            return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
        } catch (ResourceExceptions e) {
            throw new ResourceExceptions("Erro ao alterar a tarefa " + e.getCause());
        }
    }

    public void deletarTarefa(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceExceptions e) {
            throw new ResourceExceptions("Id não encontrado " + e.getMessage());
        }

    }

    public TarefasDTO alteraStatus(String id, StatusNotificacaoEnum statusNotificacaoEnum) {
        TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceExceptions("Tarefa não encontrada " + id));

        tarefasEntity.setStatusNotificacaoEnum(statusNotificacaoEnum);
        return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
    }

}
