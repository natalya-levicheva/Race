package org.example.stage;

import org.example.Car;

public class Tunnel extends Stage {
    private final Object monitor = new Object();
    private int maxCarsInTunnel;
    private int carsInTunnel = 0;

    public Tunnel(int maxCarsInTunnel) {
        this.maxCarsInTunnel = maxCarsInTunnel;
        this.length = 150;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car car) throws InterruptedException {
        synchronized (monitor) {
            while (carsInTunnel >= maxCarsInTunnel) {
                monitor.wait(); // Ждем, пока освободится место в туннеле
            }
            carsInTunnel++;
        }

        System.out.println(car.getName() + " заехал в туннель: " + description);
        Thread.sleep(1000); // Имитация прохождения туннеля
        System.out.println(car.getName() + " покинул туннель: " + description);

        synchronized (monitor) {
            carsInTunnel--;
            monitor.notifyAll(); // Уведомляем, что место освободилось
        }
    }
}