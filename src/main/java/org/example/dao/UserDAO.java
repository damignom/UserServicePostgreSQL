package org.example.dao;

import org.example.models.User;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO implements Dao<User> {


    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public User getT(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(User.class, id);
        }
    }

    @Override
    public void save(User o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(o);
            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(o);
            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User o) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(o);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
