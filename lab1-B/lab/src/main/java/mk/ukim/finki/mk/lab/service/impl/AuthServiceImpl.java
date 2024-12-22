package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.model.enumerations.Role;
import mk.ukim.finki.mk.lab.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.mk.lab.model.exception.PasswordDoNotMatchException;
import mk.ukim.finki.mk.lab.model.exception.UserAlreadyExistsException;
import mk.ukim.finki.mk.lab.repository.jpa.UserRepository;
import mk.ukim.finki.mk.lab.service.AuthService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService
{

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password)
    {
        if (CustomHandler.isNullOrEmpty(username) || CustomHandler.isNullOrEmpty(password))
        {
            throw new InvalidArgumentException("Username or password is empty or null");
        }

        return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname)
    {
        if (CustomHandler.anyNullOrEmpty(username, password, repeatPassword, name, surname))
        {
            throw new InvalidArgumentException("Some argument is is empty or null");
        }

        if (!password.equals(repeatPassword))
        {
            throw new PasswordDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExistsException();
        }

        return this.userRepository.save(new User(username, password, name, surname, Role.ROLE_USER));
    }
}
