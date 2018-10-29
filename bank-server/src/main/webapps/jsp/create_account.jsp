<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="box">
    <h1>Создать счет</h1>
    <div class="line">
        <input id="newAccountDescription" class="description" placeholder="Описание для нового счета"/>
        <button class="bttn create" onclick="createNewAccount()">Создать</button>
        <span class="whitespace"></span>
    </div>
</div>