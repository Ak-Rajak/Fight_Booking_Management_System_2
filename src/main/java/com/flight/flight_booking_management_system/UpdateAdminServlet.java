package com.flight.flight_booking_management_system;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateAdmin")
public class UpdateAdminServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        String country = req.getParameter("country");
        String gender = req.getParameter("gender");
        String profilePhotoPath = req.getParameter("profilePhotoPath");
        String dob = req.getParameter("dob");
        String message;

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setPhoneNumber(phoneNumber);
        admin.setNationality(country);
        admin.setGender(gender);
        admin.setProfilePhotoPath(profilePhotoPath);
        admin.setDateOfBirth(dob);

        try {
            boolean isUpdated = adminDAO.updateAdmin(admin);

            if (isUpdated) {
                message = "Admin details updated successfully.";
            } else {
                message = "No admin found with the given email.";
            }
        } catch (SQLException e) {
            message = "Error occurred: " + e.getMessage();
            e.printStackTrace();
        }

        req.setAttribute("message", message);
        req.getRequestDispatcher("updateAdminSuccess.jsp").forward(req, resp);
    }
}
