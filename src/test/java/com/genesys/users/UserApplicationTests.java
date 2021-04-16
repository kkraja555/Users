package com.genesys.users;

import com.genesys.users.model.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    public void testVaidateUser(User user) throws Exception {

        HttpEntity<User> request = new HttpEntity<>(user);
        String response = restTemplate.postForObject(getHost() + "/validateUser", request, String.class);
        assertTrue(response.equals("Login Successful"));
        request = new HttpEntity<>(new User());
        response = restTemplate.postForObject(getHost() + "/validateUser", request, String.class);
        assertTrue(response.equals("Invalid UserName/Password"));

    }

    @Test
    public void testCrud() throws Exception {
        Random random = new Random(1000);
        int id = random.nextInt();
        // test insert
        User u1 = new User();
        u1.setName("Mike" + id);
        u1.setPassword("123@" + id);
        u1.setEmail("mike@genesys.com" + id);
        HttpEntity<User> request = new HttpEntity<>(u1);
        User insertedUser = restTemplate.postForObject(getHost() + "/newUser",
                request, User.class);
        assertTrue(u1.equals(insertedUser));

        // test get
        assertTrue(this.restTemplate.getForObject(getHost() + "/users/" +
                insertedUser.getId(), User.class).equals(insertedUser));

        // validate user test
        testVaidateUser(insertedUser);

        //test update
        User user = new User();
        user.setName("Miller");
        user.setPassword("566@444");
        user.setEmail("miller@genesys.com");
        request = new HttpEntity<>(user);
        restTemplate.put(getHost() + "/users/" + insertedUser.getId(), request);
        assertTrue(this.restTemplate.getForObject(getHost() + "/users/" + insertedUser.getId(),
                User.class).equals(user));

        //test delete
        restTemplate.delete(getHost() + "/deleteuser/" + insertedUser.getId());
        assertFalse(u1.equals(this.restTemplate.getForObject(getHost() + "/users/" + insertedUser.getId(),
                User.class)));
    }


    String getHost() {
        return "http://localhost:" + port;
    }
}
