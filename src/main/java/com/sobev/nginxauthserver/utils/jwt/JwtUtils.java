package com.sobev.nginxauthserver.utils.jwt;

import com.sobev.nginxauthserver.common.JwtProperties;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
public class JwtUtils {

    @Autowired
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

    public static void main(String[] args) {
        try{
            String compact = Jwts.builder()
                    .setSubject("123456") // user id from db
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 86400000L))
                    .signWith(SignatureAlgorithm.HS512, "926D96C90030DD58429D2751AC1BDBBC")
                    .compact();
            System.out.println("compact = " + compact);
            Jwts.parser()
                .setSigningKey("926D96C90030DD58429D2751AC1BDBBC")
                .parseClaimsJws(compact);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
