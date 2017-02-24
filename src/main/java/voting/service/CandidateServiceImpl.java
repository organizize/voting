package voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.dto.CandidateData;
import voting.exception.NotFoundException;
import voting.model.Candidate;
import voting.repository.CandidateRepository;

import java.util.List;

/**
 * Created by domas on 1/14/17.
 */
@Service
public class CandidateServiceImpl implements CandidateService{

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }


    @Override
    public Candidate getCandidate(Long id) {
        Candidate candidate = candidateRepository.findOne(id);
        if (candidate == null) {
            throw (new NotFoundException("Couldn't find candidate with id " + id));
        }
        return candidate;
    }

    @Override
    public Candidate getCandidate(String personId) {
        Candidate candidate = candidateRepository.findByPersonId(personId);
        if (candidate == null) {
            throw (new NotFoundException("Couldn't find candidate with personId " + personId));
        }
        return candidate;
    }

    @Override
    public List<Candidate> getCandidates() {
        return (List<Candidate>) candidateRepository.findAll();
    }

    @Override
    public Candidate addNewCandidate(CandidateData candidateData) {
        Candidate candidate = new Candidate(candidateData.getFirstName(), candidateData.getLastName(), candidateData.getPersonId(), candidateData.getDescription());
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate addNewOrGetIfExists(CandidateData candidateData) {
        if (exists(candidateData.getPersonId())) {
            Candidate existingCandidate = getCandidate(candidateData.getPersonId());
            checkCandidateIntegrity(candidateData, existingCandidate);
            return existingCandidate;
        } else {
            return addNewCandidate(candidateData);
        }
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = getCandidate(id);
        candidateRepository.delete(id);
    }

    @Override
    public boolean exists(Long id) {
        return candidateRepository.exists(id);
    }

    @Override
    public boolean exists(String personId) {
        return candidateRepository.existsByPersonId(personId);
    }


    /**
     * Checks for conflicts between existing and updated candidate.
     * Assumes both have same PersonID.
     * Throws IllegalArgument if candidates have different names, belongs to different parties or districts
     * @param newCandidateData - must have first/last name, personID, party and optionally district fields set
     * @param existingCandidate - persisted candidate
     */
    @Override
    public void checkCandidateIntegrity(CandidateData newCandidateData, Candidate existingCandidate) {
        if (!existingCandidate.getFirstName().equals(newCandidateData.getFirstName())
                || !existingCandidate.getLastName().equals(newCandidateData.getLastName())) {
            throw (new IllegalArgumentException(
                    String.format("Kandidatas (a.k. %s) jau įrašytas kaip %s %s",
                            existingCandidate.getPersonId(), existingCandidate.getFirstName(), existingCandidate.getFirstName())
            ));
        }
        if (existingCandidate.getDistrict() != null && newCandidateData.getDistrictName() != null) {
            throw (new IllegalArgumentException(
                    String.format("Kandidatas %s jau priskirtas kitai apygardai - %s",
                            existingCandidate, existingCandidate.getDistrict()
                    )));
        }
        if (existingCandidate.getParty() != null && existingCandidate.getParty().getName() != newCandidateData.getPartyName()) {
            throw (new IllegalArgumentException(
                    String.format("Kandidatas %s jau priskirtas kitai partijai - %s",
                            existingCandidate, existingCandidate.getParty())
            ));
        }
    }

}
