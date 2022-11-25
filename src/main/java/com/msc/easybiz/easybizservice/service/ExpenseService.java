package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.ExpenseDA;
import com.msc.easybiz.easybizservice.dbaccess.ProjectDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseService implements BaseService {

    @Autowired
    private ExpenseDA expenseDA;

    @Override
    public List<?> getAll() {
        return expenseDA.getAll();
    }

    @Override
    public List<?> getAll(Object... arg) {
        return expenseDA.getAll(arg);
    }

    @Override
    public Object get(Object... arg) {
        return expenseDA.get(arg);
    }

    @Override
    public Object insert(Object... arg) {
        return expenseDA.insert(arg);
    }

    @Override
    public Object update(Object... arg) {
        return expenseDA.update(arg);
    }

    @Override
    public void delete(Object id) {
        expenseDA.delete(id);
    }

    public List<?> getAllRequests() {
        return expenseDA.getAllRequests();
    }

    public Object updateStatus(Object... arg) {
        return expenseDA.updateStatus(arg);
    }

}
