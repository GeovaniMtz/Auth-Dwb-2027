package com.dwb.auth.util;

import com.dwb.auth.entity.User;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;


class JwtUtilTest{
    private final JwtUtil jwtUtil = new JwtUtil();
    
    private User buildUser(String username) {
        User u = new User();
        u.setId(1L);
        u.setUsername(username);
        u.setRoles(Set.of("User"));
        return u;
    }

    @Test
    void alGenerarToken_elUsernameQueSaleEsElMismo(){
        User user = buildUser("mdelgado");
        String token = jwtUtil.generateToken(user);
        assertEquals("mdelgado",jwtUtil.extractUsername(token));
    }

    @Test
    void tokenRecienGenerado_EsValidoParaSuUsuario(){
        User user = buildUser("mdelgado");
        String token = jwtUtil.generateToken(user);
        assertTrue(jwtUtil.validateToken(token,user));
    }

    @Test
    void tokenNoEsValidoParaOtroUsuario(){
        User user = buildUser("mdelgado");
        String token = jwtUtil.generateToken(user);
        User otroUsuario = buildUser("rfuentes");
        assertFalse(jwtUtil.validateToken(token,otroUsuario));
    }
}