package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kraj", schema = "sprava_cien_project", catalog = "")
public class KrajEntity {
    private int idKraja;
    private String nazov;

    @Id
    @Column(name = "id_kraja", nullable = false)
    public int getIdKraja() {
        return idKraja;
    }

    public void setIdKraja(int idKraja) {
        this.idKraja = idKraja;
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
        KrajEntity that = (KrajEntity) o;
        return idKraja == that.idKraja &&
                Objects.equals(nazov, that.nazov);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idKraja, nazov);
    }
}
