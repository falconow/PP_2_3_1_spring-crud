package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove( entityManager.find(User.class, id));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(Long id) {
       return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        List<User> result = entityManager.createQuery("SELECT user from User user").getResultList();
        return result;
    }
}
