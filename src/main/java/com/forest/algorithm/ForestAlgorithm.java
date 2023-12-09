package com.forest.algorithm;

import com.forest.plants.Plant;
import com.forest.plants.Tree;
import com.forest.terrain.Terrain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
@Setter
public class ForestAlgorithm {
    private int currentTurn;
    private Season currentSeason;
    private Season lastSeason;
    private Terrain[][] forest;
    private List<Plant> plants;
    private Random random = new Random();

    public void nextTurn() {
        for (int i = 0; i < forest[0].length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                var currTerrain = forest[i][j];
                if (currTerrain.getPlant() != null) {
                    if (!currTerrain.getPlant().isAlive()) {
                        currTerrain.setPlant(null);
                    }
                    else {
                        currTerrain.getPlant().grow(currentTurn, currentSeason.name(), lastSeason.name());
                        if (currTerrain.getPlant().getGrowthStatus() == currTerrain.getPlant().getGrowthThreshold()) {
                            fertilityAlgorithm(currTerrain.getPlant(), i, j);
                        }
                    }
                }
            }
        }
        lastSeason = currentSeason;
        currentSeason = currentSeasonNext(currentSeason);
    }

    private void initialization() {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[i].length; j++) {
                forest[i][j] = new Terrain(null);
            }
        }
        currentTurn = 0;
        for (Plant plant: plants) {
            int x = random.nextInt(forest.length);
            int y = random.nextInt(forest[0].length);
            forest[x][y] = new Terrain(new Tree(plant));
        }
    }

    private Season currentSeasonNext(Season currentSeason) {
        return switch (currentSeason) {
            case Summer -> Season.Autumn;
            case Autumn -> Season.Winter;
            case Winter -> Season.Spring;
            default -> Season.Summer;
        };
    }

    private void fertilityAlgorithm(Plant plant, int i, int j) {
        if (plant.getAge() % plant.getFertility() == 0 && plant.getGrowthStatus() == plant.getGrowthThreshold()) {
            for (int k = 0; k < plant.getFertility(); k++) {
                int x, y;
                if (k % 2 == 0) {
                    x = random.nextInt(i + 1, i + 4);
                    y = random.nextInt(j + 1, j + 4);
                } else if (k % 3 == 0) {
                    x = random.nextInt(i + 1, i + 4);
                    y = random.nextInt(j - 4, j - 1);
                } else if (k % 5 == 0) {
                    x = random.nextInt(i - 4, i - 1);
                    y = random.nextInt(j + 1, j + 4);
                } else {
                    x = random.nextInt(i - 4, i - 1);
                    y = random.nextInt(j - 4, j - 1);
                }
                if (checkBounds(x, y)) {
                    if (checkNearTrees(x, y)) {
                        // forest[x][y].setPlant(new Tree(determinePlant(plant)));
                        forest[x][y].setPlant(new Tree(plants.get(random.nextInt(3))));
                        // With age, the ability to produce shoots decreases
                        if (plant.getFertility() > 1) {
                            plant.setFertility(plant.getFertility() - plant.getGrowthStep());
                            if (plant.getFertility() < 1)
                                plant.setFertility(1);
                        }
                        return;
                    }
                }
            }
        }
    }

    private boolean checkBounds(int x, int y) {
        return x < forest.length && x >= 0 && y < forest[0].length && y >= 0;
    }

    private boolean checkNearTrees(int x, int y) {
        int nearCase = random.nextInt(2);
        if (x < forest.length - 1 && x >= 1 && y < forest[0].length - 1 && y >= 1) {
            if (nearCase == 0) {
                return forest[x][y - 1].getPlant() == null && forest[x][y + 1].getPlant() == null &&
                        forest[x - 1][y].getPlant() == null && forest[x + 1][y].getPlant() == null;
            } else {
                return forest[x + 1][y + 1].getPlant() == null && forest[x - 1][y + 1].getPlant() == null &&
                        forest[x + 1][y - 1] == null && forest[x - 1][y - 1].getPlant() == null &&
                        forest[x][y - 1].getPlant() == null && forest[x][y + 1].getPlant() == null &&
                        forest[x - 1][y].getPlant() == null && forest[x + 1][y].getPlant() == null;
            }
        }
        return false;
    }

    private Plant determinePlant(Plant plant) {
        for(Plant pl: plants) {
            if (pl.getPlantName().equals(plant.getPlantName())) {
                return pl;
            }
        }
        return plants.get(0);
    }

    public ForestAlgorithm(Season currentSeason, Season lastSeason, Terrain[][] forest, List<Plant> plants) {
        this.currentSeason = currentSeason;
        this.lastSeason = lastSeason;
        this.forest = forest;
        this.plants = plants;
        initialization();
    }

    /*
    @Override
    public String toString() {
        StringBuilder forestString = new StringBuilder();
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[i].length; j++) {
                if (forest[i][j].getPlant() == null) {
                    forestString.append(" ._ _ _ _ _ _. ");
                } else {
                    forestString.append(forest[i][j].getPlant().getSprite()).append("(").append(forest[i][j].getPlant().getAge()).append("/").append(forest[i][j].getPlant().getAgeThreshold()).append(")");
                }
            }
            forestString.append("\n");
        }
        return forestString.toString();
    }*/


}
