package models;

public class Meal_Recipe {

    private Integer Posilek_id;
    private Integer Przepis_id;

    public Meal_Recipe() {
    }

    public Meal_Recipe(Integer posilek_id, Integer przepis_id) {
        Posilek_id = posilek_id;
        Przepis_id = przepis_id;
    }

    public Integer getPosilek_id() {
        return Posilek_id;
    }

    public void setPosilek_id(Integer posilek_id) {
        Posilek_id = posilek_id;
    }

    public Integer getPrzepis_id() {
        return Przepis_id;
    }

    public void setPrzepis_id(Integer przepis_id) {
        Przepis_id = przepis_id;
    }
}
