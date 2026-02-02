package com.gestaovagas.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthCompanyDTO(

        @NotBlank(message = "Username é obrigatório")
        String username,

        @NotBlank(message = "Password é obrigatório")
        String password
) {}
