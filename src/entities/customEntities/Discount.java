package entities.customEntities;

import entities.*;

public class Discount {
    private ZlavaEntity zlavaEntity;
    private TypZlavyEntity typZlavyEntity;
    private KvantitovaZlavaEntity kvantitovaZlavaEntity;
    private PercentualnaZlavaEntity percentualnaZlavaEntity;
    private CenovaZlavaEntity cenovaZlavaEntity;
    private DatumovaZlavaEntity datumovaZlavaEntity;
    private String dateFrom;
    private String dateTo;
    private KumulaciaZliavEntity kumulaciaZliavEntity;

    public Discount(ZlavaEntity zlavaEntity, TypZlavyEntity typZlavyEntity) {
        this.zlavaEntity = zlavaEntity;
        this.dateFrom = zlavaEntity.getDatOd() != null ? zlavaEntity.getDatOd().toString() : null;
        this.dateTo = zlavaEntity.getDatDo() != null ? zlavaEntity.getDatDo().toString() : null;
        this.typZlavyEntity = typZlavyEntity;
    }

    Discount(ZlavaEntity zlavaEntity, TypZlavyEntity typZlavyEntity, KvantitovaZlavaEntity kvantitovaZlavaEntity) {
        this.zlavaEntity = zlavaEntity;
        this.typZlavyEntity = typZlavyEntity;
        this.kvantitovaZlavaEntity = kvantitovaZlavaEntity;
    }

    Discount(ZlavaEntity zlavaEntity, TypZlavyEntity typZlavyEntity, PercentualnaZlavaEntity percentualnaZlavaEntity) {
        this.zlavaEntity = zlavaEntity;
        this.typZlavyEntity = typZlavyEntity;
        this.percentualnaZlavaEntity = percentualnaZlavaEntity;
    }

    Discount(ZlavaEntity zlavaEntity, TypZlavyEntity typZlavyEntity, CenovaZlavaEntity cenovaZlavaEntity) {
        this.zlavaEntity = zlavaEntity;
        this.typZlavyEntity = typZlavyEntity;
        this.cenovaZlavaEntity = cenovaZlavaEntity;
    }

    Discount(ZlavaEntity zlavaEntity, TypZlavyEntity typZlavyEntity, DatumovaZlavaEntity datumovaZlavaEntity) {
        this.zlavaEntity = zlavaEntity;
        this.typZlavyEntity = typZlavyEntity;
        this.datumovaZlavaEntity = datumovaZlavaEntity;
    }

    public ZlavaEntity getZlavaEntity() {
        return zlavaEntity;
    }

    public TypZlavyEntity getTypZlavyEntity() {
        return typZlavyEntity;
    }

    public KvantitovaZlavaEntity getKvantitovaZlavaEntity() {
        return kvantitovaZlavaEntity;
    }

    public PercentualnaZlavaEntity getPercentualnaZlavaEntity() {
        return percentualnaZlavaEntity;
    }

    public CenovaZlavaEntity getCenovaZlavaEntity() {
        return cenovaZlavaEntity;
    }

    public DatumovaZlavaEntity getDatumovaZlavaEntity() {
        return datumovaZlavaEntity;
    }

    public void setZlavaEntity(ZlavaEntity zlavaEntity) {
        this.zlavaEntity = zlavaEntity;
    }

    public void setTypZlavyEntity(TypZlavyEntity typZlavyEntity) {
        this.typZlavyEntity = typZlavyEntity;
    }

    public void setKvantitovaZlavaEntity(KvantitovaZlavaEntity kvantitovaZlavaEntity) {
        this.kvantitovaZlavaEntity = kvantitovaZlavaEntity;
    }

    public void setPercentualnaZlavaEntity(PercentualnaZlavaEntity percentualnaZlavaEntity) {
        this.percentualnaZlavaEntity = percentualnaZlavaEntity;
    }

    public void setCenovaZlavaEntity(CenovaZlavaEntity cenovaZlavaEntity) {
        this.cenovaZlavaEntity = cenovaZlavaEntity;
    }

    public void setDatumovaZlavaEntity(DatumovaZlavaEntity datumovaZlavaEntity) {
        this.datumovaZlavaEntity = datumovaZlavaEntity;
    }

    public void setDiscountType(Object discountType) {
        if (discountType instanceof CenovaZlavaEntity) {
            this.setCenovaZlavaEntity((CenovaZlavaEntity) discountType);
        }
        if (discountType instanceof KvantitovaZlavaEntity) {
            this.setKvantitovaZlavaEntity((KvantitovaZlavaEntity) discountType);
        }
        if (discountType instanceof PercentualnaZlavaEntity) {
            this.setPercentualnaZlavaEntity((PercentualnaZlavaEntity) discountType);
        }
        if (discountType instanceof DatumovaZlavaEntity) {
            this.setDatumovaZlavaEntity((DatumovaZlavaEntity) discountType);
        }
    }

    public void setKumulaciaZliavEntity(KumulaciaZliavEntity kumulaciaZliavEntity) {
        this.kumulaciaZliavEntity = kumulaciaZliavEntity;
    }

    public KumulaciaZliavEntity getKumulaciaZliavEntity() {
        return kumulaciaZliavEntity;
    }
}
