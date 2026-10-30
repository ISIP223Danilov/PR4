package com.example.cinema_console;

import java.util.Arrays;

public class Cinema {
    private Movie[] movies;
    private int movieCount;
    private Hall[] halls;
    private int hallCount;
    private Session[] sessions;
    private int sessionCount;
    private Ticket[] tickets;
    private int ticketCount;

    public Cinema() {
        this.movies = new Movie[5];
        this.movieCount = 0;
        this.halls = new Hall[5];
        this.hallCount = 0;
        this.sessions = new Session[5];
        this.sessionCount = 0;
        this.tickets = new Ticket[10];
        this.ticketCount = 0;
    }

    public void addMovie(Movie movie) {
        if (movieCount == movies.length) movies = Arrays.copyOf(movies, movies.length * 2);
        movies[movieCount++] = movie;
    }

    public void addHall(Hall hall) {
        if (hallCount == halls.length) halls = Arrays.copyOf(halls, halls.length * 2);
        halls[hallCount++] = hall;
    }

    public void addSession(Session session) {
        if (sessionCount == sessions.length) sessions = Arrays.copyOf(sessions, sessions.length * 2);
        sessions[sessionCount++] = session;
    }

    // --- НОВЫЕ МЕТОДЫ ДЛЯ ДОБАВЛЕНИЯ СЕАНСОВ ---

    // Показать доступные фильмы (для меню)
    public void printAvailableMovies() {
        System.out.println("\nДоступные фильмы:");
        for (int i = 0; i < movieCount; i++) {
            System.out.println((i + 1) + ". " + movies[i].getTitle());
        }
    }

    // Показать доступные залы (для меню)
    public void printAvailableHalls() {
        System.out.println("\nДоступные залы:");
        for (int i = 0; i < hallCount; i++) {
            System.out.println((i + 1) + ". " + halls[i].getInfo());
        }
    }

    // Получить фильм по индексу с проверкой
    public Movie getMovieByIndex(int index) throws NotFoundException {
        if (index < 0 || index >= movieCount) throw new NotFoundException("Фильм не найден.");
        return movies[index];
    }

    // Получить зал по индексу с проверкой
    public Hall getHallByIndex(int index) throws NotFoundException {
        if (index < 0 || index >= hallCount) throw new NotFoundException("Зал не найден.");
        return halls[index];
    }

    // --- ОСТАЛЬНАЯ ЛОГИКА ---

    public Session findSessionByIndex(int index) throws NotFoundException {
        if (index < 0 || index >= sessionCount) throw new NotFoundException("Сеанс с таким номером не найден.");
        return sessions[index];
    }

    public void printSchedule() {
        System.out.println("\n--- РАСПИСАНИЕ НА ДЕНЬ ---");
        if (sessionCount == 0) {
            System.out.println("Сеансов пока нет.");
            return;
        }
        for (int i = 0; i < sessionCount; i++) {
            System.out.println((i + 1) + ". " + sessions[i].getInfo());
        }
    }

    public void printHallLayout(int sessionIndex) throws NotFoundException {
        Session session = findSessionByIndex(sessionIndex);
        System.out.println("\nСхема зала для сеанса: " + session.getMovie().getTitle() + " в " + session.getStartTime());
        System.out.println("Легенда: [ ] - свободно, [X] - занято");
        System.out.print(session.getHall().drawLayout());
    }

    public void bookSeat(int sessionIndex, int row, int seat) throws NotFoundException, IllegalArgumentException {
        Session session = findSessionByIndex(sessionIndex);
        Hall hall = session.getHall();

        if (row < 1 || row > hall.getRows() || seat < 1 || seat > hall.getCols()) {
            throw new IllegalArgumentException("Неверные координаты места. Ряд: " + row + ", Место: " + seat);
        }
        if (hall.getSeats()[row - 1][seat - 1]) {
            throw new IllegalArgumentException("Это место уже занято!");
        }

        hall.getSeats()[row - 1][seat - 1] = true;

        if (ticketCount == tickets.length) tickets = Arrays.copyOf(tickets, tickets.length * 2);
        tickets[ticketCount++] = new Ticket(session, row, seat, session.getBasePrice());

        System.out.println("Успешно! Место Ряд " + row + ", Место " + seat + " забронировано.");
    }

    public void cancelSeat(int sessionIndex, int row, int seat) throws NotFoundException, IllegalArgumentException {
        Session session = findSessionByIndex(sessionIndex);
        Hall hall = session.getHall();

        if (row < 1 || row > hall.getRows() || seat < 1 || seat > hall.getCols()) {
            throw new IllegalArgumentException("Неверные координаты места.");
        }
        if (!hall.getSeats()[row - 1][seat - 1]) {
            throw new IllegalArgumentException("Это место и так свободно!");
        }

        hall.getSeats()[row - 1][seat - 1] = false;

        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i].getSession().equals(session) && tickets[i].getRow() == row && tickets[i].getSeat() == seat) {
                for (int j = i; j < ticketCount - 1; j++) tickets[j] = tickets[j + 1];
                tickets[--ticketCount] = null;
                break;
            }
        }
        System.out.println("Бронь успешно отменена.");
    }

    public void calculateRevenue(int sessionIndex) throws NotFoundException {
        Session session = findSessionByIndex(sessionIndex);
        double total = 0;
        int soldTickets = 0;

        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i].getSession().equals(session)) {
                total += tickets[i].getPricePaid();
                soldTickets++;
            }
        }
        System.out.println("Выручка за сеанс '" + session.getMovie().getTitle() + "' (" + session.getStartTime() + "): " + total + " руб. (Продано билетов: " + soldTickets + ")");
    }
}