package com.leonardosoares.agendadortarefas.business.mapper;

import com.leonardosoares.agendadortarefas.business.dto.TarefasDTO;
import com.leonardosoares.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);
    TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> tarefasDTO);
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntities);


}
