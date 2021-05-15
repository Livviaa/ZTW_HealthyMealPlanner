package models;

import java.util.List;

public class User {
    private Integer Uzytkownik_id;
    private String Imie;
    private String Nazwisko;
    private String Plec;
    private Double Waga;
    private Integer Wzrost;
    private String Aktywnosc;
    private String Email;
    private String Haslo;
    private Double Zalecane_dzienne_kcal;
    private Double Zalecane_dzienne_bialko;
    private Double Zalecane_dzienne_tluszcze;
    private Double Zalecane_dzienne_weglowodany;
    private List<DailyMenu> Jadlospisy;

    public User() {
    }

    public User(Integer uzytkownik_id, String imie, String nazwisko, String plec, Double waga, Integer wzrost, String aktywnosc,
                String email, String haslo, Double zalecane_dzienne_kcal, Double zalecane_dzienne_bialko,
                Double zalecane_dzienne_tluszcze, Double zalecane_dzienne_weglowodany, List<DailyMenu> jadlospisy) {
        Uzytkownik_id = uzytkownik_id;
        Imie = imie;
        Nazwisko = nazwisko;
        Plec = plec;
        Waga = waga;
        Wzrost = wzrost;
        Aktywnosc = aktywnosc;
        Email = email;
        Haslo = haslo;
        Zalecane_dzienne_kcal = zalecane_dzienne_kcal;
        Zalecane_dzienne_bialko = zalecane_dzienne_bialko;
        Zalecane_dzienne_tluszcze = zalecane_dzienne_tluszcze;
        Zalecane_dzienne_weglowodany = zalecane_dzienne_weglowodany;
        Jadlospisy = jadlospisy;
    }

    public Integer getUzytkownik_id() {
        return Uzytkownik_id;
    }

    public void setUzytkownik_id(Integer uzytkownik_id) {
        Uzytkownik_id = uzytkownik_id;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public String getPlec() {
        return Plec;
    }

    public void setPlec(String plec) {
        Plec = plec;
    }

    public Double getWaga() {
        return Waga;
    }

    public void setWaga(Double waga) {
        Waga = waga;
    }

    public Integer getWzrost() {
        return Wzrost;
    }

    public void setWzrost(Integer wzrost) {
        Wzrost = wzrost;
    }

    public String getAktywnosc() {
        return Aktywnosc;
    }

    public void setAktywnosc(String aktywnosc) {
        Aktywnosc = aktywnosc;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHaslo() {
        return Haslo;
    }

    public void setHaslo(String haslo) {
        Haslo = haslo;
    }

    public Double getZalecane_dzienne_kcal() {
        return Zalecane_dzienne_kcal;
    }

    public void setZalecane_dzienne_kcal(Double zalecane_dzienne_kcal) {
        Zalecane_dzienne_kcal = zalecane_dzienne_kcal;
    }

    public Double getZalecane_dzienne_bialko() {
        return Zalecane_dzienne_bialko;
    }

    public void setZalecane_dzienne_bialko(Double zalecane_dzienne_bialko) {
        Zalecane_dzienne_bialko = zalecane_dzienne_bialko;
    }

    public Double getZalecane_dzienne_tluszcze() {
        return Zalecane_dzienne_tluszcze;
    }

    public void setZalecane_dzienne_tluszcze(Double zalecane_dzienne_tluszcze) {
        Zalecane_dzienne_tluszcze = zalecane_dzienne_tluszcze;
    }

    public Double getZalecane_dzienne_weglowodany() {
        return Zalecane_dzienne_weglowodany;
    }

    public void setZalecane_dzienne_weglowodany(Double zalecane_dzienne_weglowodany) {
        Zalecane_dzienne_weglowodany = zalecane_dzienne_weglowodany;
    }

    public List<DailyMenu> getJadlospisy() {
        return Jadlospisy;
    }

    public void setJadlospisy(List<DailyMenu> jadlospisy) {
        Jadlospisy = jadlospisy;
    }
}
