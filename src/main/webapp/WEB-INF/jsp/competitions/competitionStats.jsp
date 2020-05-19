<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %> <!-- Para  tildes, ñ y caracteres especiales como el € %-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<petclinic:layout pageName="competitionStats">
	
<jsp:body>	

	<security:authorize access="isAuthenticated()">
   		<security:authentication var="principalUsername" property="principal.username" /> 
	</security:authorize>
 
  	
  	<!-- Jugadores  -->
  
    <h2 style="color:black">Estadísticas</h2>

 	<table id="matchesTable" class="table table-striped">
			<thead>
       			<tr>
           			<th><fmt:message key="code.label.matchrecorddetail.playername"/></th>
           			<th><fmt:message key="code.label.matchrecorddetail.goals"/></th>
 				</tr>
        	</thead>
        	<tbody>
        	
        <c:set var = "index" value = "${0}"/>
        <c:forEach items="${Players}" var="player">
        	
            <tr>
               	<td>
                    <c:out value="${player.firstName}"/>, <c:out value="${player.lastName}"/>
                </td>
                <td>
                    <c:out value="${Goals[index]}"/>
                </td>
              <c:set var = "index" value = "${index + 1}"/>                             
            </tr>
            
        </c:forEach>
        </tbody>
     </table>
    
    
    <br/>
    <br/>
    <br/>
 </jsp:body> 
</petclinic:layout>
