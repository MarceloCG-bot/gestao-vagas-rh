package com.gestaovagas.gestao_vagas.modules.candidates.useCases;

import com.gestaovagas.gestao_vagas.exceptions.UserFoundException;
import com.gestaovagas.gestao_vagas.modules.candidates.CandidateEntity;
import com.gestaovagas.gestao_vagas.modules.candidates.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCandidateUseCase(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CandidateEntity execute(CandidateEntity candidate) {
        if (candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .isPresent()) {
            throw new UserFoundException();
        }

        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return candidateRepository.save(candidate);
    }
}
