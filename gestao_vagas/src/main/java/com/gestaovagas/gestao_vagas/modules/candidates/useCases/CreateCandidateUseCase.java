package com.gestaovagas.gestao_vagas.modules.candidates.useCases;

import com.gestaovagas.gestao_vagas.exceptions.UserFoundException;
import com.gestaovagas.gestao_vagas.modules.candidates.CandidateEntity;
import com.gestaovagas.gestao_vagas.modules.candidates.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateEntity execute(CandidateEntity candidate) {

        if (candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).isPresent()) {
            throw new UserFoundException();
        }

        return candidateRepository.save(candidate);
    }
}