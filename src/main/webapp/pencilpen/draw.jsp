<!DOCTYPE html>
<html lang="en" dir="ltr" ng-app="myApp">

  <jsp:include page="pencilpen/parts/head.jsp"/>

  <body>
    <jsp:include page="pencilpen/parts/menu.jsp"/>

    <div id="panel-wraper">

      <canvas id="canvas" width="520" height="520"></canvas>
      <div id="all-panel">
        <div id="colors">
          <div class="palete-block" style="background-color: #de4d44"></div>
          <div class="palete-block" style="background-color: #9e3743"></div>
          <div class="palete-block" style="background-color: #ff842a"></div>
          <div class="palete-block" style="background-color: #fc766a"></div>
          <div class="palete-block" style="background-color: #c83e74"></div>
          <div class="palete-block" style="background-color: #909549"></div>
        </div>
        <div id="colors">
          <div class="palete-block" style="background-color: #fed65e"></div>
          <div class="palete-block" style="background-color: #2e5c9f"></div>
          <div class="palete-block" style="background-color: #755841"></div>
          <div class="palete-block" style="background-color: #daa03c"></div>
          <div class="palete-block" style="background-color: #686943"></div>
          <div class="palete-block" style="background-color: #e7b7ce"></div>
        </div>
        <div id="colors">
          <div class="palete-block" style="background-color: #d7c39d"></div>
          <div class="palete-block" style="background-color: #3a3a50"></div>
          <div class="palete-block" style="background-color: #f2edd6"></div>
          <div class="palete-block" style="background-color: #56403d"></div>
          <div class="palete-block" style="background-color: #23ff33"></div>
          <div class="palete-block" style="background-color: #94b0c2"></div>
        </div>
        <div id="colors">
          <div class="palete-block" style="background-color: #b13e53"></div>
          <div class="palete-block" style="background-color: #f69a85"></div>
          <div class="palete-block" style="background-color: #6cc2bd"></div>
          <div class="palete-block" style="background-color: #41a6f6"></div>
          <div class="palete-block" style="background-color: #1a1c2c"></div>
          <div class="palete-block" style="background-color: #333c57"></div>
        </div>
        <div id="colors">
          <div class="palete-block" style="background-color: #5d275d"></div>
          <div class="palete-block" style="background-color: #41a6f6"></div>
          <div class="palete-block" style="background-color: #f4f4f4"></div>
          <div class="palete-block" style="background-color: #a7f070"></div>
          <div class="palete-block" style="background-color: #38b764"></div>
          <div class="palete-block" style="background-color: #257179"></div>
        </div>
        <div class="palete-panel">

          <p>Size</p>
          <input id="bigger" type="button" name="+" value="+">
            <input id="smaller" type="button" name="-" value="-"></div>
            <div id="send-panel">
              <textarea id="description" placeholder="Description" cols="40" rows="2"></textarea>
              <input type="text" name="Tag" value="Tag">
                <input type="submit" name="Submit" value="Submit"></div>
              </div>
            </div>
          </body>
        </html>
