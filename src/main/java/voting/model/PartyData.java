package voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by domas on 1/12/17.
 */
public class PartyData {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String shortName;
    @Valid
    @NotNull
    @JsonProperty("candidates")
    private List<CandidateData> candidatesData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<CandidateData> getCandidatesData() {
        return candidatesData;
    }

    public void setCandidatesData(List<CandidateData> candidatesData) {
        this.candidatesData = candidatesData;
    }
}
