package com.lab1.dao;


import java.util.List;
import jakarta.persistence.EntityManager; // Đổi sang jakarta
import jakarta.persistence.TypedQuery; // Đổi sang jakarta
import com.lab1.entity.User; // Cập nhật package ở đây
import com.lab1.utils.XJPA;

public class UserDAOImpl implements UserDAO {
    EntityManager em = XJPA.getEntityManager();
    
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }

    @Override
    public List<User> findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }

    @Override
    public User findById(String id) {
        return em.find(User.class, id);
    }

    @Override
    public void create(User entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        User entity = em.find(User.class, id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}