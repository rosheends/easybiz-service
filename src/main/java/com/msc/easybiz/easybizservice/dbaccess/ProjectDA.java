package com.msc.easybiz.easybizservice.dbaccess;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class ProjectDA implements BaseDA{

    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList("select * from project");
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
        dbService.update("INSERT INTO project (project_name, description) VALUES (?,?)", arg);
        return dbService.queryForObject("select * from project order by project_id desc LIMIT 1");
    }

    @Override
    public Object update(Object... arg) {
        return dbService.update("INSERT INTO project (project_name, description) VALUES (?,?)", arg);
    }

    @Override
    public void delete(Object id) {

    }
}
