package com.example.cinema_console;

public class Hall implements IEntity {
    private int hallNumber;
    private int rows;
    private int cols;
    private boolean[][] seats; // true - занято, false - свободно

    public Hall(int hallNumber, int rows, int cols) {
        this.hallNumber = hallNumber;
        this.rows = rows;
        this.cols = cols;
        this.seats = new boolean[rows][cols]; // По умолчанию все false
    }

    public int getHallNumber() { return hallNumber; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public boolean[][] getSeats() { return seats; }

    // Метод для отрисовки схемы зала в консоли (Требование варианта 6)
    public String drawLayout() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int c = 1; c <= cols; c++) {
            sb.append(String.format("%2d ", c));
        }
        sb.append("\n");

        for (int r = 0; r < rows; r++) {
            sb.append(String.format("%2d ", r + 1));
            for (int c = 0; c < cols; c++) {
                if (seats[r][c]) {
                    sb.append("[X]"); // Занято
                } else {
                    sb.append("[ ]"); // Свободно
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getInfo() {
        return "Зал №" + hallNumber + " (Мест: " + (rows * cols) + ")";
    }

    @Override
    public String toString() {
        return getInfo();
    }
}