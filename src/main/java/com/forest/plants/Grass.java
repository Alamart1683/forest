package com.forest.plants;

import lombok.Data;

public class Grass extends Plant {

    public Grass(String plantName, PlantType plantType, int growthStep, int growthStatus, int growthThreshold, int age, int ageThreshold, int fertility, String sprite, String[][] sprites) {
        super(plantName, plantType, growthStep, growthStatus, growthThreshold, age, ageThreshold, fertility, sprite, sprites);
    }

    @Override
    public int grow(String currentSeason, String lastSeason) {
        return 0;
    }
}
