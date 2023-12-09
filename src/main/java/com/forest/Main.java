package com.forest;

import com.forest.algorithm.ForestAlgorithm;
import com.forest.algorithm.Season;
import com.forest.plants.Plant;
import com.forest.plants.Tree;
import com.forest.terrain.Terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String... args) throws InterruptedException {
        Terrain[][] forest = new Terrain[25][25];
        ForestAlgorithm forestAlgorithm = new ForestAlgorithm(Season.Summer, Season.Summer, forest, getPlantsList());
        int currentTurn = 0;
        int turns = 100;
        while (currentTurn < turns) {
            forestAlgorithm.nextTurn();
            currentTurn++;
            System.out.print("\033[H\033[J");
            Arrays.stream(forest).map(Arrays::toString).forEach(System.out::println);
            Thread.sleep(400);
            System.out.println();
            System.out.print("\033[H\033[J");
        }
    }

    public static List<Plant> getPlantsList() {
        List<Plant> plantList = new ArrayList<>();
        String[][] oakSprites = new String[3][4];
        oakSprites[0][0] = "o1Su";
        oakSprites[0][1] = "o1Au";
        oakSprites[0][2] = "o1Wi";
        oakSprites[0][3] = "o1Sp";
        oakSprites[1][0] = "o2Su";
        oakSprites[1][1] = "o2Au";
        oakSprites[1][2] = "o2Wi";
        oakSprites[1][3] = "o2Sp";
        oakSprites[2][0] = "o3Su";
        oakSprites[2][1] = "o3Au";
        oakSprites[2][2] = "o3Wi";
        oakSprites[2][3] = "o3Sp";
        Tree oak = new Tree("Oak", "Tree", 3, 1,3, 1, 15, 10, oakSprites[0][0], oakSprites);
        plantList.add(oak);
        String[][] poplarSprites = new String[3][4];
        poplarSprites[0][0] = "p1Su";
        poplarSprites[0][1] = "p1Au";
        poplarSprites[0][2] = "p1Wi";
        poplarSprites[0][3] = "p1Sp";
        poplarSprites[1][0] = "p2Su";
        poplarSprites[1][1] = "p2Au";
        poplarSprites[1][2] = "p2Wi";
        poplarSprites[1][3] = "p2Sp";
        poplarSprites[2][0] = "p3Su";
        poplarSprites[2][1] = "p3Au";
        poplarSprites[2][2] = "p3Wi";
        poplarSprites[2][3] = "p3Sp";
        Tree poplar = new Tree("Poplar", "Tree", 3,1, 3, 1, 15, 10, poplarSprites[0][0], poplarSprites);
        plantList.add(poplar);
        String[][] spruceSprites = new String[3][4];
        spruceSprites[0][0] = "s1Su";
        spruceSprites[0][1] = "s1Au";
        spruceSprites[0][2] = "s1Wi";
        spruceSprites[0][3] = "s1Sp";
        spruceSprites[1][0] = "s2Su";
        spruceSprites[1][1] = "s2Au";
        spruceSprites[1][2] = "s2Wi";
        spruceSprites[1][3] = "s2Sp";
        spruceSprites[2][0] = "s3Su";
        spruceSprites[2][1] = "s3Au";
        spruceSprites[2][2] = "s3Wi";
        spruceSprites[2][3] = "s3Sp";
        Tree spruce = new Tree("Spruce", "Tree", 3, 1,3, 1, 15, 10, spruceSprites[0][0], spruceSprites);
        plantList.add(spruce);
        return plantList;
    }
}