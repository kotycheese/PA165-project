<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="trips"/></jsp:attribute>
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
        <c:forEach items="${trips}" var="trip">
            <tr>
                <td><c:out value="${trip.name}"/></td>
                <td><f:formatDate value="${trip.dateFrom}" pattern="yyyy-MM-dd"/></td>
                <td><f:formatDate value="${trip.dateTo}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${trip.destination}"/></td>
                <td><c:out value="${trip.availableSpots}"/></td>
                <td><c:out value="${trip.price}"/></td>
                <td><c:out value="${trip.excursions.size()}"/></td>
                <td>
                    <my:a href="/browsing/create/${trip.id}" class="btn btn-primary"><f:message key="buy"/></my:a>
                </td>
                <td>
                    <my:a href="/browsing/view/${trip.id}" class="btn btn-primary"><f:message key="view"/></my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>