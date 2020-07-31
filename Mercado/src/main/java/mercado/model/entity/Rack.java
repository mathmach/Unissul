package mercado.model.entity;

import mercado.model.base.BaseEntity;

public class Rack extends BaseEntity {

    private String name;

    public Rack() {
    }

    public Rack(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
