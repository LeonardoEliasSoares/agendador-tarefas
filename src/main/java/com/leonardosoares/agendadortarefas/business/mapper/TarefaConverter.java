package com.leonardosoares.agendadortarefas.business.mapper;

import com.leonardosoares.agendadortarefas.business.dto.TarefasDTO;
import com.leonardosoares.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);
    TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity);
}
