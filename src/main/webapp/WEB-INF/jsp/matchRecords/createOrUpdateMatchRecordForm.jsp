<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %> <!-- Para  tildes, ñ y caracteres especiales como el € %-->

<petclinic:layout pageName="matchRecord">

<jsp:body>

  <h2 class="th-center"><fmt:message key="dataPlayer"/></h2>
    		
    <form:form modelAttribute="matchRecord" class="form-horizontal" id="add-footballPlayer-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Title" name="title" />
            <petclinic:inputField label="Result" name="result"/>
            <div class="control-group">
                    <petclinic:selectField label="matchStatus" name="status" names="${matchStatus}" size="2" />
                </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${!matchRecord['new']}">
                        <button class="btn btn-default" type="submit"><fmt:message key="updateMatchRecord"/></button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit"><fmt:message key="createMatchRecord"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
    </form:form>
   
    </jsp:body>
    
    
</petclinic:layout>