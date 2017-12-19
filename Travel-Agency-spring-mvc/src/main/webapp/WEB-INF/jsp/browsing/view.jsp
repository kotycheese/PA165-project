<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="trip.administration"/></jsp:attribute>
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/trip/delete/${trip.id}">
        <button type="submit" class="btn btn-primary"><f:message key="delete"/></button>
    </form>


    <table class="table">
            <thead>
            <tr>
                <th><f:message key="trip.name"/></th>
                <th><f:message key="trip.from"/></th>
                <th><f:message key="trip.to"/></th>
                <th><f:message key="trip.destination"/></th>
                <th><f:message key="trip.availableSpots"/></th>
                <th><f:message key="trip.price"/></th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td><c:out value="${trip.name}"/></td>
                    <td><f:formatDate value="${trip.dateFrom}" pattern="yyyy-MM-dd"/></td>
                    <td><f:formatDate value="${trip.dateTo}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${trip.destination}"/></td>
                    <td><c:out value="${trip.availableSpots}"/></td>
                    <td><c:out value="${trip.price}"/></td>
                </tr>
            </tbody>
        </table>

    <div class="row">
        <h2><f:message key="excursions"/></h2>
    </div>
    <table class="table">
            <thead>
            <tr>
                <th><f:message key="excursion.excursionDate"/></th>
                <th><f:message key="excursion.duration"/></th>
                <th><f:message key="excursion.destination"/></th>
                <th><f:message key="excursion.price"/></th>
                <th><f:message key="excursion.included"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${trip.excursions}" var="excursion">
                <tr>
                    <td><f:formatDate value="${excursion.excursionDate}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${excursion.duration}"/></td>
                    <td><c:out value="${excursion.destination}"/></td>
                    <td><c:out value="${excursion.price}"/></td>
                    <td><c:if test="${reservation.excursions.contains(excursion)}">
                        <f:message key="yes"/>
                    </c:if></td>
                    <td>
                        <my:a href="/browsing/add/${reservation.id}/${excursion.id}" class="btn btn-primary"><f:message key="add"/></my:a>
                    </td>
                    <td>
                        <my:a href="/browsing/remove/${reservation.id}/${excursion.id}" class="btn btn-primary"><f:message key="remove"/></my:a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <form:form method="post" action="${pageContext.request.contextPath}/browsing/save"
               modelAttribute="reservation" cssClass="form-horizontal">
        <button class="btn btn-primary" type="submit"><f:message key="trip.create"/></button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>