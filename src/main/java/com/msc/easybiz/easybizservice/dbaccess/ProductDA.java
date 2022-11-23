package com.msc.easybiz.easybizservice.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductDA implements BaseDA {

    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList("select * from product");
    }

    @Override
    public Object get(Object... arg) {
        return dbService.queryForObject("select * from product where product_code=?", arg);
    }

    @Override
    public List<?> getAll(Object... arg) {
        return null;
    }

    @Override
    public Object insert(Object... arg) {
        dbService.update("INSERT INTO product (product_code, description) VALUES (?,?)", arg);
        return dbService.queryForObject("select * from product");
    }

    @Override
    public Object update(Object... arg) {
        dbService.update("update product set description=? where product_code=?", arg);
        return dbService.queryForObject("select * from product where description=? and product_code=?", arg);
    }

    @Override
    public void delete(Object id) {
        dbService.update("DELETE from product where product_code=?", id);
    }
}
