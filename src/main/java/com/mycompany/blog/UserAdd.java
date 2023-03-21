/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.blog;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author dmitry
 */
@WebServlet(name = "UserAdd", urlPatterns = {"/UserAdd"})
public class UserAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        PrintWriter pw = null;
        try{
            pw = response.getWriter();
            Class.forName("com.postgres.jdbs.Driver");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace(pw);
            pw.print(ex.getMessage());
        }
        
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:postgres://localhost:5432/postgres", "postgres", "456321");
            if (request.getParameter("email") != null){
                PreparedStatement ps = conn.prepareStatement("INSERT INTO POSTS (user_name, email, post)" + "VALUES(?, ?, ?)");
                ps.setString(1, request.getParameter("name"));
                ps.setString(2, request.getParameter("email"));
                ps.setString(3, request.getParameter("post"));
                ps.executeUpdate();
                response.sendRedirect("./");
            }
            
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM POSTS");
            List<User> users = new LinkedList<User>();
            
            while (rs.next()){
                User u = new User(rs.getString(2), rs.getString(3), rs.getString(4));
                users.add(u);
            }
            request.setAttribute("users", users);
            session.setAttribute("users", users);
            
            ApplicationContext factory = new ClassPathXmlApplicationContext("/blogSpringXMLConfig.xml");
            if(request.getParameter("name") != "" || request.getParameter("email") != "" || request.getParameter("post") != ""){
            User user = (User)factory.getBean("User");
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPost(request.getParameter("post"));
            users.add(user);
        }
        response.sendRedirect("home.jsp");
        } catch (SQLException ex) {
            pw.print(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try{
                    conn.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
                
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserAdd</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserAdd at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
