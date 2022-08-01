package com.msc.easybiz.easybizservice.dbaccess;

import java.util.List;

public interface BaseDA {

    public List<?> getAll();
    public List<?> getAll(Object... arg);
    public Object get(Object... arg);
    public Object insert(Object... arg);
    public Object update(Object... arg);
    public void delete(Object id);

}
