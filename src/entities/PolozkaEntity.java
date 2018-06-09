package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "polozka", schema = "sprava_cien_project")
@IdClass(PolozkaEntityPK.class)
public class PolozkaEntity {
    private int idPolozky;
    private int idPredmetu;
    private double mnozstvo;
    private double cena;

    @Id
    @Column(name = "id_polozky", nullable = false)
    public int getIdPolozky() {
        return idPolozky;
    }

    public void setIdPolozky(int idPolozky) {
        this.idPolozky = idPolozky;
    }

    @Id
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

    @Basic
    @Column(name = "cena", nullable = false, precision = 0)
    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolozkaEntity that = (PolozkaEntity) o;
        return idPolozky == that.idPolozky &&
                idPredmetu == that.idPredmetu &&
                Double.compare(that.mnozstvo, mnozstvo) == 0 &&
                Double.compare(that.cena, cena) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idPolozky, idPredmetu, mnozstvo, cena);
    }
}
