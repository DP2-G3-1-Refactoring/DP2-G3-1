<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %> <!-- Para  tildes, ñ y caracteres especiales como el € %-->

<petclinic:layout pageName="Matches">
	
	<security:authorize access="isAuthenticated()">
   		<security:authentication var="principalUsername" property="principal.username" /> 
	</security:authorize>
	
	<h2 style="color:black"><fmt:message key="matchList"/></h2>

 	<table id="matchesTable" class="table table-striped">
			<thead>
       			<tr>
           			<th><fmt:message key="titleMatchList"/></th>
           			<th><fmt:message key="matchDateMatchList"/></th>
           			<th><fmt:message key="statusMatchList"/></th>
           			<th><fmt:message key="stadiumMatchList"/></th>
           			<th><fmt:message key="footballClub1MatchList"/></th>
           			<th><fmt:message key="footballClub2MatchList"/></th>
           			<th><fmt:message key="refereeMatchList"/></th>
           			<th><fmt:message key="actionsMatchList"/></th>
 				</tr>
        	</thead>
        	<tbody>
        <c:forEach items="${matches.matchesList}" var="matches">
            <tr>
                <td>
                    <c:out value="${matches.title}"/>
                </td>
                <td>
                    <c:out value="${matches.matchDate}"/>
                </td>
                <td>
                    <c:out value="${matches.matchStatus}"/>
                </td>
                <td>
                    <c:out value="${matches.stadium}"/>
                </td>
                 <td>
                    <c:out value="${matches.footballClub1.name}"/> 
                </td>
                <td>
                    <c:out value="${matches.footballClub2.name}"/>
                </td>
                <td>
                	<c:if test="${matches.referee != null}">
                   		<c:out value="${matches.referee.firstName}"/>, <c:out value="${matches.referee.lastName}"/>
                    </c:if>
                </td> 
                <td>
                  	<spring:url value="/matches/edit/${matches.id}" var='editMatchStatus'></spring:url>
                	<a href="${fn:escapeXml(editMatchStatus)}" class="btn btn-default"><fmt:message key="editMatch"/></a>
                	
                	<c:if test="${matches.referee == null}">
                		<spring:url value="/matches/refereeRequest/refereeList/${matches.id}" var='editMatchStatus'></spring:url>
                		<a href="${fn:escapeXml(editMatchStatus)}" class="btn btn-default"><fmt:message key="addRefereeMatch"/></a>    
                	</c:if>            	
                </td>
            </tr>
        </c:forEach>
        </tbody>
     </table>
</petclinic:layout>