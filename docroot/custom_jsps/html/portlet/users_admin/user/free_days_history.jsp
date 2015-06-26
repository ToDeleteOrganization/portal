<%-- <%@page import="com.evozon.evoportal.my_account.FreeDaysHistoryViewerAction"%> --%>
<%@page import="com.liferay.portal.model.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2 class="cancelVacationTitle">Free Days History</h2>

<c:choose>
	<c:when test="${not empty freeDaysHistory}">
		<div style="overflow-y:Auto;height:500px;width:100%;overflow-x:hidden">
			<table class="cancelVacationTable" style="text-align:center">
				<tr>
					<th style="min-width:70px;text-align:center"><liferay-ui:message key="operation-type"/></th>
					<th style="min-width:70px"><liferay-ui:message key="old-value"/></th>
					<th style="min-width:70px"><liferay-ui:message key="days-no"/></th>
					<th style="min-width:70px"><liferay-ui:message key="new-value"/></th>
					<th style="min-width:70px"><liferay-ui:message key="event-type"/></th>
					<th style="min-width:70px"><liferay-ui:message key="create-date"/></th>
					<th style="min-width:70px"><liferay-ui:message key="description"/></th>
				</tr>
				<c:forEach var="historyItem" items="${freeDaysHistory}">
					<tr>
						<td>
							${historyItem.getOperation()}
						</td>
						<td>
							${historyItem.getOldValue()}
						</td>
						<td>
							${historyItem.getDaysNo()}
						</td>
						<td>
							${historyItem.getNewValue()}
						</td>
						<td>
							${historyItem.getEventType()}
						</td>
						<td>
							<fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${historyItem.getCreateDate()}" />
						</td>
						<td>
							${historyItem.getDescription()}
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:otherwise>
		<p class="vacationMessage">No history available</p>
	</c:otherwise>
</c:choose>