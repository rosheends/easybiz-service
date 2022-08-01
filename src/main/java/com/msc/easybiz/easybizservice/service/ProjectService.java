package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.ProjectDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProjectService implements BaseService {

    @Autowired
    private ProjectDA baseDA;

    @Override
    public List<?> getAll() {
        return baseDA.getAll();
    }

    @Override
    public List<?> getAll(Object... arg) {
        return baseDA.getAll(arg);
    }

    @Override
    public Object get(Object... arg) {
        return baseDA.get(arg);
    }

    @Override
    public Object insert(Object... arg) {
        return baseDA.insert(arg);
    }

    @Override
    public Object update(Object id, Object... arg) {
        return baseDA.update(id, arg);
    }

    @Override
    public void delete(Object id) {
        baseDA.delete(id);
    }
}
