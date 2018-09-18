package no.hvl.erikbjorvik.nu3app.Models;

import no.hvl.erikbjorvik.nu3app.Enums.MealCategory;

/**
 * Created by erikbjorvik on 05.08.2018.
 */

public class MealsAndIntake {
    MealCategory category;
    float kcal;

    public MealCategory getCategory() {
        return category;
    }

    public void setCategory(MealCategory category) {
        this.category = category;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }
}
