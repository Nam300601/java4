package com.lab1.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import com.lab1.entity.User;

public class UserManager {
    
    // Gọi đúng tên persistence-unit trong file persistence.xml
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    EntityManager em = factory.createEntityManager();

    // --- BÀI 2: CÁC HÀM CRUD CƠ BẢN ---
    public void findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> list = query.getResultList();
        list.forEach(user -> {
            System.out.println(user.getFullname() + " - Admin: " + user.getAdmin());
        });
    }

    public void findById(String id) {
        User user = em.find(User.class, id);
        if (user != null) {
            System.out.println("Tìm thấy: " + user.getFullname() + " - Admin: " + user.getAdmin());
        } else {
            System.out.println("Không tìm thấy user có ID: " + id);
        }
    }

    public void create(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("Thêm thành công!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Lỗi thêm mới: " + e.getMessage());
        }
    }

    public void update(String id, String newFullname, String newEmail) {
        User user = em.find(User.class, id);
        if(user != null) {
            user.setFullname(newFullname);
            user.setEmail(newEmail);
            try {
                em.getTransaction().begin();
                em.merge(user);
                em.getTransaction().commit();
                System.out.println("Cập nhật thành công!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    public void deleteById(String id) {
        User user = em.find(User.class, id);
        if(user != null) {
            try {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
                System.out.println("Xóa thành công!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    // --- BÀI 3: TÌM KIẾM THEO EMAIL VÀ ROLE ---
    public void findByEmailAndRole() {
        System.out.println("\n--- BÀI 3: KẾT QUẢ TÌM KIẾM ---");
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        
        // Truyền tham số
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false); // Không phải admin
        
        List<User> list = query.getResultList();
        list.forEach(user -> {
            System.out.println("Họ tên: " + user.getFullname() + " | Email: " + user.getEmail());
        });
    }

    // --- BÀI 4: PHÂN TRANG (PAGINATION) ---
    public void findUsersByPage() {
        System.out.println("\n--- BÀI 4: HIỂN THỊ TRANG 3 (Kích thước 5) ---");
        int pageSize = 5;
        // Trang thứ 3 -> index là 2 (vì bắt đầu từ 0: Trang 1=0, Trang 2=1, Trang 3=2)
        int pageNumber = 2; 

        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        
        // Vị trí bắt đầu = pageNumber * pageSize = 2 * 5 = 10
        query.setFirstResult(pageNumber * pageSize); 
        // Lấy tối đa 5 phần tử
        query.setMaxResults(pageSize); 
        
        List<User> list = query.getResultList();
        list.forEach(user -> {
            System.out.println(user.getId() + " | " + user.getFullname());
        });
    }
}