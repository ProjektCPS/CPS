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
              .ready(function() {
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
    <div class="container">
    <div class="ui four cards">
      <div class="ui card">
        <div class="image">
          <div class="ui blurring inverted dimmer">
            <div class="content">
              <div class="center">
                <div class="ui teal button">Add Friend</div>
              </div>
            </div>
          </div>
          <img src="image/paris.jpg">
        </div>
        <div class="content">
          <div class="header">Title</div>
          <div class="meta">
            <a class="group">Meta</a>
          </div>
          <div class="description">One or two sentence description that may go to several lines</div>
        </div>
        <div class="extra content">
          <a class="right floated created">Arbitrary</a>
          <a class="friends">
            Arbitrary</a>
        </div>
      </div>
    </div>

      <div class="ui four cards">
        <div class="ui card">
          <div class="image">
            <div class="ui blurring inverted dimmer">
              <div class="content">
                <div class="center">
                  <div class="ui teal button">Add Friend</div>
                </div>
              </div>
            </div>
            <img src="image/paris.jpg">
          </div>
          <div class="content">
            <div class="header">Title</div>
            <div class="meta">
              <a class="group">Meta</a>
            </div>
            <div class="description">One or two sentence description that may go to several lines</div>
          </div>
          <div class="extra content">
            <a class="right floated created">Arbitrary</a>
            <a class="friends">
              Arbitrary</a>
          </div>
        </div>
      </div>

      <div class="ui four cards">
        <div class="ui card">
          <div class="image">
            <div class="ui blurring inverted dimmer">
              <div class="content">
                <div class="center">
                  <div class="ui teal button">Add Friend</div>
                </div>
              </div>
            </div>
            <img src="image/paris.jpg">
          </div>
          <div class="content">
            <div class="header">Title</div>
            <div class="meta">
              <a class="group">Meta</a>
            </div>
            <div class="description">One or two sentence description that may go to several lines</div>
          </div>
          <div class="extra content">
            <a class="right floated created">Arbitrary</a>
            <a class="friends">
              Arbitrary</a>
          </div>
        </div>
      </div>

      <div class="ui four cards">
        <div class="ui card">
          <div class="image">
            <div class="ui blurring inverted dimmer">
              <div class="content">
                <div class="center">
                  <div class="ui teal button">Add Friend</div>
                </div>
              </div>
            </div>
            <img src="image/paris.jpg">
          </div>
          <div class="content">
            <div class="header">Title</div>
            <div class="meta">
              <a class="group">Meta</a>
            </div>
            <div class="description">One or two sentence description that may go to several lines</div>
          </div>
          <div class="extra content">
            <a class="right floated created">Arbitrary</a>
            <a class="friends">
              Arbitrary</a>
          </div>
        </div>
      </div>
    </div>
    </body>
    </html>
  </jsp:attribute>
</mt:mainTamplate>

