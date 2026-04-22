package com.gestaovagas.gestao_vagas.modules.company.useCases;

import com.gestaovagas.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.gestaovagas.gestao_vagas.modules.company.entities.CompanyEntity;
import com.gestaovagas.gestao_vagas.modules.company.entities.JobEntity;
import com.gestaovagas.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.gestaovagas.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateJobUseCase {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public CreateJobUseCase(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public JobEntity execute(CreateJobDTO dto, String companyId) {

        UUID companyUUID = UUID.fromString(companyId);

        CompanyEntity companyEntity = companyRepository.findById(companyUUID)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));

        JobEntity job = new JobEntity();
        job.setDescription(dto.description());
        job.setBenefits(dto.benefits());
        job.setLevel(dto.level());
        job.setCompany(companyEntity);

        return jobRepository.save(job);
    }
}
