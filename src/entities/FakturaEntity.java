package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "faktura")
public class FakturaEntity {
    private int idFaktury;
    private String ico;
    private Integer idObjednavky;
    private Date datumVystavenia;
    private Date datumSplatnosti;
    private Date datumUhrady;
    private Double urok;

    @Id
    @Column(name = "id_faktury", nullable = false)
    public int getIdFaktury() {
        return idFaktury;
    }

    public void setIdFaktury(int idFaktury) {
        this.idFaktury = idFaktury;
    }

    @Basic
    @Column(name = "ICO", nullable = false, length = 8)
    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    @Basic
    @Column(name = "id_objednavky", nullable = true)
    public Integer getIdObjednavky() {
        return idObjednavky;
    }

    public void setIdObjednavky(Integer idObjednavky) {
        this.idObjednavky = idObjednavky;
    }

    @Basic
    @Column(name = "datum_vystavenia", nullable = false)
    public Date getDatumVystavenia() {
        return datumVystavenia;
    }

    public void setDatumVystavenia(Date datumVystavenia) {
        this.datumVystavenia = datumVystavenia;
    }

    @Basic
    @Column(name = "datum_splatnosti", nullable = false)
    public Date getDatumSplatnosti() {
        return datumSplatnosti;
    }

    public void setDatumSplatnosti(Date datumSplatnosti) {
        this.datumSplatnosti = datumSplatnosti;
    }

    @Basic
    @Column(name = "datum_uhrady", nullable = true)
    public Date getDatumUhrady() {
        return datumUhrady;
    }

    public void setDatumUhrady(Date datumUhrady) {
        this.datumUhrady = datumUhrady;
    }

    @Basic
    @Column(name = "urok", nullable = true, precision = 0)
    public Double getUrok() {
        return urok;
    }

    public void setUrok(Double urok) {
        this.urok = urok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakturaEntity that = (FakturaEntity) o;
        return idFaktury == that.idFaktury &&
                Objects.equals(ico, that.ico) &&
                Objects.equals(idObjednavky, that.idObjednavky) &&
                Objects.equals(datumVystavenia, that.datumVystavenia) &&
                Objects.equals(datumSplatnosti, that.datumSplatnosti) &&
                Objects.equals(datumUhrady, that.datumUhrady) &&
                Objects.equals(urok, that.urok);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idFaktury, ico, idObjednavky, datumVystavenia, datumSplatnosti, datumUhrady, urok);
    }
}
