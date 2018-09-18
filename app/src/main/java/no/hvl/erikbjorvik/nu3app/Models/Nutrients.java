package no.hvl.erikbjorvik.nu3app.Models;

import java.util.List;

import no.hvl.erikbjorvik.nu3app.Enums.Mineral;
import no.hvl.erikbjorvik.nu3app.Enums.Vitamin;
import no.hvl.erikbjorvik.nu3app.Enums.Accuracy;


public class Nutrients {
    private Accuracy Accuracy;
    private float Water;
    private float EdiblePart;
    private float Kilojoules;
    private float Kilocalories;
    private float DietaryFibre;
    private float Protein;
    private float Alcohol;
    private float Fat;
    private float Carbohydrate;
    private List<Vitamin> Vitamins;
    private List<Mineral> Minerals;

    public no.hvl.erikbjorvik.nu3app.Enums.Accuracy getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(no.hvl.erikbjorvik.nu3app.Enums.Accuracy accuracy) {
        Accuracy = accuracy;
    }

    public float getWater() {
        return Water;
    }

    public void setWater(float water) {
        Water = water;
    }

    public float getEdiblePart() {
        return EdiblePart;
    }

    public void setEdiblePart(float ediblePart) {
        EdiblePart = ediblePart;
    }

    public float getKilojoules() {
        return Kilojoules;
    }

    public void setKilojoules(float kilojoules) {
        Kilojoules = kilojoules;
    }

    public float getKilocalories() {
        return Kilocalories;
    }

    public void setKilocalories(float kilocalories) {
        Kilocalories = kilocalories;
    }

    public float getDietaryFibre() {
        return DietaryFibre;
    }

    public void setDietaryFibre(float dietaryFibre) {
        DietaryFibre = dietaryFibre;
    }

    public float getProtein() {
        return Protein;
    }

    public void setProtein(float protein) {
        Protein = protein;
    }

    public float getAlcohol() {
        return Alcohol;
    }

    public void setAlcohol(float alcohol) {
        Alcohol = alcohol;
    }

    public float getFat() {
        return Fat;
    }

    public void setFat(float fat) {
        Fat = fat;
    }

    public float getCarbohydrate() {
        return Carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        Carbohydrate = carbohydrate;
    }

    public List<Vitamin> getVitamins() {
        return Vitamins;
    }

    public void setVitamins(List<Vitamin> vitamins) {
        Vitamins = vitamins;
    }

    public List<Mineral> getMinerals() {
        return Minerals;
    }

    public void setMinerals(List<Mineral> minerals) {
        Minerals = minerals;
    }
}
