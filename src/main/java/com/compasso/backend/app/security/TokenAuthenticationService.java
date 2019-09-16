package com.compasso.backend.app.security;

import com.compasso.backend.app.CompassoContext;
import com.compasso.backend.app.util.ExpectThat;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TokenAuthenticationService {

	private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationService.class);
	
    private static final long EXPIRATIONTIME = 864000000;
    private static final String SECRET = "MySecreteApp";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, Authentication authentication) {
    	
    	String authenticationName = authentication.getName();
    	
    	Map<String,Object> payLoad = new HashMap<>();
    	payLoad.put("username", authenticationName);
    	payLoad.put("sub", authenticationName);
    	
        String JWT = Jwts.builder()
                .setClaims(payLoad)
                .setExpiration(new java.util.Date(Instant.now().toEpochMilli() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String token = TOKEN_PREFIX + " " + JWT;
        res.addHeader(HEADER_STRING, token);

        try {
            res.getOutputStream().print(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Authentication getByToken(String token) {
    	String formatedToken = token.replace(TOKEN_PREFIX, "").trim();
//    	log.info("getByToken: {}", formatedToken);
        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(formatedToken)
                .getBody()
                .getSubject();
//        log.info("body: {}", user);
        return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
    }
    
    public static CompassoContext getContextByToken(String token) {
    	if(ExpectThat.isNotNullAndNotEmpty(token)) {
	    	Claims body = Jwts.parser()
	                .setSigningKey(SECRET)
	                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
	                .getBody();
	    	String login = (String) body.get("username");
	    	return new CompassoContext(login);
    	}
    	return null;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            return getByToken(token);
        }
        return null;
    }
}