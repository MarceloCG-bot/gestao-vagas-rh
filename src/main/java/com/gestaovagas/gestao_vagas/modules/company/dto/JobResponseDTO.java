package com.gestaovagas.gestao_vagas.modules.company.dto;

import java.util.UUID;

public record JobResponseDTO(
        UUID id,
        String description,
        String benefits,
        String level,
        UUID companyId
) {}
