package mercado.model.base.service;

import mercado.model.base.BaseCRUDService;
import mercado.model.entity.Product;

public interface BaseProductService extends BaseCRUDService<Product>{
    
    public void removeFromQuantity(String barCode, Long quantity) throws Exception;
    
}