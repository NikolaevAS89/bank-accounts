<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="box">
    <h1>Создать кассу</h1>
    <div class="line">
        <input id="newCashboxDescription" class="description" placeholder="Описание для новой кассы"/>
        <button class="bttn create" onclick="addNewCashbox()">Создать</button>
        <span class="whitespace"></span>
    </div>
</div>