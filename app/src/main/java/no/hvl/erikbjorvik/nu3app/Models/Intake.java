package no.hvl.erikbjorvik.nu3app.Models;

import java.util.Date;

import no.hvl.erikbjorvik.nu3app.Enums.Accuracy;
import no.hvl.erikbjorvik.nu3app.Enums.MealCategory;
import no.hvl.erikbjorvik.nu3app.Enums.Unit;

/**
 * Created by erikbjorvik on 30.07.2018.
 */

public class Intake {

    private String id;
    private String consumableId;
    private String userId;
    private MealCategory mealType;
    private Date registeredAt;
    private float amount;
    private Unit unit;
    private Accuracy accuracy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsumableId() {
        return consumableId;
    }

    public void setConsumableId(String consumableId) {
        this.consumableId = consumableId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MealCategory getMealType() {
        return mealType;
    }

    public void setMealType(MealCategory mealType) {
        this.mealType = mealType;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }
}
