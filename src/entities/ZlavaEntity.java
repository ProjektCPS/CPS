package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "zlava", schema = "sprava_cien_project")
public class ZlavaEntity {
    private int idZlavy;
    private Date datOd;
    private Date datDo;

    @Id
    @Column(name = "id_zlavy", nullable = false)
    public int getIdZlavy() {
        return idZlavy;
    }

    public void setIdZlavy(int idZlavy) {
        this.idZlavy = idZlavy;
    }

    @Basic
    @Column(name = "dat_od", nullable = false)
    public Date getDatOd() {
        return datOd;
    }

    public void setDatOd(Date datOd) {
        this.datOd = datOd;
    }

    @Basic
    @Column(name = "dat_do", nullable = true)
    public Date getDatDo() {
        return datDo;
    }

    public void setDatDo(Date datDo) {
        this.datDo = datDo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZlavaEntity that = (ZlavaEntity) o;
        return idZlavy == that.idZlavy &&
                Objects.equals(datOd, that.datOd) &&
                Objects.equals(datDo, that.datDo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idZlavy, datOd, datDo);
    }
}
