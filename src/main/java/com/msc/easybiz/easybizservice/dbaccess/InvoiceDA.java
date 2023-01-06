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
                "select e.*, p.budget, p.project_name, CONCAT(u.first_name , \" \", u.last_name) AS client_name\n" +
                    "from expense e\n" +
                    "inner join invoice i on i.id = e.invoice_id\n" +
                    "inner join app_user u on u.id = i.user_id\n" +
                    "inner join project p on e.project_id = p.id\n" +
                    "where e.invoice_id = ? and e.exp_status = 'Approved' and e.is_active = 1"
                , arg);
    }

    public Object getProjExpenses(Object... arg){
        return dbService.queryForObject(
                "select SUM(e.amount) as totExp from expense e\n" +
                        "where e.project_id = ? and e.exp_status = 'Approved' and e.is_active = 1"
                , arg);
    }

    @Override
    public Object insert(Object... arg) {
        dbService.update("INSERT INTO invoice (user_id, invoice_date, due_date, late_fee, total_amount, title, payment_status, project_id) VALUES (?,?,?,?,?,?,?,?)", arg);
        return dbService.queryForObject("select * from invoice order by id desc LIMIT 1");
    }

    @Override
    public Object update(Object... arg) {
        dbService.update("UPDATE expense SET is_paid=1 where invoice_id=?", arg);
        return dbService.queryForObject("select * from expense order by id desc LIMIT 1");

    }

    public Object updateInvoiceId(Object... arg) {
        dbService.update("UPDATE expense SET invoice_id=? where project_id=? and is_paid is null", arg);
        return dbService.queryForObject("select * from expense order by project_id desc LIMIT 1");

    }

    public Object updateInvPaymentStatus(Object... arg) {
        dbService.update("UPDATE invoice SET payment_status=? where id=?", arg);
        return dbService.queryForObject("select * from invoice order by id desc LIMIT 1");

    }

    @Override
    public void delete(Object id) {
        dbService.update("delete from invoice where id = ?", id);
    }
}
