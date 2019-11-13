package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "typ_predmetu")
public class TypPredmetuEntity {
    private int idTypu;
    private String nazov;

    @Id
    @Column(name = "id_typu", nullable = false)
    public int getIdTypu() {
        return idTypu;
    }

    public void setIdTypu(int idTypu) {
        this.idTypu = idTypu;
    }

    @Basic
    @Column(name = "nazov", nullable = false, length = 30)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypPredmetuEntity that = (TypPredmetuEntity) o;
        return idTypu == that.idTypu &&
                Objects.equals(nazov, that.nazov);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypu, nazov);
    }
}
