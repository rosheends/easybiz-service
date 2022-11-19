package com.msc.easybiz.easybizservice.service;

import java.util.List;
public interface BaseService {


    public List<?> getAll();

    public List<?> getAll(Object... arg);

    public Object get(Object... arg);

    public Object insert(Object... arg);

    public Object update(Object... arg);

    public void delete(Object id);
}
