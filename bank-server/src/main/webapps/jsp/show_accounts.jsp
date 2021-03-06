<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="box accounts">
    <h1>Счета</h1>
    <table id="accountTable">
        <tbody>
        <tr>
            <th class="th account">Счет</th>
            <th class="th type">Тип</th>
            <th class="th summa">Остаток</th>
            <th class="th description">Описание</th>
        </tr>
        <c:if test="${not empty requestScope.accounts}">
            <c:forEach var="account" items="${requestScope.accounts}" >
                <tr>
                    <td><button class="bttn in_cell" onclick="chooseAccount(this)">${account.id}</button></td>
                    <td>${account.type.name()}</td>
                    <td><fmt:formatNumber pattern="#######0.00" type="number" value="${account.amount}"/></td>
                    <td>${account.description}</td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>