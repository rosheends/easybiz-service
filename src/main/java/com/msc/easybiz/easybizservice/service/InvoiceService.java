package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.InvoiceDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceService implements BaseService{
    @Autowired
    private InvoiceDA baseDA;

    @Override
    public List<?> getAll() {
        return baseDA.getAll();
    }

    @Override
    public List<?> getAll(Object... arg) {
        return baseDA.getAll();
    }

    @Override
    public Object get(Object... arg) {
        return baseDA.get(arg);
    }

    public Object getExpenses(Object... arg) {
        return baseDA.getExpenses(arg);
    }

    public Object getProjExpenses(Object... arg) {
        return baseDA.getProjExpenses(arg);
    }

    @Override
    public Object insert(Object... arg) {
        return baseDA.insert(arg);
    }

    @Override
    public Object update(Object... arg) {
        return null;
    }

    @Override
    public void delete(Object id) {
        baseDA.delete(id);
    }

}
