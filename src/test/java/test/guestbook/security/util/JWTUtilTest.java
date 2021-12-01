package test.guestbook.security.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTUtilTest {

    private JWTUtil jwtUtil;

    @BeforeEach
    void testBefore() {
        System.out.println("testBefore");
        jwtUtil = new JWTUtil();
    }

    @Test
    void testEncode() throws Exception {
        String email = "user95@test.com";
        String str = jwtUtil.generateToken(email);
        System.out.println("str = " + str);
    }

    @Test
    void testValidate() throws Exception {
        String email = "user95@test.com";
        String str = jwtUtil.generateToken(email);

        Thread.sleep(5000);
        String resultEmail = jwtUtil.validateAndExtract(str);
        System.out.println("resultEmail = " + resultEmail);
    }
}