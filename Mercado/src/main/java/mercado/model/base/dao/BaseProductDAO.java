package mercado.model.base.dao;

import java.sql.Connection;
import mercado.model.base.BaseDAO;
import mercado.model.entity.Product;

public interface BaseProductDAO extends BaseDAO<Product>{
    
    public void removeFromQuantity(Connection conn, String barCode, Long quantity) throws Exception;
    
}