package com.gestaovagas.gestao_vagas.modules.candidates.controllers;

import com.gestaovagas.gestao_vagas.modules.candidates.CandidateEntity;
import com.gestaovagas.gestao_vagas.modules.candidates.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;

    public CandidateController(CreateCandidateUseCase createCandidateUseCase) {
        this.createCandidateUseCase = createCandidateUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(
            @Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

