package com.sobev.nginxauthserver.utils.jwt;

import com.sobev.nginxauthserver.common.JwtProperties;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
public class JwtUtils {

    JwtProperties jwtProperties;

    public JwtUtils(){

    }

    public JwtUtils(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    public String createToken(){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration());
        return Jwts.builder()
                .setSubject("Sobev") // user id from db
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getKey())
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getKey())
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT signature");
        }
        catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }
    public String getUserIdFromJToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getKey())
                .parseClaimsJws(token)
                .getBody();
        return (claims.getSubject());
    }

    public String getToken(){
        return createToken();
    }

    public String getKey(){
        return jwtProperties.getKey();
    }
}
