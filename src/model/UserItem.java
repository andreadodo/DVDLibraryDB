package model;

/**
 * Created by andrea on 14/02/17.
 */
public class UserItem {

    public static final int ADMIN = 1,
                            USER = 2;

    private static int idGlo = 1;

    private String user, password;
    private int id, type; //type [1= admin, 2= user]
    private boolean state; //state [T= Ok, F= Banned]

    protected UserItem(String user, String password, int type) {
        this.id = idGlo++;
        this.user = user;
        this.password = password;
        if(type==ADMIN||type==USER)
            this.type = type;
        else
            this.type = USER;
        this.state = true;
    }

    public UserItem(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        if(type==ADMIN||type==USER)
            this.type = type;
        else
            this.type = USER;
    }

    public boolean getState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserItem userItem = (UserItem) o;

        if (!user.equals(userItem.user)) return false;
        return password.equals(userItem.password);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", type=" + type +
                ", state=" + state +
                '}';
    }
}
