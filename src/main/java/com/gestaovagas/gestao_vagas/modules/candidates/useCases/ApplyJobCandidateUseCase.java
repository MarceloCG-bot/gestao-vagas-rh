package com.gestaovagas.gestao_vagas.modules.candidates.useCases;

import com.gestaovagas.gestao_vagas.exceptions.JobNotFoundException;
import com.gestaovagas.gestao_vagas.exceptions.UserNotFoundException;
import com.gestaovagas.gestao_vagas.modules.candidates.CandidateRepository;
import com.gestaovagas.gestao_vagas.modules.candidates.repository.ApplyJobRepository;
import com.gestaovagas.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    private final CandidateRepository candidateRepository;

    private final JobRepository jobRepository;

    private final ApplyJobRepository applyJobRepository;

    public ApplyJobCandidateUseCase(CandidateRepository candidateRepository, JobRepository jobRepository, ApplyJobRepository applyJobRepository) {
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.applyJobRepository = applyJobRepository;
    }

    // ID do Candidato
    // ID da vaga
    public void execute(UUID idCandidate, UUID idJob) {
        // Validar se candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        // Candidato se inscrever na vaga

    }
}
