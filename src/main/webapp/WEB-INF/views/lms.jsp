<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Учебная платформа</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/lms-style.css">
</head>

<body>

<div class="wrapper">
    <!-- Sidebar Holder -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>Текущие Курсы</h3>
        </div>

        <ul class="list-unstyled components">
            <c:forEach var="course" items="${courses}">
                <li>
                    <a href="#course-${course.id}" data-toggle="collapse"
                       aria-expanded="false" class="dropdown-toggle">
                            ${course.title}
                    </a>
                    <div class="collapse" id="course-${course.id}">
                        <ul class="list-unstyled">
                            <c:forEach var="lesson" items="${course.lessons}" varStatus="lessonLoop">
                                <li>
                                    <a href="/lms/${lesson.id}">Урок ${lessonLoop.index + 1}: ${lesson.title}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </nav>
    <div id="content">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button type="button" id="sidebarCollapse" class="navbar-btn">
                    <span></span>
                    <span></span>
                    <span></span>
                </button>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-align-justify"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="btn btn-outline-info float-end" href="/profile">
                                <span class="fa fa-user"></span>
                                Профиль
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-outline-danger float-end" href="/logout">
                                <span class="fa fa-sign-out-alt"></span>
                                Выйти
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <c:if test="${lesson == null}">
            <h2>Урок не выбран</h2>
        </c:if>
        <c:if test="${lesson != null}">
            <h2>${lesson.title}</h2>
            <p>${lesson.description}</p>
            <c:if test="${empty lesson.homeWorks}">
                <h3>Без домашек</h3>
            </c:if>
            <ul>
                <c:forEach var="hw" items="${lesson.homeWorks}">
                    <li>
                        <h3>${hw.title}</h3>
                        <p>${hw.description}</p>
                        <p>
                            Сдать до: <fmt:formatDate pattern="dd.MM.yyyy" value="${hw.till}"/>
                        </p>
                        Попыток: ${hw.attempts}
                    </li>
                </c:forEach>
            </ul>

        </c:if>
    </div>
</div>
</body>

<!-- Font Awesome JS -->
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
        integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
        crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
        integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
        crossorigin="anonymous"></script>

<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"
        crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
            $(this).toggleClass('active');
        });
    });
</script>
</html>