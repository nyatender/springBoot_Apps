package io.springforjames.coronavirustracker.services;

import io.springforjames.coronavirustracker.models.locationStates;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    //private static String CORONA_VIRUS_DATA = "https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String CORONA_VIRUS_DATA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<locationStates> states = new ArrayList<>();

    public List<locationStates> getStates() {
        return states;
    }

    @PostConstruct
    @Scheduled(cron = "* * * 1 * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<locationStates> newStates = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(CORONA_VIRUS_DATA))
                .build();
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            locationStates currentState = new locationStates();
            currentState.setState(record.get("Province/State"));
            currentState.setCountry(record.get("Country/Region"));
            currentState.setlatestRecords(Integer.parseInt(record.get(record.size() - 1)));
            newStates.add(currentState);
           // System.out.println(currentState);
        }
        this.states = newStates;
    }

}
