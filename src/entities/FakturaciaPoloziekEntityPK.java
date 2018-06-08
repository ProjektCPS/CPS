package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FakturaciaPoloziekEntityPK implements Serializable {
    private int id;
    private int idFaktury;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "id_faktury", nullable = false)
    @Id
    public int getIdFaktury() {
        return idFaktury;
    }

    public void setIdFaktury(int idFaktury) {
        this.idFaktury = idFaktury;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakturaciaPoloziekEntityPK that = (FakturaciaPoloziekEntityPK) o;
        return id == that.id &&
                idFaktury == that.idFaktury;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idFaktury);
    }
}
