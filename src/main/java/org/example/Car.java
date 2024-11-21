package org.example;

import org.example.stage.Stage;


public class Car implements Runnable {
    private String name;
    private Race race;
    private StartSignal startSignal;

    public Car(String name, Race race, StartSignal startSignal) {
        this.name = name;
        this.race = race;
        this.startSignal = startSignal;
    }

    @Override
    public void run() {
        try {
            // Ожидание сигнала на старт
            synchronized (startSignal) {
                while (!startSignal.isStarted()) {
                    startSignal.wait();
                }
            }

            System.out.println(name + " выходит на старт");

            // Прохождение этапов трассы
            for (Stage stage : race.getStages()) {
                stage.go(this);
            }

            System.out.println(name + " финишировал!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}