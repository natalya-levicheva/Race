package org.example;

import org.example.stage.Stage;

import java.util.List;

public class Race {
    private List<Stage> stages;

    public Race(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Stage> getStages() {
        return stages;
    }
}