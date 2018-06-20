package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mesto", schema = "sprava_cien_project", catalog = "")
public class MestoEntity {
    private String psc;
    private Integer idOkresu;
    private String nazov;
    private String ulica;
    private Integer cisloDomu;

    @Id
    @Column(name = "PSC", nullable = false, length = 5)
    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    @Basic
    @Column(name = "id_okresu", nullable = true)
    public Integer getIdOkresu() {
        return idOkresu;
    }

    public void setIdOkresu(Integer idOkresu) {
        this.idOkresu = idOkresu;
    }

    @Basic
    @Column(name = "nazov", nullable = false, length = 30)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Basic
    @Column(name = "ulica", nullable = true, length = 30)
    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Basic
    @Column(name = "cislo_domu", nullable = true)
    public Integer getCisloDomu() {
        return cisloDomu;
    }

    public void setCisloDomu(Integer cisloDomu) {
        this.cisloDomu = cisloDomu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MestoEntity that = (MestoEntity) o;
        return Objects.equals(psc, that.psc) &&
                Objects.equals(idOkresu, that.idOkresu) &&
                Objects.equals(nazov, that.nazov) &&
                Objects.equals(ulica, that.ulica) &&
                Objects.equals(cisloDomu, that.cisloDomu);
    }

    @Override
    public int hashCode() {

        return Objects.hash(psc, idOkresu, nazov, ulica, cisloDomu);
    }
}
