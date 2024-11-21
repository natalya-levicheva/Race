package org.example;

import org.example.stage.Ferry;
import org.example.stage.Road;
import org.example.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        // Создаем этапы трассы
        List<Stage> stages = new ArrayList<>();
        stages.add(new Road(500)); // Первая дорога
        stages.add(new Ferry());  // Паром (только 1 машина)
        stages.add(new Road(300)); // Вторая дорога

        // Создаем трассу
        Race race = new Race(stages);

        // Количество участников
        int numberOfCars = 5;

        // Флаг старта гонки
        StartSignal startSignal = new StartSignal();

        // Запускаем машины
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfCars; i++) {
            cars.add(new Car("Car #" + (i + 1), race, startSignal));
        }

        System.out.println("Гонка начинается!");

        for (Car car : cars) {
            new Thread(car).start();
        }

        // Даем сигнал на старт гонки
        synchronized (startSignal) {
            startSignal.setStarted(true);
            startSignal.notifyAll();
        }
    }
}

