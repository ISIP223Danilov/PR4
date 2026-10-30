package com.example.cinema_console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {}

        Cinema cinema = new Cinema();
        initTestData(cinema);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Добро пожаловать в систему управления Кинотеатром!");

        while (running) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Показать расписание на день");
            System.out.println("2. Показать схему зала");
            System.out.println("3. Забронировать место");
            System.out.println("4. Отменить бронь");
            System.out.println("5. Рассчитать выручку за сеанс");
            System.out.println("6. ДОБАВИТЬ НОВЫЙ СЕАНС");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1": {
                        cinema.printSchedule();
                        break;
                    }
                    case "2": {
                        System.out.print("Введите номер сеанса: ");
                        int sessionIdForLayout = Integer.parseInt(scanner.nextLine());
                        cinema.printHallLayout(sessionIdForLayout - 1);
                        break;
                    }
                    case "3": {
                        System.out.print("Введите номер сеанса: ");
                        int sessionIdBook = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите ряд: ");
                        int rowBook = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите место: ");
                        int seatBook = Integer.parseInt(scanner.nextLine());
                        cinema.bookSeat(sessionIdBook - 1, rowBook, seatBook);
                        break;
                    }
                    case "4": {
                        System.out.print("Введите номер сеанса: ");
                        int sessionIdCancel = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите ряд: ");
                        int rowCancel = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите место: ");
                        int seatCancel = Integer.parseInt(scanner.nextLine());
                        cinema.cancelSeat(sessionIdCancel - 1, rowCancel, seatCancel);
                        break;
                    }
                    case "5": {
                        System.out.print("Введите номер сеанса: ");
                        int sessionIdRev = Integer.parseInt(scanner.nextLine());
                        cinema.calculateRevenue(sessionIdRev - 1);
                        break;
                    }
                    case "6": {
                        System.out.println("\n--- СОЗДАНИЕ НОВОГО СЕАНСА ---");
                        cinema.printAvailableMovies();
                        System.out.print("Выберите номер фильма: ");
                        int movieIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        Movie selectedMovie = cinema.getMovieByIndex(movieIndex);

                        cinema.printAvailableHalls();
                        System.out.print("Выберите номер зала: ");
                        int hallIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        Hall selectedHall = cinema.getHallByIndex(hallIndex);

                        System.out.print("Введите время сеанса (например, 21:00): ");
                        String time = scanner.nextLine();

                        System.out.print("Введите базовую цену билета (в рублях): ");
                        double price = Double.parseDouble(scanner.nextLine());

                        Session newSession = new Session(selectedMovie, selectedHall, time, price);
                        cinema.addSession(newSession);
                        System.out.println("Сеанс успешно добавлен в расписание!");
                        break;
                    }
                    case "0": {
                        running = false;
                        System.out.println("Выход из программы. Хорошего дня!");
                        break;
                    }
                    default: {
                        System.out.println("Неверный пункт меню. Попробуйте еще раз.");
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА ВВОДА: Пожалуйста, вводите только числа!");
            } catch (NotFoundException e) {
                System.out.println("ОШИБКА: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("ОШИБКА ДЕЙСТВИЯ: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void initTestData(Cinema cinema) {
        Movie movie1 = new Movie("Интерстеллар", "Фантастика", 169);
        Movie movie2 = new Movie("Джон Уик 4", "Боевик", 169);

        Hall hall1 = new Hall(1, 5, 6);
        Hall hall2 = new Hall(2, 4, 5);

        Session session1 = new Session(movie1, hall1, "12:00", 350.0);
        Session session2 = new Session(movie2, hall2, "15:30", 400.0);
        Session session3 = new Session(movie1, hall2, "18:00", 450.0);

        cinema.addMovie(movie1);
        cinema.addMovie(movie2);
        cinema.addHall(hall1);
        cinema.addHall(hall2);
        cinema.addSession(session1);
        cinema.addSession(session2);
        cinema.addSession(session3);
    }
}