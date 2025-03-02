<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: rgb(205,117,10);	
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #ffffff;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
        	border:1px solid black;
            background: rgb(205, 89, 7);
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
    <h1>User List</h1>
    <table>
        <thead>
            <tr>
                <th>User Name</th>
                <th>User Mail</th>
                <th>country</th>
            </tr>
        </thead>
        <tbody>
        <%
        String url = "jdbc:mysql://localhost:3306/orders";
        String un = "root";
        String pass = "suguprasath@14";
        String q = "SELECT * FROM users";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            con = DriverManager.getConnection(url, un, pass);
            
            // Create a statement
            st = con.createStatement();
            
            // Execute the query
            rs = st.executeQuery(q);
            
            // Process the result set
            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getString("username") + "</td>");
                out.print("<td>" + rs.getString("usermail") + "</td>");
                out.print("<td>" + rs.getString("country") + "</td>");
                out.print("</tr>");
            }
        } catch (ClassNotFoundException e) {
            out.print("<tr><td colspan='3'>MySQL Driver not found: " + e.getMessage() + "</td></tr>");
        } catch (SQLException e) {
            out.print("<tr><td colspan='3'>SQL Error: " + e.getMessage() + "</td></tr>");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                out.print("<tr><td colspan='3'>Error closing resources: " + e.getMessage() + "</td></tr>");
            }
        }
        %>
        </tbody>
    </table>
</body>
</html>
