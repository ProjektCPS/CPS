package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "percentualna_zlava")
public class PercentualnaZlavaEntity {
    private int idPerZlavy;
    private double percentZlavy;

    public enum Fields {
        idPerZlavy,
        percentZlavy
    }

    @Id
    @Column(name = "id_per_zlavy", nullable = false)
    public int getIdPerZlavy() {
        return idPerZlavy;
    }

    public void setIdPerZlavy(int idPerZlavy) {
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
                idPerZlavy == that.idPerZlavy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerZlavy, percentZlavy);
    }
}
