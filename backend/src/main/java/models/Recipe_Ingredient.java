package models;

public class Recipe_Ingredient {
    private Integer Przepis_id;
    private Integer Produkt_id;

    public Recipe_Ingredient() {
    }

    public Recipe_Ingredient(Integer przepis_id, Integer produkt_id) {
        Przepis_id = przepis_id;
        Produkt_id = produkt_id;
    }

    public Integer getPrzepis_id() {
        return Przepis_id;
    }

    public void setPrzepis_id(Integer przepis_id) {
        Przepis_id = przepis_id;
    }

    public Integer getProdukt_id() {
        return Produkt_id;
    }

    public void setProdukt_id(Integer produkt_id) {
        Produkt_id = produkt_id;
    }
}
