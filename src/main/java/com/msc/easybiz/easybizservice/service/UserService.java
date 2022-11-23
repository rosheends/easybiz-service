package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.UserDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService implements BaseService {

    @Autowired
    private UserDA baseDA;

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
    public Object update(Object... arg) {
        return baseDA.update(arg);
    }

    @Override
    public void delete(Object id) {
        baseDA.delete(id);
    }
}
