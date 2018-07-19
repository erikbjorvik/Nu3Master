package no.hvl.erikbjorvik.nu3app.Models;

/**
 * Created by erikbjorvik on 14.07.2018.
 */

public class PredefinedMeal {

    private String id;
    private String name;
    private String imagePath;
    //private String image;


    public PredefinedMeal() {
    }

    public PredefinedMeal(String name) {
        this.name = name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
