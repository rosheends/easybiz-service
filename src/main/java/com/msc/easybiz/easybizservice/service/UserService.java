package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.UserDA;
import com.msc.easybiz.easybizservice.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserService implements BaseService {

    @Autowired
    private UserDA baseDA;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private Util util;

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

    public Object getProjClient(Object... arg) {
        return baseDA.getProjClient(arg);
    }

    public Object getByUsername(String username) {
        return baseDA.getByUsername(username);
    }

    @Override
    public Object insert(Object... arg) {
        return baseDA.insert(arg);
    }

    public Object insert(Map<String,String> data){

        String ePass = util.getEncodedStr(data.get("password"));
        return baseDA.insert(data.get("first_name"), data.get("last_name"),data.get("email"),data.get("username"),
                ePass, data.get("contact_no"), data.get("city"), data.get("address_no"), data.get("street"),
                data.get("country"), data.get("is_active"), data.get("is_default_pwd"), data.get("role_id")
        );
    }

    @Override
    public Object update(Object... arg) {
        return baseDA.update(arg);
    }

    @Override
    public void delete(Object id) {
        baseDA.delete(id);
    }

    public List<?> getAllClients() {
        return baseDA.getAllClients();
    }

    public void resetPassword(String username, String password, String newPassword) throws Exception {
        String ePass = util.getEncodedStr(newPassword);
        securityService.validate(username, password);
        baseDA.reset(username, ePass);
    }
}
