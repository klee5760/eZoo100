<jsp:include page = "header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

    <header>
    <div class = "container">

    <c:choose>
    <c:when test="${not empty message }">
    <p class = "alert ${messageClass}">${message }</p>
    <%
    session.setAttribute("message", null);
    session.setAttribute("messageClass", null);
    %>
    </c:when>
    </c:choose>

    <h1>eZoo<small>Create Feeding Schedule</small></h1>
    <hr class = "paw-primary">

    <form action = "createFeedingSchedule" method = "post" class="form-horizontal">

    <div class = "form-group">
    <label for = "scheduleID" class="col-sm-4 control-label control-label">Feeding Schedule ID</label>
    <div class = "col-sm-4">
    <input type = "number" class="form-control" id="scheduleID" name="scheduleID" placeholder = "ScheduleID" required="required"/>
    </div>
    </div>

    <div class = "form-group">
    <label for = "feedingTime" class="col-sm-4 control-label control-label">Time</label>
    <div class = "col-sm-4">
    <input type = "text" class="form-control" id="feedingTime" name="feedingTime" placeholder = "FeedingTime" required="required"/>
    </div>
    </div>

    <div class = "form-group">
    <label for = "recurrence" class="col-sm-4 control-label control-label">Recurrence</label>
    <div class = "col-sm-4">
    <input type = "text" class="form-control" id="recurrence" name="recurrence" placeholder = "Recurrence" required="required"/>
    </div>
    </div>

    <div class = "form-group">
    <label for = "food" class="col-sm-4 control-label control-label">Food</label>
    <div class = "col-sm-4">
    <input type = "text" class="form-control" id="food" name="food" placeholder = "Food" required="required"/>
    </div>
    </div>

    <div class = "form-group">
    <label for = "notes" class="col-sm-4 control-label control-label">Notes</label>
    <div class = "col-sm-4">
    <input type = "text" class="form-control" id="notes" name="notes" placeholder = "Notes"/>
    </div>
    </div>

    <div class = "form-group">
    <div class="col-sm-4 col-sm-1">
    <button type = "submit" class = "btn btn-primary">Create</button>
    </div>
    </div>
    </form>
    </div>
    </header>

    <jsp:include page = "footer.jsp" />

