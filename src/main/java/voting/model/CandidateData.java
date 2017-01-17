package voting.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Created by domas on 1/12/17.
 */
public class CandidateData {

    private Long id;
    @NotNull
    @Pattern(regexp = "\\d{11}")
    private String personId;
    @NotNull
    @Length(min = 1, max = 40)
    private String firstName;
    @NotNull
    @Length(min = 1, max = 40)
    private String lastName;


    // not sure ar situ reik, kolkas palieku
    private Long partyId;
    private String partyName;
    private String partyShortName;
    private Long numberInPartyList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getPartyShortName() {
        return partyShortName;
    }

    public void setPartyShortName(String partyShortName) {
        this.partyShortName = partyShortName;
    }

    public Long getNumberInPartyList() {
        return numberInPartyList;
    }

    public void setNumberInPartyList(Long numberInPartyList) {
        this.numberInPartyList = numberInPartyList;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateData that = (CandidateData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(partyId, that.partyId) &&
                Objects.equals(partyName, that.partyName) &&
                Objects.equals(partyShortName, that.partyShortName) &&
                Objects.equals(numberInPartyList, that.numberInPartyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, firstName, lastName, partyId, partyName, partyShortName, numberInPartyList);
    }

    @Override
    public String toString() {
        return "CandidateData{" +
                "id=" + id +
                ", personId='" + personId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", partyId=" + partyId +
                ", partyName='" + partyName + '\'' +
                ", partyShortName='" + partyShortName + '\'' +
                ", numberInPartyList=" + numberInPartyList +
                '}';
    }
}
