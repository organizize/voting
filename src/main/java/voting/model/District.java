package voting.model;

import voting.results.model.result.DistrictMMResult;
import voting.results.model.result.DistrictSMResult;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by domas on 1/10/17.
 */
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<County> counties = new ArrayList<>();

    @OneToMany(mappedBy = "district")
    private List<Candidate> candidates = new ArrayList<>();

    @OneToOne(mappedBy = "district", cascade = CascadeType.ALL)
    private DistrictMMResult mmResult;

    @OneToOne(mappedBy = "district", cascade = CascadeType.ALL)
    private DistrictSMResult smResult;


    public District() { }

    public District(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<County> getCounties() {
        return counties;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void addCounty(County county) {
        counties.add(county);
        county.setDistrict(this);
    }

    public void removeCounty(County county) {
        counties.remove(county);
        county.setDistrict(null);
    }

    public void removeAllCounties() {
        counties.forEach(c -> c.setDistrict(null));
        counties = new ArrayList<County>();
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        candidate.setDistrict(this);
    }

    public void removeCandidate(Candidate candidate) {
        candidates.remove(candidate);
        candidate.setDistrict(null);
    }

    public void removeAllCandidates() {
        candidates.forEach(c -> c.setDistrict(null));
        candidates = new ArrayList<Candidate>();
    }

    public DistrictMMResult getMmResult() {
        return mmResult;
    }

    public void setMmResult(DistrictMMResult mmResult) {
        this.mmResult = mmResult;
        mmResult.setDistrict(this);
    }

    public DistrictSMResult getSmResult() {
        return smResult;
    }

    public void setSmResult(DistrictSMResult smResult) {
        this.smResult = smResult;
        smResult.setDistrict(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(id, district.id) &&
                Objects.equals(name, district.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, counties, candidates);
    }

    @Override
    public String toString() {
        return String.format("%s (id %d)", name, id);
    }
}
