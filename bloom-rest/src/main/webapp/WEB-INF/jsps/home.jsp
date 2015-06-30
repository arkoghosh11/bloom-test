<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%--
  Created by IntelliJ IDEA.
  User: Rono
  Date: 6/7/2015
  Time: 10:05 PM
  @author Rono, Ankur Bhardwaj
  @email arkoghosh@hotmail.com, meankur1@gmail.com
  @Copyright
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>
    Hello world!
</h1>
<%--Note Global Class Variable declaration --%>
<%!
    private static final Logger logger = LoggerFactory.getLogger( "home.jsp" );%>
<%-- Note try/catch block start --%>
<% try {%>
<P> The time on the server is ${serverTime}. </P>
<table align="centre">
    <tr>
        <th>Table Header</th>
    </tr>
    <tr>
        <td><core:out value="Value"/></td>
    </tr>
</table>
<% } catch ( RuntimeException exception ) {
    logger.error( "Exception occurred while rendering home view" );
    throw exception;
} finally {
    logger.info( "Home view completed" );
}
%>
</body>
</html>
