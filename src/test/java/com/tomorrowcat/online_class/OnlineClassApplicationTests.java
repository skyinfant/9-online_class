package com.tomorrowcat.online_class;

import com.tomorrowcat.online_class.model.entity.User;
import com.tomorrowcat.online_class.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineClassApplicationTests {


    /**
     * @Description: 测试jwt生成token并校验
     * @param:
     * @return: void
     */
	@Test
	void testGeneJwt() throws InterruptedException {
        User user = new User();
        user.setId(66);
        user.setName("二当家小D");
        user.setHeadImg("png");

        String token = JWTUtils.generateJsonWebToken(user);
        System.out.println(token);

        Thread.sleep(1000L);

        Claims claims = JWTUtils.checkJWT(token);

        System.out.println(claims.get("name"));

	}

}
