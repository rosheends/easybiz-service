package com.msc.easybiz.easybizservice.dbaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AssignedUsersDA implements BaseDA{

    @Autowired
    private DBService dbService;

    @Override
    public List<?> getAll() {
        return dbService.queryForList("select * from manage_project");
    }

    @Override
    public List<?> get(Object... arg) {
        return dbService.queryForList("SELECT b.first_name, b.last_name, b.email, c.role_desc, a.project_id, a.user_id\n" +
                "    from manage_project a\n" +
                "    inner join app_user b  on a.user_id = b.id\n" +
                "    inner join role c on b.role_id = c.id where project_id=? ", arg);
    }

    @Override
    public List<?> getAll(Object... arg) {
        return null;
    }

    @Override
    public Object insert(Object... arg) {
        dbService.update("INSERT INTO manage_project (user_id, project_id) VALUES (?,?)", arg);
        return dbService.queryForObject("select * from manage_project order by user_id desc LIMIT 1");
    }

    @Override
    public Object update(Object... arg) {
        dbService.update("update app_user set email=?, username=?, contact_no=?, city=?, address_no=?, street=?, country=?, is_active=?, is_default_pwd=?, role_id=? where id=?", arg);
        return dbService.queryForObject("select * from app_user where is_active=1 order by id desc LIMIT 1");
    }

    public void delete(Object... arg) {
        dbService.update("delete from manage_project where project_id=? and user_id=?", arg);
    }

    @Override
    public void delete(Object id) {
        dbService.update("delete from manage_project where id=?", id);
    }
}
