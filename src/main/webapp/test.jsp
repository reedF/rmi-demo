<%@page import="com.reed.rmi.base.AppliactionContextHelper"%>
<%@page import="com.reed.rmi.base.RemoteZkListener"%>
<%@page import="com.reed.rmi.base.ClientZkListener"%>
</html>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.core.env.StandardEnvironment"%>
<%@ page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<%
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		StandardEnvironment senv = (StandardEnvironment) ctx
				.getEnvironment();
		String version = (String) senv.getPropertySources().get("version")
				.getProperty("version");
		String root = AppliactionContextHelper
				.getApplicationContext().getBean(RemoteZkListener.class).getRpcZkWatcher().root;
		String nodes = AppliactionContextHelper
				.getApplicationContext().getBean(RemoteZkListener.class)
				.getRpcZkWatcher().zk.getChildren(root, false).toString();
	%>
	<h2>
		mybatis-version:<%=version%></h2>
	<h2>
		ZK-root:<%=root%></h2>
	<h2>
		ZK-children:<%=nodes%></h2>
</body>
</html>