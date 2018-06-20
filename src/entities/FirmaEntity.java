package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "firma", schema = "sprava_cien_project", catalog = "")
public class FirmaEntity {
    private String ico;
    private String psc;
    private String nazov;
    private String email;

    @Id
    @Column(name = "ICO", nullable = false, length = 8)
    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    @Basic
    @Column(name = "PSC", nullable = false, length = 5)
    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    @Basic
    @Column(name = "nazov", nullable = false, length = 30)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirmaEntity that = (FirmaEntity) o;
        return Objects.equals(ico, that.ico) &&
                Objects.equals(psc, that.psc) &&
                Objects.equals(nazov, that.nazov) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ico, psc, nazov, email);
    }
}
