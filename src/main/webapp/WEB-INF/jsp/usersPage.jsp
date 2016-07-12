<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
</head>
<body>
<h1>Users</h1>

<c:url var="addUrl" value="/main/users/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
  <thead style="background:#ffab00">
  <tr>
    <th>name</th>
    <th>password</th>
    <th>email</th>
    <th colspan="3"></th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${users}" var="users">
    <c:url var="editUrl" value="/main/users/edit?id=${users.id}" />
    <c:url var="deleteUrl" value="/main/users/delete?id=${users.id}" />
    <tr>
      <td><c:out value="${users.name}" /></td>
      <td><c:out value="${users.password}" /></td>
      <td><c:out value="${users.email}" /></td>
      <td><a href="${editUrl}">Edit</a></td>
      <td><a href="${deleteUrl}">Delete</a></td>
      <td><a href="${addUrl}">Add</a></td>
    </tr>
  </c:forEach>
  <div>
    <a href="/main/webm"></a>
  </div>

  </tbody>
</table>

<c:if test="${empty users}">
  There are currently no persons in the list. <a href="${addUrl}">Add</a> a user.
</c:if>


</body>
</html>