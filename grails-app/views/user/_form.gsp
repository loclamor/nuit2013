<%@ page import="nuitinfo2013.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" maxlength="20" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'emailAddress', 'error')} required">
	<label for="emailAddress">
		<g:message code="user.emailAddress.label" default="Email Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="emailAddress" cols="40" rows="5" maxlength="255" required="" value="${userInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" maxlength="30" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="user.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="role" from="${nuitinfo2013.Role?.values()}" keys="${nuitinfo2013.Role.values()*.name()}" required="" value="${userInstance?.role?.name()}"/>
</div>

