package com.gestaovagas.gestao_vagas.modules.candidates.useCases;

import com.gestaovagas.gestao_vagas.modules.company.entities.JobEntity;
import com.gestaovagas.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    private final JobRepository jobRepository;

    public ListAllJobsByFilterUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobEntity> execute(String filter) {
        return jobRepository.findByDescriptionWithCompany(filter);
    }
}
