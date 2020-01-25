package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "polozka")
@IdClass(PolozkaEntityPK.class)
public class PolozkaEntity {
    private int idPolozky;
    private int idPredmetu;
    private Integer idZlavy;
    private int idObjednavky;
    private Integer idDarceku;
    private double mnozstvo;
    private double cena;

    public enum Fields {
        idPolozky,
        idPredmetu,
        idZlavy,
        idObjednavky,
        idDarceku,
        mnozstvo,
        cena
    }

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
    @Column(name = "id_zlavy", nullable = true)
    public Integer getIdZlavy() {
        return idZlavy;
    }

    public void setIdZlavy(Integer idZlavy) {
        this.idZlavy = idZlavy;
    }

    @Basic
    @Column(name = "id_objednavky", nullable = false)
    public int getIdObjednavky() {
        return idObjednavky;
    }

    public void setIdObjednavky(int idObjednavky) {
        this.idObjednavky = idObjednavky;
    }

    @Basic
    @Column(name = "id_darceku", nullable = true)
    public Integer getIdDarceku() {
        return idDarceku;
    }

    public void setIdDarceku(Integer idDarceku) {
        this.idDarceku = idDarceku;
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
                idObjednavky == that.idObjednavky &&
                Double.compare(that.mnozstvo, mnozstvo) == 0 &&
                Double.compare(that.cena, cena) == 0 &&
                Objects.equals(idZlavy, that.idZlavy) &&
                Objects.equals(idDarceku, that.idDarceku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPolozky, idPredmetu, idZlavy, idObjednavky, idDarceku, mnozstvo, cena);
    }
}
