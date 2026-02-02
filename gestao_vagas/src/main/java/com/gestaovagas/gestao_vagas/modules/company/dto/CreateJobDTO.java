package com.gestaovagas.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateJobDTO(
        String description,
        String benefits,

        @NotBlank(message = "O campo [level] é obrigatório")
        String level
) {}
