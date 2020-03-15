<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %> <!-- Para  tildes, ñ y caracteres especiales como el € %-->

<petclinic:layout pageName="referees">
    <h2>
        <c:if test="${referee['new']}">New </c:if> Referee
    </h2>
    
    <form:form modelAttribute="referee" class="form-horizontal" id="add-referee-form">
      
       		<div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <petclinic:inputField label="Dni" name="dni"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Telephone" name="telephone"/>
            <petclinic:inputField label="Username" name="user.username" readonly="true"/>
            <petclinic:inputField label="Password" name="user.password"/>
        	</div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${referee['new']}">
                        <button class="btn btn-default" type="submit">Become a Referee</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Referee</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>