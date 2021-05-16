package models;

public class Ingredient {

    private Integer Produkt_id;
    private String Nazwa_produktu;
    private String Jednostka_miary;
    private Integer Liczba_jednostek;
    private Double Kcal_na_100_jednostek;
    private Double Bialko_na_100_jednostek;
    private Double Tluszcze_na_100_jednostek;
    private Double Weglowodany_na_100_jednostek;

    public Ingredient() {
    }

    public Ingredient(Integer produkt_id, String nazwa_produktu, String jednostka_miary, Integer liczba_jednostek,
                      Double kcal_na_100_jednostek, Double bialko_na_100_jednostek, Double tluszcze_na_100_jednostek,
                      Double weglowodany_na_100_jednostek) {
        Produkt_id = produkt_id;
        Nazwa_produktu = nazwa_produktu;
        Jednostka_miary = jednostka_miary;
        Liczba_jednostek = liczba_jednostek;
        Kcal_na_100_jednostek = kcal_na_100_jednostek;
        Bialko_na_100_jednostek = bialko_na_100_jednostek;
        Tluszcze_na_100_jednostek = tluszcze_na_100_jednostek;
        Weglowodany_na_100_jednostek = weglowodany_na_100_jednostek;
    }

    public Integer getProdukt_id() {
        return Produkt_id;
    }

    public void setProdukt_id(Integer produkt_id) {
        Produkt_id = produkt_id;
    }

    public String getNazwa_produktu() {
        return Nazwa_produktu;
    }

    public void setNazwa_produktu(String nazwa_produktu) {
        Nazwa_produktu = nazwa_produktu;
    }

    public String getJednostka_miary() {
        return Jednostka_miary;
    }

    public void setJednostka_miary(String jednostka_miary) {
        Jednostka_miary = jednostka_miary;
    }

    public Integer getLiczba_jednostek() {
        return Liczba_jednostek;
    }

    public void setLiczba_jednostek(Integer liczba_jednostek) {
        Liczba_jednostek = liczba_jednostek;
    }

    public Double getKcal_na_100_jednostek() {
        return Kcal_na_100_jednostek;
    }

    public void setKcal_na_100_jednostek(Double kcal_na_100_jednostek) {
        Kcal_na_100_jednostek = kcal_na_100_jednostek;
    }

    public Double getBialko_na_100_jednostek() {
        return Bialko_na_100_jednostek;
    }

    public void setBialko_na_100_jednostek(Double bialko_na_100_jednostek) {
        Bialko_na_100_jednostek = bialko_na_100_jednostek;
    }

    public Double getTluszcze_na_100_jednostek() {
        return Tluszcze_na_100_jednostek;
    }

    public void setTluszcze_na_100_jednostek(Double tluszcze_na_100_jednostek) {
        Tluszcze_na_100_jednostek = tluszcze_na_100_jednostek;
    }

    public Double getWeglowodany_na_100_jednostek() {
        return Weglowodany_na_100_jednostek;
    }

    public void setWeglowodany_na_100_jednostek(Double weglowodany_na_100_jednostek) {
        Weglowodany_na_100_jednostek = weglowodany_na_100_jednostek;
    }
}
