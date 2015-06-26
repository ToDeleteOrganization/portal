<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<h3>Extra Days History</h3>

<c:choose>
	<c:when test="${not empty extraDaysList}">
		<div style="overflow-y:Auto;height:150px;width:100%;overflow-x:hidden">
			<table class="cancelVacationTable" style="text-align:center">
				<tr>
					<th style="min-width:70px;text-align:center"><liferay-ui:message key="days-no"/></th>
					<th style="min-width:70px"><liferay-ui:message key="description"/></th>
					<th style="min-width:70px"><liferay-ui:message key="allocated-date"/></th>
				</tr>
				<c:forEach var="extraDayItem" items="${extraDaysList}">
					<tr>
						<td>
							${extraDayItem.getDaysNo()}
						</td>
						<td>
							${extraDayItem.getComment()}
						</td>
						<td>
							<fmt:formatDate type="date" value="${extraDayItem.getAllocatedDate()}" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:otherwise>
		<p>No extra days history available</p>
	</c:otherwise>
</c:choose>
