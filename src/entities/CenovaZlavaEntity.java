package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cenova_zlava")
public class CenovaZlavaEntity {
    private int idCenovejZlavy;
    private double hodnotaZlavy;

    @Id
    @Column(name = "id_cenovej_zlavy", nullable = false)
    public int getIdCenovejZlavy() {
        return idCenovejZlavy;
    }

    public void setIdCenovejZlavy(int idCenovejZlavy) {
        this.idCenovejZlavy = idCenovejZlavy;
    }

    @Basic
    @Column(name = "hodnota_zlavy", nullable = false, precision = 0)
    public double getHodnotaZlavy() {
        return hodnotaZlavy;
    }

    public void setHodnotaZlavy(double hodnotaZlavy) {
        this.hodnotaZlavy = hodnotaZlavy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CenovaZlavaEntity that = (CenovaZlavaEntity) o;
        return Double.compare(that.hodnotaZlavy, hodnotaZlavy) == 0 &&
                idCenovejZlavy == that.idCenovejZlavy;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCenovejZlavy, hodnotaZlavy);
    }
}
