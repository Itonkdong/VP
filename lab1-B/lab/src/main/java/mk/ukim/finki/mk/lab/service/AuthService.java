package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.model.exception.PasswordDoNotMatchException;
import mk.ukim.finki.mk.lab.model.exception.UserAlreadyExistsException;


public interface AuthService
{
    User login(String username,String password);
    User register(String username, String password, String repeatPassword, String name, String surname) throws PasswordDoNotMatchException, UserAlreadyExistsException;
}
