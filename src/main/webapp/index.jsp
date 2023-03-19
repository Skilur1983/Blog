<%-- 
    Document   : index
    Created on : Mar 18, 2023, 8:55:41 PM
    Author     : dmitry
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <style>
            h1{
                text-align: center;
            }
            #page{
                width:800px;
                margin:auto;
            }
            form{
                width:400px;
                margin: 20px auto;
            }
            input[type=submit]{
                margin: auto;
            }
            .list, .list td, .list th{
                margin:auto;
                border:1px solid black;
                border-collapse: collapse;
            }
            .list td, .list th{
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Hello User!</h1>
        <form method="post" action="UserAdd">
            <table>
                <tbody>
                    <tr>
                        <td><label for="name">Name</label></td>
                        <td><input id="name" type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email</label></td>
                        <td><input id="email" type="text" name="email"></td>
                    </tr>
                    <tr>
                        <td><label for="post">Post</label></td>
                        <td><input id="post" type="text" name="post"></td>
                    </tr>      
                </tbody>
            </table>
            <input type="submit" name="send" value="Send">
        </form>
        <c:if test="${users.size() > 5}">
            <<form action="CalculateStats">
                <input type="submit" name="send2" value="Statistics">
            </form>
        </c:if>
        <div id="content">
            <c:if test="${users.size() > 0}">
            <table class="list">
                <tr>
                    <th>Name</th>                  
                    <th>Email</th>     
                    <th>Post</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.getEmail()}"/></td>
                        <td><c:out value="${user.getName()}"/></td>    
                        <td><c:out value="${user.getPost()}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        </div>
    </body>
</html>
