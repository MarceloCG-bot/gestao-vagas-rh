package com.gestaovagas.gestao_vagas.modules.company.useCases;

import com.gestaovagas.gestao_vagas.exceptions.UserFoundException;
import com.gestaovagas.gestao_vagas.modules.company.entities.CompanyEntity;
import com.gestaovagas.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public  CompanyEntity execute(CompanyEntity companyEntity){

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        companyEntity.setPassword(passwordEncoder.encode(companyEntity.getPassword()));

        return this.companyRepository.save(companyEntity);
    }

}
