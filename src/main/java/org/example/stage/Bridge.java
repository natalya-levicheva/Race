package org.example.stage;

import org.example.Car;

import java.util.concurrent.Semaphore;

public class Bridge extends Stage {
    private final Object monitor = new Object();
    private int maxCarsOnBridge;
    private int carsOnBridge = 0;

    public Bridge(int maxCarsOnBridge) {
        this.maxCarsOnBridge = maxCarsOnBridge;
        this.length = 300;
        this.description = "Мост " + length + " метров";
    }

    @Override
    public void go(Car car) throws InterruptedException {
        synchronized (monitor) {
            while (carsOnBridge >= maxCarsOnBridge) {
                monitor.wait(); // Ждем, пока освободится место на мосту
            }
            carsOnBridge++;
        }

        System.out.println(car.getName() + " едет по мосту: " + description);
        Thread.sleep(1000); // Имитация прохождения моста
        System.out.println(car.getName() + " покинул мост: " + description);

        synchronized (monitor) {
            carsOnBridge--;
            monitor.notifyAll(); // Уведомляем, что место освободилось
        }
    }
}