package com.lab1.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConnection {

    public static void main(String[] args) {
        System.out.println("Đang tiến hành kết nối đến CSDL SQL Server...");
        
        EntityManagerFactory factory = null;
        EntityManager em = null;
        
        try {
            // Tên "PolyOE" phải khớp tuyệt đối với <persistence-unit name="PolyOE"> trong persistence.xml
            factory = Persistence.createEntityManagerFactory("PolyOE");
            em = factory.createEntityManager();
            
            System.out.println("✅ KẾT NỐI CƠ SỞ DỮ LIỆU THÀNH CÔNG!");
            System.out.println("Cấu hình Hibernate và JPA của bạn đã hoàn toàn chính xác.");
            
        } catch (Exception e) {
            System.out.println("❌ KẾT NỐI THẤT BẠI! Hãy kiểm tra lại persistence.xml hoặc SQL Server.");
            System.out.println("Chi tiết lỗi:");
            e.printStackTrace();
        } finally {
            // Luôn nhớ đóng kết nối sau khi dùng xong để giải phóng tài nguyên
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (factory != null && factory.isOpen()) {
                factory.close();
            }
        }
    }
}