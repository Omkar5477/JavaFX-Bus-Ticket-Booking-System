package sample;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Time;

public class AddBus {

    private final SimpleStringProperty service;
    private  final SimpleStringProperty source;
    private  final SimpleStringProperty destination;
    private  final SimpleStringProperty fare;
    private  final Time depart;
    private  final Time arrival;
    private  final SimpleStringProperty totalseat;
    private  final SimpleStringProperty date;

    public String getService() {
        return service.get();
    }

    public SimpleStringProperty serviceProperty() {
        return service;
    }

    public void setService(String service) {
        this.service.set(service);
    }

    public String getSource() {
        return source.get();
    }

    public SimpleStringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getFare() {
        return fare.get();
    }

    public SimpleStringProperty fareProperty() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare.set(fare);
    }



    public String getTotalseat() {
        return totalseat.get();
    }

    public SimpleStringProperty totalseatProperty() {
        return totalseat;
    }

    public void setTotalseat(String totalseat) {
        this.totalseat.set(totalseat);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public Time getDepart() {
        return depart;
    }

    public Time getArrival() {
        return arrival;
    }

    public AddBus(SimpleStringProperty service, SimpleStringProperty source, SimpleStringProperty destination,
                  SimpleStringProperty fare, Time depart, Time arrival, SimpleStringProperty totalseat, SimpleStringProperty date) {
        this.service = service;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.depart = depart;
        this.arrival = arrival;
        this.totalseat = totalseat;
        this.date = date;
    }
}


