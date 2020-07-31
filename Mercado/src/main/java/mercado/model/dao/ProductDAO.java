package mercado.model.dao;

import academico.model.criteria.ProductCriteria;
import academico.model.criteria.PreparedStatementBuilder;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mercado.model.base.dao.BaseProductDAO;
import mercado.model.entity.Product;
import mercado.model.entity.Rack;

public class ProductDAO implements BaseProductDAO {

    @Override
    public void create(Connection conn, Product entity) throws Exception {
        String sql = "INSERT INTO product (barCode, name, id_rack, quantity, total) VALUES (?, ?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getBarCode());
        ps.setString(++i, entity.getName());
        if (entity.getRack() == null) {
            ps.setNull(++i, Types.BIGINT);
        } else {
            ps.setLong(++i, entity.getRack().getId());
        }
        ps.setLong(++i, entity.getQuantity());
        ps.setLong(++i, entity.getTotal());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Product readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT product.id, product.barCode, product.name, product.id_rack, product.quantity, product.total, rack.name AS name_rack FROM product LEFT JOIN rack ON (rack.id = product.id_rack) WHERE product.id = ?;";

        Product product = null;

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            product = new Product();
            product.setId(rs.getLong("id"));
            product.setBarCode(rs.getString("barCode"));
            product.setName(rs.getString("name"));
            product.setQuantity(rs.getLong("quantity"));
            product.setTotal(rs.getLong("total"));

            Rack rack = new Rack();
            rack.setId(rs.getLong("id_rack"));
            rack.setName(rs.getString("name_rack"));

            product.setRack(rack);
        }
        rs.close();
        ps.close();

        return product;
    }

    @Override
    public List<Product> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT product.id, product.barCode, product.name, product.id_rack, product.quantity, product.total, rack.name AS name_rack FROM product LEFT JOIN rack ON (rack.id = product.id_rack) WHERE TRUE ";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {
            if (criteria.containsKey(ProductCriteria.NOME_ILIKE)) {
                String name = (String) criteria.get(ProductCriteria.NOME_ILIKE);
                sql += " AND product.name ILIKE ?";
                paramList.add("%" + name + "%");
            }
            if (criteria.containsKey(ProductCriteria.RACK_ID_EQ)) {
                Long idRack = (Long) criteria.get(ProductCriteria.RACK_ID_EQ);
                sql += " AND product.id_rack = ?";
                paramList.add(idRack);
            }
            if (criteria.containsKey(ProductCriteria.QUANTITY_GT)) {
                BigDecimal price = (BigDecimal) criteria.get(ProductCriteria.QUANTITY_GT);
                sql += " AND product.quantity > ?";
                paramList.add(price);
            }
            if (criteria.containsKey(ProductCriteria.QUANTITY_LT)) {
                BigDecimal price = (BigDecimal) criteria.get(ProductCriteria.QUANTITY_LT);
                sql += " AND product.quantity < ?";
                paramList.add(price);
            }
        }
        sql += " ORDER BY product.id ASC";
        if (limit != null) {
            sql += " LIMIT " + String.valueOf(limit) + " ";
        }
        if (offset != null) {
            sql += " OFFSET " + String.valueOf(limit);
        }
        sql += ";";

        List<Product> productList = new ArrayList<>();

        PreparedStatement ps = PreparedStatementBuilder.build(conn, sql, paramList);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setBarCode(rs.getString("barCode"));
            product.setName(rs.getString("name"));
            product.setQuantity(rs.getLong("quantity"));
            product.setTotal(rs.getLong("total"));

            Rack rack = new Rack();
            rack.setId(rs.getLong("id_rack"));
            rack.setName(rs.getString("name_rack"));

            product.setRack(rack);

            productList.add(product);
        }
        rs.close();
        ps.close();

        return productList;
    }

    @Override
    public void update(Connection conn, Product entity) throws Exception {
        String sql = "UPDATE product SET barCode = ?, name = ?, id_rack = ?, quantity = ?, total = ? WHERE id = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getBarCode());
        ps.setString(++i, entity.getName());
        if (entity.getRack() == null) {
            ps.setNull(++i, Types.BIGINT);
        } else {
            ps.setLong(++i, entity.getRack().getId());
        }
        ps.setLong(4, entity.getQuantity());
        ps.setLong(5, entity.getTotal());
        ps.setLong(6, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM product WHERE id = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void removeFromQuantity(Connection conn, String barCode, Long quantity) throws Exception {
        String sql = "UPDATE product SET quantity = quantity - ? WHERE barCode = ? RETURNING quantity;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, quantity);
        ps.setString(2, barCode);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new Exception("Código de barras não encontrado!");
        } else {
            if (rs.getLong("quantity") < 0) {
                throw new Exception("Não possui itens suficientes no estoque!");
            }
        }
        ps.close();
    }

}
