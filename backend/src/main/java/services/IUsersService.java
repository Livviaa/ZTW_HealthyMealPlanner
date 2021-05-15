package services;

import models.User;

import java.util.Collection;

public interface IUsersService {

    // CREATE
    public abstract Integer createUser(User user);

    // READ
    public abstract User getUser(Integer id);
    public abstract Collection<User> getUser();

    // UPDATE
    public abstract Integer updateUser(User user);

    // DELETE
    public abstract Integer deleteUser(Integer id);
}
