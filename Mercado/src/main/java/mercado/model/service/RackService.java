package mercado.model.service;

import java.sql.Connection;
import mercado.model.entity.Rack;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import mercado.model.ConnectionManager;
import mercado.model.base.service.BaseRackService;
import mercado.model.dao.RackDAO;

public class RackService implements BaseRackService {

    @Override
    public void create(Rack entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            RackDAO dao = new RackDAO();

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
    public Rack readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        Rack rack = null;

        try {
            RackDAO dao = new RackDAO();

            rack = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }

        return rack;
    }

    @Override
    public List<Rack> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        List<Rack> rackList = new ArrayList<>();

        try {
            RackDAO dao = new RackDAO();

            rackList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }

        return rackList;
    }

    @Override
    public void update(Rack entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            RackDAO dao = new RackDAO();

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
            RackDAO dao = new RackDAO();

            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

}
