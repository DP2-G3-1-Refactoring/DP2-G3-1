<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %> <!-- Para  tildes, ñ y caracteres especiales como el € %-->

<petclinic:layout pageName="compAdminRequestDetails">

    <h2><fmt:message key="code.title.compadminrequestdetails"/></h2>


    <table class="table table-striped">
        <tr>
            <th><fmt:message key="code.label.compadminrequestdetails.title"/></th>
            <td><b><c:out value="${compAdminRequest.title}"/></b></td>
        </tr>
        <tr>
            <th><fmt:message key="code.label.compadminrequestdetails.description"/></th>
            <td><c:out value="${compAdminRequest.description}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="code.label.compadminrequestdetails.status"/></th>
            <td><c:out value="${compAdminRequest.status}"/></td>
        </tr>
    </table>
    
    <!-- PREGUNTAR SI SE VA A DEJAR EDITAR/BORRAR LA PETICION
    
    <!-- Tomo el valor del nombre de usuario actual %-->  
    
	<security:authorize access="isAuthenticated()">
   		<security:authentication var="principalUsername" property="principal.username" /> 
	</security:authorize>
	
	<!-- Muestro el botón de editar si el usuario coincide con el usuario actual %-->  
	
	<c:if test="${compAdminRequest.user.username == principalUsername}">
    	<spring:url value="myCompetitionAdminRequest/edit" var="editUrl">  	</spring:url>
    	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default"><fmt:message key="code.button.compadminrequestdetails.edit"/></a>
    	
    	
    	<spring:url value="/deleteCompAdminRequest" var="editUrl">  </spring:url>
    	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default2" style="color:white"><b><fmt:message key="code.button.compadminrequestdetails.delete"/></b></a>
    	
    </c:if>  
     
    <br/>
    <br/>
    <br/>
    
</petclinic:layout>
