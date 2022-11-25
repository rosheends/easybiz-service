package com.msc.easybiz.easybizservice.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceDA implements BaseDA{
    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList(
                "SELECT i.*, CONCAT(u.first_name , \" \", u.last_name) AS client_name\n" +
                "FROM invoice i\n" +
                "INNER JOIN app_user u on u.id = i.user_id;"
        );
    }

    @Override
    public List<?> getAll(Object... arg) {
        return dbService.queryForList("SELECT i.*, CONCAT(u.first_name , \" \", u.last_name) AS client_name\n" +
                "FROM invoice i\n" +
                "INNER JOIN app_user u on u.id = i.user_id where i.id = ?;", arg);
    }

    @Override
    public Object get(Object... arg) {
        return dbService.queryForObject("SELECT i.*, CONCAT(u.first_name , \" \", u.last_name) AS client_name\n" +
                "FROM invoice i\n" +
                "INNER JOIN app_user u on u.id = i.user_id where i.id = ?;", arg);
    }

    public List<?> getExpenses(Object... arg){
        return dbService.queryForList(
                "select e.*, CONCAT(u.first_name , \" \", u.last_name) AS client_name\n" +
                    "from expense e\n" +
                    "inner join invoice i on i.id = e.invoice_id\n" +
                    "inner join app_user u on u.id = i.user_id\n" +
                    "where e.invoice_id = ? and e.exp_status = 'Approved' and e.is_active = 1"
                , arg);
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
        dbService.update("delete from invoice where id = ?", id);
    }
}
