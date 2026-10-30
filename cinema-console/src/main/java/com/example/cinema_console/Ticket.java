package com.example.cinema_console;

import java.util.Objects;

public class Ticket implements IEntity {
    private Session session;
    private int row;
    private int seat;
    private double pricePaid;

    public Ticket(Session session, int row, int seat, double pricePaid) {
        this.session = session;
        this.row = row;
        this.seat = seat;
        this.pricePaid = pricePaid;
    }

    public Session getSession() { return session; }
    public int getRow() { return row; }
    public int getSeat() { return seat; }
    public double getPricePaid() { return pricePaid; }

    @Override
    public String getInfo() {
        return "Билет на " + session.getMovie().getTitle() + " (" + session.getStartTime() + ") Ряд: " + row + ", Место: " + seat + ", Оплачено: " + pricePaid;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return row == ticket.row && seat == ticket.seat && Objects.equals(session, ticket.session);
    }
}