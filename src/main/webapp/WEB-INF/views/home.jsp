<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>
    <form method="POST" action="uploadFile" enctype="multipart/form-data">
        File to upload: <input type="file" name="file"><br /> 
       <br /> <br /> 
        <input type="submit" value="Upload"> 
    </form>
     
</body>
</html>