<jsp:include page = "header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

    <header>
    <div class = "container">

    <c:choose>
    <c:when test = "${not empty message }">
    <p class = "alert ${messageClass}">${message }</p>
    <%
    session.setAttribute("message", null);
    session.setAttribute("messageClass", null);
    %>
    </c:when>
    <c:choose>

    <h1>eZoo<small>Feeding Schedules</small></h1>
    <hr class = "paw-primary">

    <table class = "table table-striped table-hover table-responsive ezoo-datatable">
    <thead>
    <tr>
    <th class = "text-center">Assign</th>
     <th class = "text-center">ScheduleID</th>
      <th class = "text-center">Feeding Time</th>
       <th class = "text-center">Recurrence</th>
        <th class = "text-center">Food</th>
         <th class = "text-center">Notes</th>
          <th class = "text-center">Animals Assigned</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="schedule" items="${feedingSchedules}">
          <tr>
          <td>
          <form action = "FSAssignment" method="post">
          <input type = "hidden" value="${schedule.scheduleID}" name="scheduleID" />
          <input type = "hidden" value="${schedule.feedingTime}" name="feedingTime" />
          <input type = "hidden" value="${schedule.recurrence}" name="recurrence" />
          <input type = "hidden" value="${schedule.food}" name="food" />
          <input type = "hidden" value="${schedule.notes}" name="notes" />
          <input type = "hidden" value="${animalID}" name="animalID" />
          <button type = "submit" class = "btn btn-primary">Assign</button>
          </form>
          </td>
          <td><fmt:formatNumber value="${schedule.scheduleID}"/></td>
          <td><c:out value="${schedule.feedingTime}" /></td>
          <td><c:out value="${schedule.recurrence}" /></td>
          <td><c:out value="${schedule.food}" /></td>
          <td><c:out value="${schedule.notes}" /></td>
          <td><c:out value="${schedule.animals}" /></td>
          </tr>
          </c:forEach>
          </tbody>
          </table>
          </div>
          </header>

          <jsp:include page = "footer.jsp" />

