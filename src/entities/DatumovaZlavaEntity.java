package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "datumova_zlava", schema = "sprava_cien_project", catalog = "")
public class DatumovaZlavaEntity {
    private int idDatumu;
    private int den;
    private double dlzkaTrvania;
    private Double hodina;
    private Integer frekvencia;

    @Id
    @Column(name = "id_datumu", nullable = false)
    public int getIdDatumu() {
        return idDatumu;
    }

    public void setIdDatumu(int idDatumu) {
        this.idDatumu = idDatumu;
    }

    @Basic
    @Column(name = "den", nullable = false)
    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        this.den = den;
    }

    @Basic
    @Column(name = "dlzka_trvania", nullable = false, precision = 0)
    public double getDlzkaTrvania() {
        return dlzkaTrvania;
    }

    public void setDlzkaTrvania(double dlzkaTrvania) {
        this.dlzkaTrvania = dlzkaTrvania;
    }

    @Basic
    @Column(name = "hodina", nullable = true, precision = 0)
    public Double getHodina() {
        return hodina;
    }

    public void setHodina(Double hodina) {
        this.hodina = hodina;
    }

    @Basic
    @Column(name = "frekvencia", nullable = true)
    public Integer getFrekvencia() {
        return frekvencia;
    }

    public void setFrekvencia(Integer frekvencia) {
        this.frekvencia = frekvencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatumovaZlavaEntity that = (DatumovaZlavaEntity) o;
        return idDatumu == that.idDatumu &&
                den == that.den &&
                Double.compare(that.dlzkaTrvania, dlzkaTrvania) == 0 &&
                Objects.equals(hodina, that.hodina) &&
                Objects.equals(frekvencia, that.frekvencia);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idDatumu, den, dlzkaTrvania, hodina, frekvencia);
    }
}
