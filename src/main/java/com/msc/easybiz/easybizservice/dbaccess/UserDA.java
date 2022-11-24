package com.msc.easybiz.easybizservice.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDA implements BaseDA{

    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList("select * from app_user a inner join role b on a.role_id = b.id where is_active=1");
    }

    @Override
    public Object get(Object... arg) {
        return dbService.queryForObject("select * from app_user where id=?", arg);
    }

    @Override
    public List<?> getAll(Object... arg) {
        return null;
    }

    @Override
    public Object insert(Object... arg) {
        dbService.update("INSERT INTO app_user (first_name, last_name, email, username, password, contact_no, city, address_no, street, country, is_active, is_default_pwd, role_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", arg);
        return dbService.queryForObject("select * from app_user where is_active=1 order by id desc LIMIT 1");
    }

    @Override
    public Object update(Object... arg) {
        dbService.update("update app_user set email=?, username=?, contact_no=?, city=?, address_no=?, street=?, country=?, is_active=?, is_default_pwd=?, role_id=? where id=?", arg);
        return dbService.queryForObject("select * from app_user where is_active=1 order by id desc LIMIT 1");
    }

    @Override
    public void delete(Object id) {
        dbService.update("update app_user set is_active=0 where id=?", id);
    }
}