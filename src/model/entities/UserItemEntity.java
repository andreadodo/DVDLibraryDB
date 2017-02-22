package model.entities;

import javax.persistence.*;

/**
 * Created by andrea on 20/02/17.
 */
@Entity
@Table(name = "UserItem", schema = "DVDCollection")
public class UserItemEntity {
    private int id;
    private String username;
    private String password;
    private int type;
    private boolean state;

    public UserItemEntity(String username, String password, int type, boolean state) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.state = state;
    }

    public UserItemEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserItemEntity() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "state", nullable = false)
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

        UserItemEntity that = (UserItemEntity) o;

        if (!username.equals(that.username)) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
