package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.ProjectService;
import com.msc.easybiz.easybizservice.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/role")
@RequestMapping(path = "/management/role", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class RoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getProjects() {
        logger.info("Request to get all roles");
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }
}
