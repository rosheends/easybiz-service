package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.AssignedUsersDA;
import com.msc.easybiz.easybizservice.dbaccess.UserDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssignedUsersService implements BaseService {

    @Autowired
    private AssignedUsersDA assignedUserDA;

    @Override
    public List<?> getAll() {
        return assignedUserDA.getAll();
    }

    public List<?> getAllClients() {
        return assignedUserDA.getAll();
    }

    @Override
    public List<?> getAll(Object... arg) {
        return assignedUserDA.getAll(arg);
    }

    @Override
    public Object get(Object... arg) {
        return assignedUserDA.get(arg);
    }

    @Override
    public Object insert(Object... arg) {
        return assignedUserDA.insert(arg);
    }

    @Override
    public Object update(Object... arg) {
        return assignedUserDA.update(arg);
    }

    public void delete(Object... arg) {
        assignedUserDA.delete(arg);
    }

    @Override
    public void delete(Object id) {
        assignedUserDA.delete(id);
    }
}

