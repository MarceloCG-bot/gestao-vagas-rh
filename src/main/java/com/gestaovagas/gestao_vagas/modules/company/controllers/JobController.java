package com.gestaovagas.gestao_vagas.modules.company.controllers;

import com.gestaovagas.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.gestaovagas.gestao_vagas.modules.company.entities.JobEntity;
import com.gestaovagas.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping
    public ResponseEntity<JobEntity> create(@Valid @RequestBody CreateJobDTO dto, HttpServletRequest request) {

        String companyId = (String) request.getAttribute("company_id");

        JobEntity job = createJobUseCase.execute(dto, companyId);

        return ResponseEntity.status(HttpStatus.CREATED).body(job);
    }
}
