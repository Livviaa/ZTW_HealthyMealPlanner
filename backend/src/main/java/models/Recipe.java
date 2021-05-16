package models;

import java.util.List;

public class Recipe {

    private Integer Przepis_id;
    private String Nazwa_potrawy;
    private String Tresc;
    private String Zdjecie;
    private Double Sum_kcal;
    private Double Sum_bialko;
    private Double Sum_tluszcze;
    private Double Sum_weglowodany;
    private List<Ingredient> Skladniki;

    public Recipe() {
    }

    public Recipe(Integer przepis_id, String nazwa_potrawy, String tresc, String zdjecie, Double sum_kcal, Double sum_bialko, Double sum_tluszcze, Double sum_weglowodany, List<Ingredient> skladniki) {
        Przepis_id = przepis_id;
        Nazwa_potrawy = nazwa_potrawy;
        Tresc = tresc;
        Zdjecie = zdjecie;
        Sum_kcal = sum_kcal;
        Sum_bialko = sum_bialko;
        Sum_tluszcze = sum_tluszcze;
        Sum_weglowodany = sum_weglowodany;
        Skladniki = skladniki;
    }

    public Integer getPrzepis_id() {
        return Przepis_id;
    }

    public void setPrzepis_id(Integer przepis_id) {
        Przepis_id = przepis_id;
    }

    public String getNazwa_potrawy() {
        return Nazwa_potrawy;
    }

    public void setNazwa_potrawy(String nazwa_potrawy) {
        Nazwa_potrawy = nazwa_potrawy;
    }

    public String getTresc() {
        return Tresc;
    }

    public void setTresc(String tresc) {
        Tresc = tresc;
    }

    public String getZdjecie() {
        return Zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        Zdjecie = zdjecie;
    }

    public Double getSum_kcal() {
        return Sum_kcal;
    }

    public void setSum_kcal(Double sum_kcal) {
        Sum_kcal = sum_kcal;
    }

    public Double getSum_bialko() {
        return Sum_bialko;
    }

    public void setSum_bialko(Double sum_bialko) {
        Sum_bialko = sum_bialko;
    }

    public Double getSum_tluszcze() {
        return Sum_tluszcze;
    }

    public void setSum_tluszcze(Double sum_tluszcze) {
        Sum_tluszcze = sum_tluszcze;
    }

    public Double getSum_weglowodany() {
        return Sum_weglowodany;
    }

    public void setSum_weglowodany(Double sum_weglowodany) {
        Sum_weglowodany = sum_weglowodany;
    }

    public List<Ingredient> getSkladniki() {
        return Skladniki;
    }

    public void setSkladniki(List<Ingredient> skladniki) {
        Skladniki = skladniki;
    }
}
