package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "eshop_uzivatel")
public class EshopUzivatelEntity {
    private int idUzivatela;
    private String email;
    private String heslo;
    private String rodCislo;
    private String ico;

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

    @Basic
    @Column(name = "rod_cislo", nullable = true, length = 11)
    public String getRodCislo() {
        return rodCislo;
    }

    public void setRodCislo(String rodCislo) {
        this.rodCislo = rodCislo;
    }

    @Basic
    @Column(name = "ICO", nullable = true, length = 8)
    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EshopUzivatelEntity that = (EshopUzivatelEntity) o;
        return idUzivatela == that.idUzivatela &&
                Objects.equals(email, that.email) &&
                Objects.equals(heslo, that.heslo) &&
                Objects.equals(rodCislo, that.rodCislo) &&
                Objects.equals(ico, that.ico);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUzivatela, email, heslo, rodCislo, ico);
    }
}
