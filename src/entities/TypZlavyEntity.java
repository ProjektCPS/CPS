package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "typ_zlavy")
public class TypZlavyEntity {
    private int idTypu;
    private String nazovTypu;
    private String popis;

    public enum Fields {
        idTypu,
        nazovTypu,
        mnozstvo,
        popis
    }

    @Id
    @Column(name = "id_typu", nullable = false)
    public int getIdTypu() {
        return idTypu;
    }

    public void setIdTypu(int idTypu) {
        this.idTypu = idTypu;
    }

    @Basic
    @Column(name = "nazov_typu", nullable = false, length = 30)
    public String getNazovTypu() {
        return nazovTypu;
    }

    public void setNazovTypu(String nazovTypu) {
        this.nazovTypu = nazovTypu;
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
        TypZlavyEntity that = (TypZlavyEntity) o;
        return idTypu == that.idTypu &&
                Objects.equals(nazovTypu, that.nazovTypu) &&
                Objects.equals(popis, that.popis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypu, nazovTypu, popis);
    }
}
