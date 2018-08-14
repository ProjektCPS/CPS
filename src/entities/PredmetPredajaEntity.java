package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "predmet_predaja")
public class PredmetPredajaEntity {
    private int idPredmetu;
    private int idKategorie;
    private String nazov;
    private double cena;
    private String jednotka;
    private Date datumExpiracie;
    private String znacka;
    private String serioveCislo;
    private String popis;
    private int idAdmin;

    @Id
    @Column(name = "id_predmetu", nullable = false)
    public int getIdPredmetu() {
        return idPredmetu;
    }

    public void setIdPredmetu(int idPredmetu) {
        this.idPredmetu = idPredmetu;
    }

    @Basic
    @Column(name = "id_kategorie", nullable = false)
    public int getIdKategorie() {
        return idKategorie;
    }

    public void setIdKategorie(int idKategorie) {
        this.idKategorie = idKategorie;
    }

    @Basic
    @Column(name = "nazov", nullable = false, length = 30)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Basic
    @Column(name = "cena", nullable = false, precision = 0)
    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Basic
    @Column(name = "jednotka", nullable = false, length = 20)
    public String getJednotka() {
        return jednotka;
    }

    public void setJednotka(String jednotka) {
        this.jednotka = jednotka;
    }

    @Basic
    @Column(name = "datum_expiracie", nullable = true)
    public Date getDatumExpiracie() {
        return datumExpiracie;
    }

    public void setDatumExpiracie(Date datumExpiracie) {
        this.datumExpiracie = datumExpiracie;
    }

    @Basic
    @Column(name = "znacka", nullable = true, length = 30)
    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    @Basic
    @Column(name = "seriove_cislo", nullable = true, length = 30)
    public String getSerioveCislo() {
        return serioveCislo;
    }

    public void setSerioveCislo(String serioveCislo) {
        this.serioveCislo = serioveCislo;
    }

    @Basic
    @Column(name = "popis", nullable = true, length = 4000)
    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Basic
    @Column(name = "id_admin", nullable = false)
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PredmetPredajaEntity that = (PredmetPredajaEntity) o;
        return idPredmetu == that.idPredmetu &&
                idKategorie == that.idKategorie &&
                Double.compare(that.cena, cena) == 0 &&
                idAdmin == that.idAdmin &&
                Objects.equals(nazov, that.nazov) &&
                Objects.equals(jednotka, that.jednotka) &&
                Objects.equals(datumExpiracie, that.datumExpiracie) &&
                Objects.equals(znacka, that.znacka) &&
                Objects.equals(serioveCislo, that.serioveCislo) &&
                Objects.equals(popis, that.popis);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idPredmetu, idKategorie, nazov, cena, jednotka, datumExpiracie, znacka, serioveCislo, popis, idAdmin);
    }
}
