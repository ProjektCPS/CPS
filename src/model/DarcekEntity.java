package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "darcek", schema = "sprava_cien_project", catalog = "")
public class DarcekEntity {
    private int idDarceku;
    private Double mnozstvo;
    private String popis;

    @Id
    @Column(name = "id_darceku", nullable = false)
    public int getIdDarceku() {
        return idDarceku;
    }

    public void setIdDarceku(int idDarceku) {
        this.idDarceku = idDarceku;
    }

    @Basic
    @Column(name = "mnozstvo", nullable = true, precision = 0)
    public Double getMnozstvo() {
        return mnozstvo;
    }

    public void setMnozstvo(Double mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

    @Basic
    @Column(name = "popis", nullable = true, length = 2000)
    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DarcekEntity that = (DarcekEntity) o;
        return idDarceku == that.idDarceku &&
                Objects.equals(mnozstvo, that.mnozstvo) &&
                Objects.equals(popis, that.popis);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idDarceku, mnozstvo, popis);
    }
}
