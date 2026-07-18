package com.lab1.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.lab1.dao.UserDAO;
import com.lab1.dao.UserDAOImpl;
import com.lab1.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
    "/user/crud/index",
    "/user/crud/edit/*",
    "/user/crud/create",
    "/user/crud/update",
    "/user/crud/delete",
    "/user/crud/reset"
})
public class UserCRUDServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        UserDAO dao = new UserDAOImpl(); // Khởi tạo DAO
        User form = new User();
        
        try {
            BeanUtils.populate(form, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        String message = "Enter user information";
        String path = req.getServletPath();
        
        if (path.contains("edit")) {
            String id = req.getPathInfo().substring(1);
            form = dao.findById(id); // Lấy user từ DB
            message = "Edit: " + id;
        } else if (path.contains("create")) {
            dao.create(form); // Thêm vào DB
            message = "Create: " + form.getId() + " success!";
            form = new User();
        } else if (path.contains("update")) {
            dao.update(form); // Cập nhật DB
            message = "Update: " + form.getId() + " success!";
        } else if (path.contains("delete")) {
            dao.deleteById(form.getId()); // Xóa khỏi DB
            message = "Delete: " + form.getId() + " success!";
            form = new User();
        } else if (path.contains("reset")) {
            form = new User();
            message = "Form reset!";
        }
        
        // Truy vấn tất cả User từ CSDL
        List<User> list = dao.findAll();
        
        req.setAttribute("message", message);
        req.setAttribute("user", form);
        req.setAttribute("users", list);
        req.getRequestDispatcher("/pages/user-crud.jsp").forward(req, resp);
    }
}