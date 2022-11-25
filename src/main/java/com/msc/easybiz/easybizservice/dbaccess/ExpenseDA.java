package com.msc.easybiz.easybizservice.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseDA implements BaseDA{

    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList("select * from expense e inner join project p on e.project_id = p.id");
    }

    @Override
    public Object get(Object... arg) {
        return dbService.queryForObject("select * from expense where id=?", arg);
    }

    @Override
    public List<?> getAll(Object... arg) {
        return dbService.queryForList("select * from expense e inner join project p on e.project_id = p.id where e.exp_status =?", arg);
    }

    @Override
    public Object insert(Object... arg) {
        dbService.update("INSERT INTO expense (project_id, invoice_id, amount, record_date, description, exp_status, is_active) VALUES (?,?,?,?,?,?,?)", arg);
        return dbService.queryForObject("select * from expense order by id desc LIMIT 1");
    }

    @Override
    public Object update(Object... arg) {
        dbService.update("update expense set amount=?, description=?, record_date=?, exp_status=? where id=?", arg);
        return dbService.queryForObject("select * from app_user where is_active=1 order by id desc LIMIT 1");
    }

    @Override
    public void delete(Object id) {
        dbService.update("update expense set is_active=0 where id=?", id);
    }

    public List<?> getAllRequests() {
        return dbService.queryForList("select * from expense e inner join project p on e.project_id = p.id where e.exp_status = 'Requested'");
    }

    public Object updateStatus(Object... arg) {
        dbService.update("update expense set exp_status=? where id=?", arg);
        return dbService.queryForObject("select * from expense where exp_status != 'Requested' order by id desc LIMIT 1");
    }
}
