package org.example.stage;

import org.example.Car;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " начинает этап: " + description);
            int speed = (int) (Math.random() * 60 + 20); // Случайная скорость
            Thread.sleep(length / speed * 100); // Время прохождения этапа
            System.out.println(car.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}