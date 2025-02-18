package mk.ukim.finki.wpaud.repository;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public class UserJPARepository
{
    @PersistenceContext
    private final EntityManager entityManager;

    public UserJPARepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public User findById(Long id)
    {
        User user = this.entityManager.find(User.class, id);
        return user;
    }

    public User create(String username, String password)
    {
        User user = new User(username, password);
        this.entityManager.persist(user);
        return user;
    }

    public User update(Long id, String username)
    {
        User user = this.entityManager.find(User.class, id);
        user.username = username;

        this.entityManager.merge(user);

        return user;

    }

    public void remove(Long id)
    {
        User user = this.entityManager.find(User.class, id);
        this.entityManager.remove(user);
    }

    public List<User> findAll()
    {
        TypedQuery<User> typedQuery = this.entityManager.createQuery("select a from User a", User.class);
        return typedQuery.getResultList();
    }

    public User findByUsername(String username)
    {
        TypedQuery<User> namedQuery = this.entityManager.createNamedQuery("myNamedQuery", User.class);

        namedQuery.setParameter("username", username);

        return namedQuery.getSingleResult();
    }

    public List<User> findByCity(String city)
    {
        Query nativeQuery = this.entityManager.createNativeQuery(
                "select * from App_user u join Address a on a.user_id = u.id where a.city = :city ");

        nativeQuery.setParameter("city", city);

        return nativeQuery.getResultList();
    }

}
