package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kumulacia_zliav")
@IdClass(KumulaciaZliavEntityPK.class)
public class KumulaciaZliavEntity {
    private int id;
    private int idZlavy;
    private Integer idKategorie;
    private Integer idPredmetu;

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

    @Basic
    @Column(name = "id_kategorie", nullable = true)
    public Integer getIdKategorie() {
        return idKategorie;
    }

    public void setIdKategorie(Integer idKategorie) {
        this.idKategorie = idKategorie;
    }

    @Basic
    @Column(name = "id_predmetu", nullable = true)
    public Integer getIdPredmetu() {
        return idPredmetu;
    }

    public void setIdPredmetu(Integer idPredmetu) {
        this.idPredmetu = idPredmetu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KumulaciaZliavEntity that = (KumulaciaZliavEntity) o;
        return id == that.id &&
                idZlavy == that.idZlavy &&
                Objects.equals(idKategorie, that.idKategorie) &&
                Objects.equals(idPredmetu, that.idPredmetu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idZlavy, idKategorie, idPredmetu);
    }
}
