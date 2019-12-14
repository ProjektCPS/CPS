package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tenant", schema = "sprava_cien_konto", catalog = "")
public class TenantEntity {
    private int tenantId;
    private String nazov;

    @Id
    @Column(name = "tenant_id", nullable = false)
    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Column(name = "nazov", nullable = false, length = 45)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantEntity that = (TenantEntity) o;
        return tenantId == that.tenantId &&
                Objects.equals(nazov, that.nazov);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantId, nazov);
    }
}
