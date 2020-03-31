package org.example.dao;

import org.example.entity.User;
import org.example.service.IUserService;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<User> findUser(User user) {
        String hql = "from User where email=:inputEmail and password=:inputPassword";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("inputEmail", user.getEmail());
        query.setParameter("inputPassword", user.getPassword());
        return query.list();
    }
}
