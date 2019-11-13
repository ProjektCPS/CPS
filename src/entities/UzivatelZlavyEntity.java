package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "uzivatel_zlavy")
@IdClass(UzivatelZlavyEntityPK.class)
public class UzivatelZlavyEntity {
    private int idZlavy;
    private int idUzivatela;
    private String aktivna;
    private String kodZlavy;

    @Id
    @Column(name = "id_zlavy", nullable = false)
    public int getIdZlavy() {
        return idZlavy;
    }

    public void setIdZlavy(int idZlavy) {
        this.idZlavy = idZlavy;
    }

    @Id
    @Column(name = "id_uzivatela", nullable = false)
    public int getIdUzivatela() {
        return idUzivatela;
    }

    public void setIdUzivatela(int idUzivatela) {
        this.idUzivatela = idUzivatela;
    }

    @Basic
    @Column(name = "aktivna", nullable = false, length = 1)
    public String getAktivna() {
        return aktivna;
    }

    public void setAktivna(String aktivna) {
        this.aktivna = aktivna;
    }

    @Basic
    @Column(name = "kod_zlavy", nullable = true, length = 30)
    public String getKodZlavy() {
        return kodZlavy;
    }

    public void setKodZlavy(String kodZlavy) {
        this.kodZlavy = kodZlavy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UzivatelZlavyEntity that = (UzivatelZlavyEntity) o;
        return idZlavy == that.idZlavy &&
                idUzivatela == that.idUzivatela &&
                Objects.equals(aktivna, that.aktivna) &&
                Objects.equals(kodZlavy, that.kodZlavy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZlavy, idUzivatela, aktivna, kodZlavy);
    }
}
