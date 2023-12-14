package com.forest.plants;

import lombok.Getter;

@Getter
public abstract class Plant {
    private final String plantName;
    private final PlantType plantType;
    private int growthStep;
    private int growthStatus;
    private final int growthThreshold;
    private int age;
    private final int ageThreshold;
    private int fertility;
    private boolean isAlive;
    private String sprite;
    private int lastGrowthStatus;
    private String[][] sprites;

    public void setAge(int age) {
        this.age = age;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setGrowthStep(int growthStep) {
        this.growthStep = growthStep;
    }

    public void setLastGrowthStatus(int lastGrowthStatus) {
        this.lastGrowthStatus = lastGrowthStatus;
    }

    public void setSprites(String[][] sprites) {
        this.sprites = sprites;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setFertility(int fertility) {
        this.fertility = fertility;
    }

    public void setGrowthStatus(int growthStatus) {
        this.growthStatus = growthStatus;
    }

    public Plant(String plantName, PlantType plantType, int growthStep, int growthStatus, int growthThreshold, int age, int ageThreshold, int fertility, String sprite, String[][] sprites) {
        this.plantName = plantName;
        this.plantType = plantType;
        this.growthStep = growthStep;
        this.growthStatus = growthStatus;
        this.setLastGrowthStatus(growthStep);
        this.growthThreshold = growthThreshold;
        this.age = age;
        this.ageThreshold = ageThreshold;
        this.fertility = fertility;
        this.isAlive = true;
        this.sprite = sprite;
        this.sprites = sprites;
    }

    public abstract int grow(String currentSeason, String lastSeason);

    public int determineSeason(String currentSeason) {
        switch (currentSeason) {
            case "Autumn" -> {
                return 1;
            }
            case "Winter" -> {
                return 2;
            }
            case "Spring" -> {
                return 3;
            }
            default -> {
                return 0;
            }
        }
    }

}
