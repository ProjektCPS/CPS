package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fakturacia_poloziek", schema = "sprava_cien_project", catalog = "")
@IdClass(FakturaciaPoloziekEntityPK.class)
public class FakturaciaPoloziekEntity {
    private int id;
    private int idFaktury;
    private double mnozstvo;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id_faktury", nullable = false)
    public int getIdFaktury() {
        return idFaktury;
    }

    public void setIdFaktury(int idFaktury) {
        this.idFaktury = idFaktury;
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
        FakturaciaPoloziekEntity that = (FakturaciaPoloziekEntity) o;
        return id == that.id &&
                idFaktury == that.idFaktury &&
                Double.compare(that.mnozstvo, mnozstvo) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idFaktury, mnozstvo);
    }
}
