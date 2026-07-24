package com.leonardosoares.agendadortarefas.controller;

import com.leonardosoares.agendadortarefas.business.TarefasService;
import com.leonardosoares.agendadortarefas.business.dto.TarefasDTO;
import com.leonardosoares.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorPeriodo(dataInicio, dataFim));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefa(@RequestParam("id") String id,
                                                   @RequestBody TarefasDTO dto) {
        return ResponseEntity.ok(tarefasService.updateTarefa(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefa(@RequestParam("id") String id) {

        tarefasService.deletarTarefa(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatus(@RequestParam("id") String id,
                                                   @RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum) {
        return ResponseEntity.ok(tarefasService.alteraStatus(id, statusNotificacaoEnum));
    }


}
