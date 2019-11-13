package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PolozkaEntityPK implements Serializable {
    private int idPolozky;
    private int idPredmetu;

    @Column(name = "id_polozky", nullable = false)
    @Id
    public int getIdPolozky() {
        return idPolozky;
    }

    public void setIdPolozky(int idPolozky) {
        this.idPolozky = idPolozky;
    }

    @Column(name = "id_predmetu", nullable = false)
    @Id
    public int getIdPredmetu() {
        return idPredmetu;
    }

    public void setIdPredmetu(int idPredmetu) {
        this.idPredmetu = idPredmetu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolozkaEntityPK that = (PolozkaEntityPK) o;
        return idPolozky == that.idPolozky &&
                idPredmetu == that.idPredmetu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPolozky, idPredmetu);
    }
}
