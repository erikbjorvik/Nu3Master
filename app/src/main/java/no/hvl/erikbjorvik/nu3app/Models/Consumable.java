package no.hvl.erikbjorvik.nu3app.Models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import no.hvl.erikbjorvik.nu3app.Enums.Unit;
import no.hvl.erikbjorvik.nu3app.Misc.ObjectIdSerializer;

/**
 * Created by erikbjorvik on 21.07.2018.
 *  "type": "Food",
 "mealCategory": "General",
 "name": "Eple",
 "nutrients":
 */

public class Consumable {

    private String id;

    private String type;

    private Unit defaultUnit;

    private String mealCategory;

    private String name;

    private String imagePath;

    public Nutrients Nutrients;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Unit getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(Unit defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Nutrients getNutrients() {
        return Nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        Nutrients = nutrients;
    }
}
