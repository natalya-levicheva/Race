package org.example.stage;

import org.example.Car;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car car) throws InterruptedException;
}