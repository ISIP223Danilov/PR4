package com.example.cinema_console;

import java.util.Objects;

public class Movie implements IEntity {
    private String title;
    private String genre;
    private int durationMinutes;

    public Movie(String title, String genre, int durationMinutes) {
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String getInfo() {
        return "Фильм: " + title + " | Жанр: " + genre + " | Длительность: " + durationMinutes + " мин.";
    }

    @Override
    public String toString() {
        return getInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return durationMinutes == movie.durationMinutes && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, durationMinutes);
    }
}