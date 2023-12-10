package com.forest.plants;

import java.util.Random;

public class Tree extends Plant {

    public Tree(String plantName, PlantType plantType, int growthStep, int growthStatus, int growthThreshold, int age, int ageThreshold, int fertility, String sprite, String[][] sprites) {
        super(plantName, plantType, growthStep, growthStatus, growthThreshold, age, ageThreshold, fertility, sprite, sprites);
    }

    public Tree(Plant plant) {
        super(
                plant.getPlantName(),
                plant.getPlantType(),
                plant.getGrowthStep(),
                plant.getGrowthStatus(),
                plant.getGrowthThreshold(),
                plant.getAge(),
                plant.getAgeThreshold(),
                plant.getFertility(),
                plant.getSprite(),
                plant.getSprites()
        );
    }

    @Override
    public int grow(int currentTurn, String currentSeason, String lastSeason) {
        setAge(getAge() + 1);
        boolean isGrow = false;
        boolean isChangedSeason = !currentSeason.equals(lastSeason);
        int newGrowthStatus = 0;
        // Death trigger
        Random random = new Random(getAge());
        if (getAge() > getAgeThreshold()) {
            if (random.nextInt(getAgeThreshold() + getAgeThreshold() / 10 - getAge()) == 0) {
                setAlive(false);
                return newGrowthStatus;
            }
        }
        // Growth trigger
        if (getAge() % getGrowthStep() == 0 && getGrowthStatus() < getGrowthThreshold()) {
            setLastGrowthStatus(getGrowthStatus());
            newGrowthStatus = getLastGrowthStatus() + 1;
            setGrowthStatus(newGrowthStatus);
            isGrow = true;
        }
        // Change sprite trigger
        if (isGrow || isChangedSeason) {
            setSprite(getSprites()[getGrowthStatus() - 1][determineSeason(currentSeason)]);
        }
        return newGrowthStatus;
    }

}
