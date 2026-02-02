package com.gestaovagas.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.algorithms.Algorithm;
import com.gestaovagas.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.gestaovagas.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCompanyUseCase(
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String execute(AuthCompanyDTO authCompanyDTO) {

        var company = companyRepository.findByUsername(authCompanyDTO.username())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username/password incorrect")
                );

        if (!passwordEncoder.matches(authCompanyDTO.password(), company.getPassword())) {
            throw new BadCredentialsException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);
    }
}
