package com.forest.terrain;

import com.forest.plants.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Terrain {
    private Plant plant;

    @Override
    public String toString() {
        if (plant == null) {
            return ".___.";
        } else {
            var age = Math.round((float) plant.getAge() / getPlant().getGrowthThreshold());
            if (age > 9)
                age = 0;
            return plant.getSprite() + age;
        }
    }
}
