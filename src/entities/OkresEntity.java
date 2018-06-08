package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "okres", schema = "sprava_cien_project", catalog = "")
public class OkresEntity {
    private int idOkresu;
    private String nazov;

    @Id
    @Column(name = "id_okresu", nullable = false)
    public int getIdOkresu() {
        return idOkresu;
    }

    public void setIdOkresu(int idOkresu) {
        this.idOkresu = idOkresu;
    }

    @Basic
    @Column(name = "nazov", nullable = false, length = 30)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OkresEntity that = (OkresEntity) o;
        return idOkresu == that.idOkresu &&
                Objects.equals(nazov, that.nazov);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOkresu, nazov);
    }
}
