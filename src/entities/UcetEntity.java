package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ucet")
public class UcetEntity {
    private int idAdmin;
    private String rodCislo;
    private String ico;
    private String uzivatel;
    private String heslo;
    private String email;
    private Integer active;
    private String rola;
    private Integer tenantId;

    @Id
    @Column(name = "id_admin", nullable = false)
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
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
    @Column(name = "ICO", length = 8)
    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    @Basic
    @Column(name = "uzivatel", nullable = false, length = 30)
    public String getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(String uzivatel) {
        this.uzivatel = uzivatel;
    }

    @Basic
    @Column(name = "heslo", nullable = false, length = 255)
    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
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
    @Column(name = "active", nullable = true)
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "rola", nullable = false, length = 45)
    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UcetEntity that = (UcetEntity) o;
        return idAdmin == that.idAdmin &&
                tenantId == that.tenantId &&
                Objects.equals(rodCislo, that.rodCislo) &&
                Objects.equals(ico, that.ico) &&
                Objects.equals(uzivatel, that.uzivatel) &&
                Objects.equals(heslo, that.heslo) &&
                Objects.equals(email, that.email) &&
                Objects.equals(active, that.active) &&
                Objects.equals(rola, that.rola);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAdmin, rodCislo, ico, uzivatel, heslo, email, active, rola, tenantId);
    }

    @Basic
    @Column(name = "tenant_id", nullable = false)
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}
