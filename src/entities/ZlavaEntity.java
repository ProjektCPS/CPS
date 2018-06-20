package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "zlava", schema = "sprava_cien_project", catalog = "")
public class ZlavaEntity {
    private int idZlavy;
    private Integer idKvantity;
    private int idTypu;
    private Integer idDatumu;
    private String idCenovejZlavy;
    private String idPerZlavy;
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
    @Column(name = "id_kvantity", nullable = true)
    public Integer getIdKvantity() {
        return idKvantity;
    }

    public void setIdKvantity(Integer idKvantity) {
        this.idKvantity = idKvantity;
    }

    @Basic
    @Column(name = "id_typu", nullable = false)
    public int getIdTypu() {
        return idTypu;
    }

    public void setIdTypu(int idTypu) {
        this.idTypu = idTypu;
    }

    @Basic
    @Column(name = "id_datumu", nullable = true)
    public Integer getIdDatumu() {
        return idDatumu;
    }

    public void setIdDatumu(Integer idDatumu) {
        this.idDatumu = idDatumu;
    }

    @Basic
    @Column(name = "id_cenovej_zlavy", nullable = true, length = 30)
    public String getIdCenovejZlavy() {
        return idCenovejZlavy;
    }

    public void setIdCenovejZlavy(String idCenovejZlavy) {
        this.idCenovejZlavy = idCenovejZlavy;
    }

    @Basic
    @Column(name = "id_per_zlavy", nullable = true, length = 30)
    public String getIdPerZlavy() {
        return idPerZlavy;
    }

    public void setIdPerZlavy(String idPerZlavy) {
        this.idPerZlavy = idPerZlavy;
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
                idTypu == that.idTypu &&
                Objects.equals(idKvantity, that.idKvantity) &&
                Objects.equals(idDatumu, that.idDatumu) &&
                Objects.equals(idCenovejZlavy, that.idCenovejZlavy) &&
                Objects.equals(idPerZlavy, that.idPerZlavy) &&
                Objects.equals(datOd, that.datOd) &&
                Objects.equals(datDo, that.datDo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idZlavy, idKvantity, idTypu, idDatumu, idCenovejZlavy, idPerZlavy, datOd, datDo);
    }
}
