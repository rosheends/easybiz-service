package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/user")
@RequestMapping(path = "/management/user", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getUsers() {
        logger.info("Request to get all users");
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String userId) {
        logger.info("Request to get user details for id : {}", userId);
        return new ResponseEntity<>(userService.get(userId), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addUser(@RequestBody String body) {
        logger.info("Request to add a new user");
        Map<String,String> data = util.getData(body);
        return new ResponseEntity<>(userService.insert(data), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String userId, @RequestBody String body) {
        logger.info("Request to update user details for id : {}", userId);
        Map<String, String> data = util.getData(body);
        return new ResponseEntity<>(userService.update(data.get("email"), data.get("username"), data.get("contact_no"), data.get("city"), data.get("address_no"), data.get("street"), data.get("country"), data.get("is_active"), data.get("is_default_pwd"), data.get("role_id"), userId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String userId) {
        logger.info("Request to delete user with code: {}", userId);
        userService.delete(userId);
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}