package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kraj")
public class KrajEntity {
    private int idKraja;
    private String nazov;
    private int idKrajiny;

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

    @Basic
    @Column(name = "id_krajiny", nullable = false)
    public int getIdKrajiny() {
        return idKrajiny;
    }

    public void setIdKrajiny(int idKrajiny) {
        this.idKrajiny = idKrajiny;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KrajEntity that = (KrajEntity) o;
        return idKraja == that.idKraja &&
                idKrajiny == that.idKrajiny &&
                Objects.equals(nazov, that.nazov);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKraja, nazov, idKrajiny);
    }
}
