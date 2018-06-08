package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "registrovany_uzivatel", schema = "sprava_cien_project", catalog = "")
public class RegistrovanyUzivatelEntity {
    private int idUzivatela;
    private String email;
    private String heslo;

    @Id
    @Column(name = "id_uzivatela", nullable = false)
    public int getIdUzivatela() {
        return idUzivatela;
    }

    public void setIdUzivatela(int idUzivatela) {
        this.idUzivatela = idUzivatela;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "heslo", nullable = false, length = 40)
    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrovanyUzivatelEntity that = (RegistrovanyUzivatelEntity) o;
        return idUzivatela == that.idUzivatela &&
                Objects.equals(email, that.email) &&
                Objects.equals(heslo, that.heslo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUzivatela, email, heslo);
    }
}
