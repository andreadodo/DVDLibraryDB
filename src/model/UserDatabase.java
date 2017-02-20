package model;

import java.util.ArrayList;

/**
 * Created by andrea on 14/02/17.
 */
public class UserDatabase {
    private ArrayList<UserItem> userDatabase = new ArrayList<>();

    public ArrayList<UserItem> getUserDatabase() {
        return userDatabase;
    }

    private UserDatabase() {
        addUser(new UserItem("admin","",UserItem.ADMIN));
        addUser(new UserItem("user","user",UserItem.USER));
    }

    private static final UserDatabase userDb = new UserDatabase();

    public static UserDatabase getUserDb() {
        return userDb;
    }

    public UserItem getUser(String user) {
        for (UserItem usr: userDatabase) {
            if(usr.getUser().equals(user))
                return usr;
        }
        return null;
    }

    public boolean addUser(UserItem newUser) {
        for (UserItem user: userDatabase) {
            if (user.getUser().equals(newUser.getUser()))
                return true; //error user already exist.
        }
        userDatabase.add(newUser);
        return false;
    }

    public boolean loginUser(UserItem userItem){
        for (UserItem user: userDatabase) {
            if (user.equals(userItem))
                return true;
        }
        return false;
    }

}

