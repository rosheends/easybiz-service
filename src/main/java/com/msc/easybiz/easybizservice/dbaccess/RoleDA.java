package com.msc.easybiz.easybizservice.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDA implements BaseDA{
    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList("select * from role");
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
}
