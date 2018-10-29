<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="box payment">
    <h1>Проводка</h1>
    <table>
        <tbody>
        <tr>
            <th class="th account">Дебет</th>
            <th class="th swap"></th>
            <th class="th account">Кредит</th>
            <th class="th summa">Сумма</th>
        </tr>
        <tr>
            <td>
                <button id="debetId" class="bttn debet in_cell" onclick="changeAccount('debetId')">Не задано</button>
            </td>
            <td>
                <button class="bttn in_cell" onclick="swapAccount()">&lt;&gt;</button>
            </td>
            <td>
                <button id="creditId" class="bttn credit in_cell" onclick="changeAccount('creditId')">Не задано</button>
            </td>
            <td>
                <input id="summa" class="summa" placeholder="Сумма"/>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="line">
        <button class="bttn create" onclick="newDocument()">Создать</button>
        <span class="whitespace"></span>
    </div>
</div>