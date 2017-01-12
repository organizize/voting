package voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import voting.model.*;
import voting.repository.CountyRepRepository;
import voting.repository.DistrictRepository;
import voting.repository.PartyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by domas on 1/11/17.
 */
@Component
public class DataPreloader implements CommandLineRunner {

    private final CountyRepRepository countyRepRepository;
    private final DistrictRepository districtRepository;
    private final PartyRepository partyRepository;

    @Autowired
    public DataPreloader(CountyRepRepository countyRepRepository, DistrictRepository districtRepository, PartyRepository partyRepository) {
        this.countyRepRepository = countyRepRepository;
        this.districtRepository = districtRepository;
        this.partyRepository = partyRepository;
    }


    @Override
    public void run(String... strings) throws Exception {

        District district1 = new District("Vilnius");
        District district2 = new District("Kaunas");
        District district3 = new District("Klaipėda");

        County county1 = new County("Senamiestis", 3000L);
        County county2 = new County("Naujamiestis", 3000L);
        County county3 = new County("Pašilaičiai", 3000L);
        County county4 = new County("Senamiestis", 3000L);
        County county5 = new County("Šilainiai", 3000L);

        Party party1 = new Party("Demokratai", "DEM");
        Party party2 = new Party("Tvarka ir tesingumas", "TT");
        Party party3 = new Party("Liberalai", "LIB");

        Candidate cand1 = new Candidate("111", "Jonas", "Joinaitis");
        Candidate cand2 = new Candidate("222", "Petras", "Petraitis");
        Candidate cand3 = new Candidate("333", "Jonas", "Kubilius");
        Candidate cand4 = new Candidate("444", "Jonas", "Adamkus");
        Candidate cand5 = new Candidate("555", "Jonas", "Grybas");
        Candidate cand6 = new Candidate("666", "Jonas", "Alekna");
        Candidate cand7 = new Candidate("777", "Jonas", "Juršėnas");
        Candidate cand8 = new Candidate("888", "Jonas", "Uspaskichas");
        Candidate cand9 = new Candidate("999", "Jonas", "Aparatas");

        List<County> countyList1 = new ArrayList<County>(Arrays.asList(county1, county2, county3));
        List<County> countyList2 = new ArrayList<County>(Arrays.asList(county4, county5));

        List<Candidate> candidateList1 = new ArrayList<Candidate>(Arrays.asList(cand1, cand2, cand3, cand4));
        List<Candidate> candidateList2 = new ArrayList<Candidate>(Arrays.asList(cand5, cand6, cand7));

        countyList1.forEach(c -> district1.addCounty(c));
        countyList2.forEach(c -> district2.addCounty(c));

        candidateList1.forEach(c -> district1.addCandidate(c));
        candidateList2.forEach(c -> district2.addCandidate(c));

        districtRepository.save(district1);
        districtRepository.save(district2);
        districtRepository.save(district3);
    }




}
