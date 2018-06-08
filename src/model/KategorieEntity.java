package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kategorie", schema = "sprava_cien_project", catalog = "")
public class KategorieEntity {
    private int idKategorie;
    private String nazov;
    private String popis;

    @Id
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
    @Column(name = "popis", nullable = true, length = 4000)
    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KategorieEntity that = (KategorieEntity) o;
        return idKategorie == that.idKategorie &&
                Objects.equals(nazov, that.nazov) &&
                Objects.equals(popis, that.popis);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idKategorie, nazov, popis);
    }
}
