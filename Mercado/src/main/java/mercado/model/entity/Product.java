package mercado.model.entity;

import mercado.model.base.BaseEntity;

public class Product extends BaseEntity {

    private String barCode;
    private String name;
    private Rack rack;
    private Long quantity;
    private Long total;

    public Product() {
    }

    public Product(Long id, Rack rack, String barcode, String name, Long quantity, Long total) {
        this.id = id;
        this.barCode = barcode;
        this.name = name;
        this.rack = rack;
        this.quantity = quantity;
        this.total = total;
    }

    public Rack getRack() {
        return this.rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
