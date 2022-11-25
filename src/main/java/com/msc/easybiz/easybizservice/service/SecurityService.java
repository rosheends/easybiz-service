package com.msc.easybiz.easybizservice.service;

import com.msc.easybiz.easybizservice.dbaccess.UserDA;
import com.msc.easybiz.easybizservice.util.Constants;
import com.msc.easybiz.easybizservice.util.Util;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private UserDA baseDA;

    @Autowired
    Util util;

    public String authenticate(String username, String password) throws Exception {

        Map<String, Object> user = (Map<String, Object>) baseDA.getByUsername(username);

        if(!user.get("password").equals(util.getEncodedStr(password))){
            throw new Exception("UNAUTHORIZED");
        }

        // generater signature
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(Constants.secret),
                SignatureAlgorithm.HS256.getJcaName());

        // generate token
        String jwtToken = Jwts.builder()
                .claim("first_name", user.get("first_name"))
                .claim("last_name", user.get("last_name"))
                .claim("email", username)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(5l, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();
        return jwtToken;
    }
}
