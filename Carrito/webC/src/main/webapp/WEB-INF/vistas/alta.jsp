<%@ include file="includes/cabecera.jsp"%>
<div>
	<h2>Alta</h2>
</div>

<form action="alta" method="post">
<fieldset>
<label for="username">Username</label>
<input id="username" name="username"/>
</fieldset>
<fieldset>
<label for="password">Password</label>
<input id="password" name="password" type="password"/>
</fieldset>
<fieldset>
<label for="password2">Repita el password</label>
<input id="password2" name="password2" type="password"/>
</fieldset>
<fieldset>
<input type="submit" value="ALTA">
</fieldset>
</form>
<div>
<p class="mensaje">${sessionScope.alta}</p>
</div>
<%@ include file="includes/pie.jsp"%>