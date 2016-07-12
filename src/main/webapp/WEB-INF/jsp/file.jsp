<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<body>

<h2>Spring MVC - Uploading a myFile.. </h2>
<form:form method="POST" commandName="myFile"	enctype="multipart/form-data">

  Upload your myFile please:
  <input type="File" name="File" />
  <input type="submit" value="upload" />
  <form:errors path="myFile" cssStyle="color: #ff0000;" />
</form:form>

</body>
</html>