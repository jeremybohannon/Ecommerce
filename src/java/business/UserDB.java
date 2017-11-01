/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Arrays;
import java.util.List;

public class UserDB {
    static User[] users = new User[]{new User("John", "Doe", "johndoe@email.com", "9201 University City Blvd", "Woodward 208", "Charlotte", "NC", "28223", "USA"), new User("Jane", "Doe", "janedoe@email.com", "9201 University City Blvd", "Bio Inf 112", "Charlotte", "NC", "28223", "USA")};

    static List<User> getUsers() {
        return Arrays.asList(users);
    }
}
