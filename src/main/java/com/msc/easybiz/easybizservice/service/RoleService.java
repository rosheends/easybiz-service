package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.ProjectDA;
import com.msc.easybiz.easybizservice.dbaccess.RoleDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService implements BaseService{
    @Autowired
    private RoleDA baseDA;

    @Override
    public List<?> getAll() {
        return baseDA.getAll();
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
