package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "objednavka", schema = "sprava_cien_project", catalog = "")
public class ObjednavkaEntity {
    private int idObjednavky;
    private Date datObjednavky;
    private String stav;

    @Id
    @Column(name = "id_objednavky", nullable = false)
    public int getIdObjednavky() {
        return idObjednavky;
    }

    public void setIdObjednavky(int idObjednavky) {
        this.idObjednavky = idObjednavky;
    }

    @Basic
    @Column(name = "dat_objednavky", nullable = false)
    public Date getDatObjednavky() {
        return datObjednavky;
    }

    public void setDatObjednavky(Date datObjednavky) {
        this.datObjednavky = datObjednavky;
    }

    @Basic
    @Column(name = "stav", nullable = true, length = 1)
    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjednavkaEntity that = (ObjednavkaEntity) o;
        return idObjednavky == that.idObjednavky &&
                Objects.equals(datObjednavky, that.datObjednavky) &&
                Objects.equals(stav, that.stav);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idObjednavky, datObjednavky, stav);
    }
}
