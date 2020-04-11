<%@include file="common/header.jspf" %>
<%@include file="common/navigation.jspf" %>
<div class="container">


	<form:form method="post" commandName="todo">
		<form:hidden path="id" />
		<fieldset class="form-group">
			<form:label path="desc">Description</form:label>
			<form:input path="desc" type="text" class="form-control"
				required="required" />
			<!-- to display error message on specific field use <form errors tag, for path= desc field
	cssClass will give a WARNGIIN text look	 -->
			<form:errors path="desc" cssClass="text-warning" />
		</fieldset>


		<!-- to add TARGET DATE FIELD -->

		<fieldset class="form-group">
			<form:label path="targetDate">Target Date</form:label>
			<form:input path="targetDate" type="text" class="form-control"
				required="required" />
			<!-- to display error message on specific field use <form errors tag, for path= desc field
	cssClass will give a WARNGIIN text look	 -->
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>


		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@include file="common/footer.jspf" %>