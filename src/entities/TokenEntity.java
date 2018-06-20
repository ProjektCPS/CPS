package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "token", schema = "sprava_cien_project", catalog = "")
public class TokenEntity {
    private int idSerial;
    private int idAdmin;
    private String tokenHash;
    private String tokenType;
    private String ipAdress;
    private String description;
    private Timestamp created;
    private Timestamp expiration;

    @Id
    @Column(name = "id_serial", nullable = false)
    public int getIdSerial() {
        return idSerial;
    }

    public void setIdSerial(int idSerial) {
        this.idSerial = idSerial;
    }

    @Basic
    @Column(name = "id_admin", nullable = false)
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Basic
    @Column(name = "token_hash", nullable = false, length = 100)
    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    @Basic
    @Column(name = "token_type", nullable = true, length = 100)
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Basic
    @Column(name = "ip_adress", nullable = true, length = 45)
    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 145)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created", nullable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "expiration", nullable = true)
    public Timestamp getExpiration() {
        return expiration;
    }

    public void setExpiration(Timestamp expiration) {
        this.expiration = expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenEntity that = (TokenEntity) o;
        return idSerial == that.idSerial &&
                idAdmin == that.idAdmin &&
                Objects.equals(tokenHash, that.tokenHash) &&
                Objects.equals(tokenType, that.tokenType) &&
                Objects.equals(ipAdress, that.ipAdress) &&
                Objects.equals(description, that.description) &&
                Objects.equals(created, that.created) &&
                Objects.equals(expiration, that.expiration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSerial, idAdmin, tokenHash, tokenType, ipAdress, description, created, expiration);
    }
}
