package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.SecurityService;
import com.msc.easybiz.easybizservice.util.JsonPaserUtil;
import com.msc.easybiz.easybizservice.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController("/security")
@RequestMapping(path = "/security", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class SecurityController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private SecurityService securityService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody String body){
        Map<String, String> data = util.getData(body);
        String jwt = securityService.authenticate(data.get("username"), data.get("password"));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("auth", jwt);
        ResponseEntity response = new ResponseEntity<>(responseHeaders ,HttpStatus.OK);
        return response;
    }
}
