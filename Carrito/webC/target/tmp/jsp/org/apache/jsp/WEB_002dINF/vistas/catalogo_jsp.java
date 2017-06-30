package org.apache.jsp.WEB_002dINF.vistas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class catalogo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>TIENDA DE BICIS</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h1>TIENDA DE BICIS</h1>\r\n");
      out.write("</body>\r\n");
      out.write("<h2>CATALOGO DE BICIS</h2>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t\t<li><a href=\"login\">Login</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"alta\">Alta</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"login?opcion=logout\">Salir</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<table border=\"1\">\r\n");
      out.write("\t<thead>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>Operaciones</th>\r\n");
      out.write("\t\t\t<th>ID producto</th>\r\n");
      out.write("\t\t\t<th>Nombre</th>\r\n");
      out.write("\t\t\t<th>Precio</th>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</thead>\r\n");
      out.write("\t<tbody>\r\n");
      out.write("\t\t<c:forEach items=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.productos}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" var=\"productos\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<a href=\"?op=alta&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${productos.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Alta</a>\r\n");
      out.write("\t\t\t\t\t<a href=\"?op=modificar&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${productos.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Modificar</a>\r\n");
      out.write("\t\t\t\t\t<a href=\"?op=borrar&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${productos.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Borrar</a>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${productos.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${productos.nombre}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("\t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${productos.precio}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\t\t\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</c:forEach>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t</tbody>\r\n");
      out.write("</table>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
