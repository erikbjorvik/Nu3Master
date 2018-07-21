package no.hvl.erikbjorvik.nu3app.Models;

import org.bson.types.ObjectId;

/**
 * Created by erikbjorvik on 21.07.2018.
 *  "type": "Food",
 "mealCategory": "General",
 "name": "Eple",
 "nutrients":
 */

public class Consumable {

    private Object id;

    private String type;

    private String mealCategory;

    private String name;

    private String imagePath;

    public Object Nutrients;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Object getNutrients() {
        return Nutrients;
    }

    public void setNutrients(Object nutrients) {
        Nutrients = nutrients;
    }
}
