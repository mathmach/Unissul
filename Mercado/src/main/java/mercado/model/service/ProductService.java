package mercado.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mercado.model.ConnectionManager;
import mercado.model.base.service.BaseProductService;
import mercado.model.dao.ProductDAO;
import mercado.model.entity.Product;

public class ProductService implements BaseProductService {

    @Override
    public void create(Product entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            ProductDAO dao = new ProductDAO();

            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Product readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        Product product = null;

        try {
            ProductDAO dao = new ProductDAO();

            product = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }

        return product;
    }

    @Override
    public List<Product> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {

        Connection conn = ConnectionManager.getInstance().getConnection();

        List<Product> productList = new ArrayList<>();

        try {
            ProductDAO dao = new ProductDAO();

            productList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }

        return productList;
    }

    @Override
    public void update(Product entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            ProductDAO dao = new ProductDAO();

            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            ProductDAO dao = new ProductDAO();

            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void removeFromQuantity(String barCode, Long quantity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            ProductDAO dao = new ProductDAO();

            dao.removeFromQuantity(conn, barCode, quantity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

}
