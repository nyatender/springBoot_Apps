package io.springforjames.coronavirustracker.models;

public class locationStates {

    private String state;
    private String country;
    private int latestRecords;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getlatestRecords() {
        return latestRecords;
    }

    public void setlatestRecords(int latestRecords) {
        this.latestRecords = latestRecords;
    }

    @Override
    public String toString() {
        return "locationStates{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestRecords=" + latestRecords +
                '}';
    }
}
