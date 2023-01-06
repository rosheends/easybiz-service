package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.DashboardDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardService implements BaseService{

    @Autowired
    private DashboardDA dashboardDA;

    @Override
    public List<?> getAll() {
        return null;
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

    public List<?> getProjects() {
        return dashboardDA.getProjects();
    }

    public List<?> getProjectUsers(Object id) {
        return dashboardDA.getProjectUsers(id);
    }

    public List<?> getProjectInvoices(Object id) {
        return dashboardDA.getProjectInvoices(id);
    }

    public List<?> getInvoiceExpenses(Object id) {
        return dashboardDA.getInvoiceExpenses(id);
    }

}
