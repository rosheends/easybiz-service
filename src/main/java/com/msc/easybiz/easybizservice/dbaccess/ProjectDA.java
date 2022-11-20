package com.msc.easybiz.easybizservice.dbaccess;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectDA implements BaseDA{

    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList("select * from project where is_active=1");
    }

    @Override
    public Object get(Object... arg) {
        return dbService.queryForObject("select * from project where id=?", arg);
    }

    @Override
    public List<?> getAll(Object... arg) {
        return null;
    }

    @Override
    public Object insert(Object... arg) {
        dbService.update("INSERT INTO project (project_name, project_code, description, budget, is_active, product_code) VALUES (?,?,?,?,?,?)", arg);
        return dbService.queryForObject("select * from project where is_active=1 order by id desc LIMIT 1");
    }

    @Override
    public Object update(Object... arg) {
        dbService.update("update project set project_name=?, description=?, budget=? where id=?", arg);
        return dbService.queryForObject("select * from project where is_active=1 order by id desc LIMIT 1");
    }

    @Override
    public void delete(Object id) {
        dbService.update("update project set is_active=0 where id=?", id);
    }
}
