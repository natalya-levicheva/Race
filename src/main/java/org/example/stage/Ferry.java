package org.example.stage;

import org.example.Car;

public class Ferry extends Stage {
    private final Object monitor = new Object(); // Монитор для синхронизации

    public Ferry() {
        this.length = 200;
        this.description = "Паром " + length + " метров";
    }

    @Override
    public void go(Car car) throws InterruptedException {
        synchronized (monitor) {
            System.out.println(car.getName() + " ждет паром: " + description);
            System.out.println(car.getName() + " начинает этап: " + description);
            Thread.sleep(2000); // Имитация времени на пароме
            System.out.println(car.getName() + " закончил этап: " + description);
        }
    }
}