package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fakturacia_poloziek")
@IdClass(FakturaciaPoloziekEntityPK.class)
public class FakturaciaPoloziekEntity {
    private int id;
    private int idFaktury;
    private int idPolozky;
    private int idPredmetu;
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
    @Column(name = "id_polozky", nullable = false)
    public int getIdPolozky() {
        return idPolozky;
    }

    public void setIdPolozky(int idPolozky) {
        this.idPolozky = idPolozky;
    }

    @Basic
    @Column(name = "id_predmetu", nullable = false)
    public int getIdPredmetu() {
        return idPredmetu;
    }

    public void setIdPredmetu(int idPredmetu) {
        this.idPredmetu = idPredmetu;
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
                idPolozky == that.idPolozky &&
                idPredmetu == that.idPredmetu &&
                Double.compare(that.mnozstvo, mnozstvo) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idFaktury, idPolozky, idPredmetu, mnozstvo);
    }
}
