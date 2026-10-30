package com.example.cinema_console;

public class Session implements IEntity {
    private Movie movie;
    private Hall hall;
    private String startTime;
    private double basePrice;

    public Session(Movie movie, Hall hall, String startTime, double basePrice) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.basePrice = basePrice;
    }

    public Movie getMovie() { return movie; }
    public Hall getHall() { return hall; }
    public String getStartTime() { return startTime; }
    public double getBasePrice() { return basePrice; }

    @Override
    public String getInfo() {
        return "Сеанс: " + startTime + " | " + movie.getTitle() + " | " + hall.getInfo() + " | Цена: " + basePrice + " руб.";
    }

    @Override
    public String toString() {
        return getInfo();
    }
}