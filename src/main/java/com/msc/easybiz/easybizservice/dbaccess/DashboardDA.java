package com.msc.easybiz.easybizservice.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardDA implements BaseDA{

    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return null;
    }

    @Override
    public List<?> getAll(Object... arg) {
        return null;
    }

    @Override
    public Object get(Object... arg) {
        return null;
    }

    @Override
    public Object insert(Object... arg) {
        return null;
    }

    @Override
    public Object update(Object... arg) {
        return null;
    }

    @Override
    public void delete(Object id) {

    }

    public List<?> getProjects() {
        return dbService.queryForList(
                "SELECT * FROM project p where p.is_active=1;"
        );
    }

    public List<?> getProjectUsers(Object id) {
        return dbService.queryForList(
                "SELECT ap.id, concat(ap.first_name, \" \", ap.last_name) as full_name, ap.email, r.role_desc as role\n" +
                        "FROM manage_project mp \n" +
                        "INNER JOIN app_user ap on ap.id = mp.user_id and ap.is_active = 1\n" +
                        "INNER JOIN role r on r.id = ap.role_id\n" +
                        "WHERE mp.project_id = ?;"
        , id);
    }

    public List<?> getProjectInvoices(Object id) {
        return dbService.queryForList(
                "SELECT * FROM invoice i where i.project_id = ?;"
                , id);
    }

    public List<?> getInvoiceExpenses(Object id) {
        return dbService.queryForList(
                "SELECT * FROM expense e where e.invoice_id = ? and e.is_active = 1;"
                , id);
    }
}
