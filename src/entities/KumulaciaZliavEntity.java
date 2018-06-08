package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kumulacia_zliav", schema = "sprava_cien_project", catalog = "")
@IdClass(KumulaciaZliavEntityPK.class)
public class KumulaciaZliavEntity {
    private int id;
    private int idZlavy;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id_zlavy", nullable = false)
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
        KumulaciaZliavEntity that = (KumulaciaZliavEntity) o;
        return id == that.id &&
                idZlavy == that.idZlavy;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idZlavy);
    }
}
