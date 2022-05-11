package rest.security;

import hibernate.entity.User;

import java.util.Date;

public class UserPrinciple {
    private Date loginDate;
    private User user;

    public UserPrinciple(User user) {
        this.user = user;
        this.loginDate = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }


}
