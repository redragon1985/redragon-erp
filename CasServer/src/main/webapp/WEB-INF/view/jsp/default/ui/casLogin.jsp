<jsp:directive.include file="includes/casTop.jsp" />

<c:if test="${not pageContext.request.secure}">
  <div id="msg" class="errors">
    <h2>Non-secure Connection</h2>
    <p>You are currently accessing CAS over a non-secure connection.  Single Sign On WILL NOT WORK.  In order to have single sign on work, you MUST log in over HTTPS.</p>
  </div>
</c:if>

<div class="box" id="login">
  <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">

    <form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />
  
    <h2><spring:message code="screen.welcome.instructions" /></h2>
  
    <section class="row">
      <label for="username"><spring:message code="screen.welcome.label.netid" /></label>
      <c:choose>
        <c:when test="${not empty sessionScope.openIdLocalId}">
          <strong>${sessionScope.openIdLocalId}</strong>
          <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
        </c:when>
        <c:otherwise>
          <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
          <form:input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
        </c:otherwise>
      </c:choose>
    </section>
    
    <section class="row">
      <label for="password"><spring:message code="screen.welcome.label.password" /></label>
      <spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
      <form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
    </section>
    
    <section class="row check">
      <input id="warn" name="warn" value="true" tabindex="3" accesskey="<spring:message code="screen.welcome.label.warn.accesskey" />" type="checkbox" />
      <label for="warn"><spring:message code="screen.welcome.label.warn" /></label>
    </section>
    
    <section class="row btn-row">
      <input type="hidden" name="lt" value="${loginTicket}" />
      <input type="hidden" name="execution" value="${flowExecutionKey}" />
      <input type="hidden" name="_eventId" value="submit" />

      <input class="btn-submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" type="submit" />
      <input class="btn-reset" name="reset" accesskey="c" value="<spring:message code="screen.welcome.button.clear" />" tabindex="5" type="reset" />
    </section>
  </form:form>
</div>
<div class="wrap">
  <div class="banner-show" id="js_ban_content">
    <div class="cell bns-01">
      <div class="con"></div>
    </div>
    <div class="cell bns-02" style="display:none;">
      <div class="con"></div>
    </div>
    <div class="cell bns-03" style="display:none;">
      <div class="con"></div>
    </div>
  </div>
  <div class="banner-control" id="js_ban_button_box"> <a href="javascript:;" class="left"></a> <a href="javascript:;" class="right"></a> </div>
  <script type="text/javascript">
                ;(function(){
                    
                    var defaultInd = 0;
                    var list = $('#js_ban_content').children();
                    var count = 0;
                    var change = function(newInd, callback){
                        if(count) return;
                        count = 2;
                        $(list[defaultInd]).fadeOut(400, function(){
                            count--;
                            if(count <= 0){
                                if(start.timer) window.clearTimeout(start.timer);
                                callback && callback();
                            }
                        });
                        $(list[newInd]).fadeIn(400, function(){
                            defaultInd = newInd;
                            count--;
                            if(count <= 0){
                                if(start.timer) window.clearTimeout(start.timer);
                                callback && callback();
                            }
                        });
                    }
                    
                    var next = function(callback){
                        var newInd = defaultInd + 1;
                        if(newInd >= list.length){
                            newInd = 0;
                        }
                        change(newInd, callback);
                    }
                    
                    var start = function(){
                        if(start.timer) window.clearTimeout(start.timer);
                        start.timer = window.setTimeout(function(){
                            next(function(){
                                start();
                            });
                        }, 8000);
                    }
                    
                    start();
                    
                    $('#js_ban_button_box').on('click', 'a', function(){
                        var btn = $(this);
                        if(btn.hasClass('right')){
                            //next
                            next(function(){
                                start();
                            });
                        }
                        else{
                            //prev
                            var newInd = defaultInd - 1;
                            if(newInd < 0){
                                newInd = list.length - 1;
                            }
                            change(newInd, function(){
                                start();
                            });
                        }
                        return false;
                    });
                    
                })();
            </script>
  <div class="container">
    <div class="register-box">
      <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">

    <form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />
  
    <h2><spring:message code="screen.welcome.instructions" /></h2>
  
    <section class="row">
      <label for="username"><spring:message code="screen.welcome.label.netid" /></label>
      <c:choose>
        <c:when test="${not empty sessionScope.openIdLocalId}">
          <strong>${sessionScope.openIdLocalId}</strong>
          <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
        </c:when>
        <c:otherwise>
          <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
          <form:input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
        </c:otherwise>
      </c:choose>
    </section>
    
    <section class="row">
      <label for="password"><spring:message code="screen.welcome.label.password" /></label>
      <spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
      <form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
    </section>
    
    <section class="row check">
      <input id="warn" name="warn" value="true" tabindex="3" accesskey="<spring:message code="screen.welcome.label.warn.accesskey" />" type="checkbox" />
      <label for="warn"><spring:message code="screen.welcome.label.warn" /></label>
    </section>
    
    <section class="row btn-row">
      <input type="hidden" name="lt" value="${loginTicket}" />
      <input type="hidden" name="execution" value="${flowExecutionKey}" />
      <input type="hidden" name="_eventId" value="submit" />

      <input class="btn-submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" type="submit" />
      <input class="btn-reset" name="reset" accesskey="c" value="<spring:message code="screen.welcome.button.clear" />" tabindex="5" type="reset" />
    </section>
  </form:form>
    </div>
  </div>
</div>
<jsp:directive.include file="includes/casBottom.jsp" />