package org.example;

import java.sql.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.example.EmployeeWatcher.sendEmail;

public class App {
    public static void main(String[] args) {
        System.out.println("📧 Auto email service started...");

        // Database connection info
        String jdbcURL = "jdbc:mysql://localhost:3306/company"; 
        String dbUser = "root";  
        String dbPassword = System.getenv("DB_password"); 

        // Email sender info
        String fromEmail = "tusharawale904904@gmail.com";
        String emailPassword = System.getenv("EMAIL_PASSWORD");

        if (emailPassword == null || emailPassword.isEmpty()) {
            System.err.println("❌ Email password not found. Set 'EMAIL_PASSWORD' environment variable.");
            return;
        }

        // ---  running every 1 min ---
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT employee_id, name, email FROM employees WHERE email_sent = 0")) {

                while (rs.next()) {
                    int id = rs.getInt("employee_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");

                    sendEmail(
                            "<p>Welcome " + name + "</p>",
                            "Welcome Employee ID " + id,
                            email,
                            fromEmail,
                            emailPassword,
                            null
                    );

                    stmt.executeUpdate("UPDATE employees SET email_sent = 1 WHERE employee_id = " + id);
                    System.out.println("✅ Sent email to " + name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES); // run every 1 min
    }
}
