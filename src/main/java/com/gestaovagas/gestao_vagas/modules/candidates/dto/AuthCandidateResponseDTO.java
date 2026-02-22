package com.gestaovagas.gestao_vagas.modules.candidates.dto;

public record AuthCandidateResponseDTO(
        String access_token,
        Long expires_in
) {
}
