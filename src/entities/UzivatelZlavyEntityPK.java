package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UzivatelZlavyEntityPK implements Serializable {
    private int idZlavy;
    private int idUzivatela;

    @Column(name = "id_zlavy", nullable = false)
    @Id
    public int getIdZlavy() {
        return idZlavy;
    }

    public void setIdZlavy(int idZlavy) {
        this.idZlavy = idZlavy;
    }

    @Column(name = "id_uzivatela", nullable = false)
    @Id
    public int getIdUzivatela() {
        return idUzivatela;
    }

    public void setIdUzivatela(int idUzivatela) {
        this.idUzivatela = idUzivatela;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UzivatelZlavyEntityPK that = (UzivatelZlavyEntityPK) o;
        return idZlavy == that.idZlavy &&
                idUzivatela == that.idUzivatela;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idZlavy, idUzivatela);
    }
}
