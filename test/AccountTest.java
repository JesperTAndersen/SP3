import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
        User newUser;
        ArrayList<User> users;
        String name;

    @Test
    void createUser() {

        //Arrange
        name = "testUser";
        newUser = new User(name);
        users = new ArrayList<>();

        //Act
        users.add(newUser);
        //System.out.println(users);

        //Assert

        String expected = "[testUser]";
        String actual = users.toString();
        assertEquals(expected,actual);
    }
}


