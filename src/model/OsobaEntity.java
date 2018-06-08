package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "osoba", schema = "sprava_cien_project", catalog = "")
public class OsobaEntity {
    private String rodCislo;
    private String meno;
    private String priezvisko;

    @Id
    @Column(name = "rod_cislo", nullable = false, length = 11)
    public String getRodCislo() {
        return rodCislo;
    }

    public void setRodCislo(String rodCislo) {
        this.rodCislo = rodCislo;
    }

    @Basic
    @Column(name = "meno", nullable = false, length = 30)
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    @Basic
    @Column(name = "priezvisko", nullable = false, length = 30)
    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OsobaEntity that = (OsobaEntity) o;
        return Objects.equals(rodCislo, that.rodCislo) &&
                Objects.equals(meno, that.meno) &&
                Objects.equals(priezvisko, that.priezvisko);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rodCislo, meno, priezvisko);
    }
}
