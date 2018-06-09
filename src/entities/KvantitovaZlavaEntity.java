package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kvantitova_zlava", schema = "sprava_cien_project")
public class KvantitovaZlavaEntity {
    private int idKvantity;
    private double mnozstvo;

    @Id
    @Column(name = "id_kvantity", nullable = false)
    public int getIdKvantity() {
        return idKvantity;
    }

    public void setIdKvantity(int idKvantity) {
        this.idKvantity = idKvantity;
    }

    @Basic
    @Column(name = "mnozstvo", nullable = false, precision = 0)
    public double getMnozstvo() {
        return mnozstvo;
    }

    public void setMnozstvo(double mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KvantitovaZlavaEntity that = (KvantitovaZlavaEntity) o;
        return idKvantity == that.idKvantity &&
                Double.compare(that.mnozstvo, mnozstvo) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idKvantity, mnozstvo);
    }
}
