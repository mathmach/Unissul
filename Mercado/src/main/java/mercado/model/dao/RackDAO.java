package mercado.model.dao;

import academico.model.criteria.RackCriteria;
import academico.model.criteria.PreparedStatementBuilder;
import mercado.model.entity.Rack;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mercado.model.base.dao.BaseRackDAO;

public class RackDAO implements BaseRackDAO {

    @Override
    public void create(Connection conn, Rack entity) throws Exception {
        String sql = "INSERT INTO rack (name) VALUES (?) RETURNING id;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getName());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        ps.close();
    }

    @Override
    public Rack readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT rack.id, rack.name FROM rack WHERE rack.id = ?;";

        Rack rack = null;

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            rack = new Rack();
            rack.setId(rs.getLong("id"));
            rack.setName(rs.getString("name"));
        }
        rs.close();
        ps.close();

        return rack;
    }

    @Override
    public List<Rack> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT rack.id, rack.name FROM rack WHERE TRUE ";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {
            if (criteria.containsKey(RackCriteria.NAME_ILIKE)) {
                String name = (String) criteria.get(RackCriteria.NAME_ILIKE);
                sql += " AND rack.name ILIKE ?";
                paramList.add("%" + name + "%");
            }
            if (criteria.containsKey(RackCriteria.QUANTITY_PRODUCT_GT)) {
                Integer productQuantity = (Integer) criteria.get(RackCriteria.QUANTITY_PRODUCT_GT);
                sql += " AND rack.id IN (SELECT id_rack FROM product GROUP BY id_rack HAVING count(*) > ?)";
                paramList.add(productQuantity);
            }
        }        
        sql += " ORDER BY rack.id ASC";
        if (limit != null) {            
            sql += " LIMIT " + String.valueOf(limit) + " ";            
        }
        if (offset != null) {            
            sql += " OFFSET " + String.valueOf(limit);            
        }
        sql += ";";

        List<Rack> rackList = new ArrayList<>();

        PreparedStatement ps = PreparedStatementBuilder.build(conn, sql, paramList);;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Rack rack = new Rack();
            rack.setId(rs.getLong("id"));
            rack.setName(rs.getString("name"));
            rackList.add(rack);
        }
        rs.close();
        ps.close();

        return rackList;
    }

    @Override
    public void update(Connection conn, Rack entity) throws Exception {
        String sql = "UPDATE rack SET rack.name = ? WHERE rack.id = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getName());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM rack WHERE rack.id = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);
        ps.execute();
        ps.close();
    }

}
