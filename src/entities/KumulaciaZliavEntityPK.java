package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class KumulaciaZliavEntityPK implements Serializable {
    private int id;
    private int idZlavy;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "id_zlavy", nullable = false)
    @Id
    public int getIdZlavy() {
        return idZlavy;
    }

    public void setIdZlavy(int idZlavy) {
        this.idZlavy = idZlavy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KumulaciaZliavEntityPK that = (KumulaciaZliavEntityPK) o;
        return id == that.id &&
                idZlavy == that.idZlavy;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idZlavy);
    }
}
