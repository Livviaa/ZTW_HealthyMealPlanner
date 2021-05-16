package models;

import java.util.List;

public class Meal {
    private Integer Posilek_id;
    private Integer Jadlospis_id;
    private Double Sum_kcal;
    private Double Sum_bialko;
    private Double Sum_tluszcze;
    private Double Sum_weglowodany;
    private List<Recipe> Przepisy;

    public Meal() {
    }

    public Meal(Integer posilek_id, Integer jadlospis_id, Double sum_kcal, Double sum_bialko, Double sum_tluszcze, Double sum_weglowodany, List<Recipe> przepisy) {
        Posilek_id = posilek_id;
        Jadlospis_id = jadlospis_id;
        Sum_kcal = sum_kcal;
        Sum_bialko = sum_bialko;
        Sum_tluszcze = sum_tluszcze;
        Sum_weglowodany = sum_weglowodany;
        Przepisy = przepisy;
    }

    public Integer getPosilek_id() {
        return Posilek_id;
    }

    public void setPosilek_id(Integer posilek_id) {
        Posilek_id = posilek_id;
    }

    public Integer getJadlospis_id() {
        return Jadlospis_id;
    }

    public void setJadlospis_id(Integer jadlospis_id) {
        Jadlospis_id = jadlospis_id;
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

    public List<Recipe> getPrzepisy() {
        return Przepisy;
    }

    public void setPrzepisy(List<Recipe> przepisy) {
        Przepisy = przepisy;
    }
}
