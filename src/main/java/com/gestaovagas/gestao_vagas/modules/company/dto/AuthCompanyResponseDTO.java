package com.gestaovagas.gestao_vagas.modules.company.dto;

public record AuthCompanyResponseDTO(
        String access_token,
        Long expires_in
) {
}
