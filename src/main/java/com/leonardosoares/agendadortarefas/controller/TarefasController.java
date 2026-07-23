package com.leonardosoares.agendadortarefas.controller;

import com.leonardosoares.agendadortarefas.business.TarefasService;
import com.leonardosoares.agendadortarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<List<TarefasDTO>> listarTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataInicial,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataFinal) {
        return ResponseEntity.ok(tarefasService.listarTarefasPorPeriodo(dataInicial, dataFinal));
    }


}
