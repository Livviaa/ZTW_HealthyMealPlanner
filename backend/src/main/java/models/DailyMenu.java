package models;

import java.util.Date;

public class DailyMenu {

    private Integer Jadlospis_id;
    private Integer Uzytkownik_id;
    private Date Data;
    private Double Sum_kcal;
    private Double Sum_bialko;
    private Double Sum_tluszcze;
    private Double Sum_weglowodany;

    public DailyMenu() {
    }

    public DailyMenu(Integer jadlospis_id, Integer uzytkownik_id, Date data, Double sum_kcal, Double sum_bialko, Double sum_tluszcze, Double sum_weglowodany) {
        Jadlospis_id = jadlospis_id;
        Uzytkownik_id = uzytkownik_id;
        Data = data;
        Sum_kcal = sum_kcal;
        Sum_bialko = sum_bialko;
        Sum_tluszcze = sum_tluszcze;
        Sum_weglowodany = sum_weglowodany;
    }

    public Integer getJadlospis_id() {
        return Jadlospis_id;
    }

    public void setJadlospis_id(Integer jadlospis_id) {
        Jadlospis_id = jadlospis_id;
    }

    public Integer getUzytkownik_id() {
        return Uzytkownik_id;
    }

    public void setUzytkownik_id(Integer uzytkownik_id) {
        Uzytkownik_id = uzytkownik_id;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
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
}
