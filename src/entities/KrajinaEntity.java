package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "krajina", schema = "sprava_cien_project")
public class KrajinaEntity {
    private int idKrajiny;
    private String nazov;

    @Id
    @Column(name = "id_krajiny", nullable = false)
    public int getIdKrajiny() {
        return idKrajiny;
    }

    public void setIdKrajiny(int idKrajiny) {
        this.idKrajiny = idKrajiny;
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
        KrajinaEntity that = (KrajinaEntity) o;
        return idKrajiny == that.idKrajiny &&
                Objects.equals(nazov, that.nazov);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idKrajiny, nazov);
    }
}
