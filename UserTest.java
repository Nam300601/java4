package com.lab1.test;

import com.lab1.dao.UserManager;

public class UserTest {
    public static void main(String[] args) {
        UserManager dao = new UserManager();

        System.out.println("=== 1. TEST TẤT CẢ USER ===");
        dao.findAll();

        System.out.println("\n=== 2. TEST TÌM THEO ID ===");
        dao.findById("U01");

        // Để test Thêm, Sửa, Xóa, bạn chỉ cần xóa dấu // ở đầu các dòng code bên dưới

        // System.out.println("\n=== 3. TEST THÊM MỚI ===");
        // User newUser = new User("U99", "123", "Nguyễn Văn Test", "test@fpt.edu.vn", false);
        // dao.create(newUser);

        // System.out.println("\n=== 4. TEST CẬP NHẬT ===");
        // dao.update("U99", "Đã đổi tên", "doiten@gmail.com");

        // System.out.println("\n=== 5. TEST XÓA ===");
        // dao.deleteById("U99");

        System.out.println("\n=== TEST BÀI 3 ===");
        dao.findByEmailAndRole();

        System.out.println("\n=== TEST BÀI 4 ===");
        dao.findUsersByPage();
        
        System.exit(0); // Kết thúc chương trình, đóng các luồng kết nối Hibernate
    }
}