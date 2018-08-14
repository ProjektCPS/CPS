package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "percentualna_zlava")
public class PercentualnaZlavaEntity {
    private String idPerZlavy;
    private double percentZlavy;

    @Id
    @Column(name = "id_per_zlavy", nullable = false, length = 30)
    public String getIdPerZlavy() {
        return idPerZlavy;
    }

    public void setIdPerZlavy(String idPerZlavy) {
        this.idPerZlavy = idPerZlavy;
    }

    @Basic
    @Column(name = "percent_zlavy", nullable = false, precision = 0)
    public double getPercentZlavy() {
        return percentZlavy;
    }

    public void setPercentZlavy(double percentZlavy) {
        this.percentZlavy = percentZlavy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PercentualnaZlavaEntity that = (PercentualnaZlavaEntity) o;
        return Double.compare(that.percentZlavy, percentZlavy) == 0 &&
                Objects.equals(idPerZlavy, that.idPerZlavy);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idPerZlavy, percentZlavy);
    }
}
