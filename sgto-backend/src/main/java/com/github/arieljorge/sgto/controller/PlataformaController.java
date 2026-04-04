package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.ApiResponse;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/plataforma")
public class PlataformaController {

    @GetMapping
    public ResponseEntity<ApiResponse<List<String>>> obterPlataformasOrigem() {
        return ResponseEntity.ok(new ApiResponse<>(
            true, null,
            Arrays.stream(PlataformaExterna.values()).map(PlataformaExterna::getValue).toList()
        ));
    }
}
