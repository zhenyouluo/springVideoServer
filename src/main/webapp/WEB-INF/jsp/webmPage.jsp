<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js" lang="">
<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>hud</title>

    <link rel="apple-touch-icon" href="/resources/apple-touch-icon.png">
    <!-- Place favicon.ico in the root directory -->


    <link rel="stylesheet" href="/resources/styles/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/resources/scripts/vendor/modernizr.js"></script>
</head>
<body>
<!--[if lt IE 10]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<div class="container">
    <div class="header">
        <ul class="nav nav-pills pull-right">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Contact</a></li>
            <li><a href="/login.html">Login</a></li>
        </ul>
        <h3 class="text-muted">webm stuff</h3>
    </div>

    <div class="content-box">

        <div class="row">
            <c:forEach items="${webms}" var="webms">
                <c:url value="${webms.image}" var="picture_url"/>
                <c:url value="${webms.path}" var="webm_url"/>


                <div class="col-md-3 col-sm-4 col-xs-12 video-col">
                    <div class="thumbnail">
                        <img src="${picture_url}" alt="">
                        <div class="caption">
                            <h3>Thumbnail label</h3>
                            <p>Ipsum dignissimos minus cumque distinctio sit? Dolore adipisci cum veniam?</p>
                            <p><a href="#" class="btn btn-primary btn-play" role="button"
                                  data-video-src="${webm_url}">
                                <span
                                    class="glyphicon glyphicon-play" aria-hidden="true">
                                </span>
                            </a> <a href="#" class="btn btn-default" role="button">Button</a></p>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>


        <nav class="pagination-wrap">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>

    </div>

    <div class="footer">
        <p>♥ Webm stuff</p>
    </div>

    <div class="video-src-container video-src-hidden">
        <video id="video-src" class="video-src" src="" loop="" autoplay="" controls=""></video>
    </div>
    <div class="video-switch-controls">
        <a href="#0" class="video-switch-control video-src-prev" role="button">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        </a>
        <a href="#0" class="video-switch-control video-src-next" role="button">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        </a>
    </div>
</div>

<!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
<script>
    (function (b, o, i, l, e, r) {
        b.GoogleAnalyticsObject = l;
        b[l] || (b[l] =
                function () {
                    (b[l].q = b[l].q || []).push(arguments)
                });
        b[l].l = +new Date;
        e = o.createElement(i);
        r = o.getElementsByTagName(i)[0];
        e.src = 'https://www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e, r)
    }(window, document, 'script', 'ga'));
    ga('create', 'UA-XXXXX-X');
    ga('send', 'pageview');
</script>

<script src="/resources/scripts/vendor.js"></script>

<script src="/resources/scripts/plugins.js"></script>

<script src="/resources/scripts/main.js"></script>


</body>
</html>
