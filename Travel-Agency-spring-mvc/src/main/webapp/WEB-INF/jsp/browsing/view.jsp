<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="trip"/></jsp:attribute>
<jsp:attribute name="body">
    <table class="table">
        <thead>
        <tr>
            <th><f:message key="trip.name"/></th>
            <th><f:message key="trip.from"/></th>
            <th><f:message key="trip.to"/></th>
            <th><f:message key="trip.destination"/></th>
            <th><f:message key="trip.availableSpots"/></th>
            <th><f:message key="trip.price"/></th>
            <th><f:message key="trip.nExcursions"/></th>
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
                <td><c:out value="${trip.excursions.size()}"/></td>
            </tr>
        </tbody>
    </table>

    <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10">
                    <h2><f:message key="excursions"/></h1>
            </div>

    <table class="table">
            <thead>
            <tr>
                <th><f:message key="excursion.id"/></th>
                <th><f:message key="excursion.excursionDate"/></th>
                <th><f:message key="excursion.duration"/></th>
                <th><f:message key="excursion.destination"/></th>
                <th><f:message key="excursion.description"/></th>
                <th><f:message key="excursion.price"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${trip.excursions}" var="excursion">
                <tr>
                    <td><c:out value="${excursion.id}"/></td>
                    <td><f:formatDate value="${excursion.excursionDate}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${excursion.duration}"/></td>
                    <td><c:out value="${excursion.destination}"/></td>
                    <td><c:out value="${excursion.description}"/></td>
                    <td><c:out value="${excursion.price}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10">
                    <h2><f:message key="trips.next"/></h1>
            </div>


    <table class="table">
        <thead>
        <tr>
            <th><f:message key="trip.name"/></th>
            <th><f:message key="trip.from"/></th>
            <th><f:message key="trip.to"/></th>
            <th><f:message key="trip.destination"/></th>
            <th><f:message key="trip.availableSpots"/></th>
            <th><f:message key="trip.price"/></th>
            <th><f:message key="trip.nExcursions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${nextTrips}" var="trip">
            <tr>
                <td><c:out value="${trip.name}"/></td>
                <td><f:formatDate value="${trip.dateFrom}" pattern="yyyy-MM-dd"/></td>
                <td><f:formatDate value="${trip.dateTo}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${trip.destination}"/></td>
                <td><c:out value="${trip.availableSpots}"/></td>
                <td><c:out value="${trip.price}"/></td>
                <td><c:out value="${trip.excursions.size()}"/></td>
                <td>
                    <my:a href="/browsing/view/${trip.id}" class="btn btn-primary"><f:message key="view"/></my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <my:a href="/browsing/create/${trip.id}" class="btn btn-primary"><f:message key="buy"/></my:a>
</jsp:attribute>
</my:pagetemplate>