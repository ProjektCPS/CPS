<%--
  Created by IntelliJ IDEA.
  User: Bugy
  Date: 20.06.2018
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>
<mt:mainTamplate title="Produkty">
  <jsp:attribute name="content">
    <html>
    <head>
        <!--- Example Javascript -->
        <script>
            $(document)
                .ready(function () {
                    $('.special.card .image').dimmer({
                        on: 'hover'
                    });
                    $('.star.rating')
                        .rating()
                    ;
                    $('.card .dimmer')
                        .dimmer({
                            on: 'hover'
                        })
                    ;
                })
            ;
        </script>
    </head>
    <body>
    <div class="group-contajner">
        <div class="ui four cards ">
            <div class="col-md-3">
                <div class="ui card card m-b-30">
                    <a href="https://www.facebook.com/">
                        <div class="image">
                            <div class="ui blurring inverted dimmer">
                                <div class="content">

                                </div>
                            </div>
                            <img src="image/paris.jpg">
                        </div>
                    </a>
                    <div class="content">
                        <div class="header">Title</div>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="ui card card m-b-30">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui teal button">Choose</div>
                                </div>
                            </div>
                        </div>
                        <img src="image/paris.jpg">
                    </div>
                    <div class="content">
                        <div class="header">Title</div>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="ui card card m-b-30">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui teal button">Choose</div>
                                </div>
                            </div>
                        </div>
                        <img src="image/paris.jpg">
                    </div>
                    <div class="content">
                        <div class="header">Title</div>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="ui card card m-b-30">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui teal button">Choose</div>
                                </div>
                            </div>
                        </div>
                        <img src="image/paris.jpg">
                    </div>
                    <div class="content">
                        <div class="header">Title</div>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="ui card m-b-30">
                    <div class="image">
                        <div class="ui blurring inverted dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui teal button">Choose</div>
                                </div>
                            </div>
                        </div>
                        <img src="image/paris.jpg">
                    </div>
                    <div class="content">
                        <div class="header">Title</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
  </jsp:attribute>
</mt:mainTamplate>

