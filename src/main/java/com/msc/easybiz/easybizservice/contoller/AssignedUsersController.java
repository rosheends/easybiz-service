package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.AssignedUsersService;
import com.msc.easybiz.easybizservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/assignedUser")
@RequestMapping(path = "/management/assignedUser", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AssignedUsersController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AssignedUsersController.class);

    @Autowired
    private AssignedUsersService assignedUserService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getAssignedUsers() {
        logger.info("Request to get all assigned users");
        return new ResponseEntity<>(assignedUserService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String userId) {
        logger.info("Request to get assigned user details for id : {}", userId);
        return new ResponseEntity<>(assignedUserService.get(userId), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addUser(@RequestBody String body) {
        logger.info("Request to assign a new user");
        Map<String,String> data = util.getData(body);
        return new ResponseEntity<>(assignedUserService.insert(data.get("user_id"), data.get("project_id")), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String userId, @RequestBody String body) {
        logger.info("Request to update user details for id : {}", userId);
        Map<String, String> data = util.getData(body);
        return new ResponseEntity<>(assignedUserService.update(data.get("email"), data.get("username"), data.get("contact_no"), data.get("city"), data.get("address_no"), data.get("street"), data.get("country"), data.get("is_active"), data.get("is_default_pwd"), data.get("role_id"), userId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String userId) {
        logger.info("Request to un-assign user: {}", userId);
        assignedUserService.delete(userId);
        return new ResponseEntity<>(assignedUserService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{projectId}/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("projectId") String projectId, @PathVariable("userId") String userId) {
        logger.info("Request to un-assign user: {}", userId);
        assignedUserService.delete(projectId, userId);
        return new ResponseEntity<>(assignedUserService.getAll(), HttpStatus.OK);
    }
}
