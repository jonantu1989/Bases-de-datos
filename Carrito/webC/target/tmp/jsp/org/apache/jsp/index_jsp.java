package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>TIENDA DE BICIS</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h1>TIENDA DE BICIS</h1>\n");
      out.write("</body>\n");
      out.write("<h2>CATALOGO DE BICIS</h2>\n");
      out.write("\t<ul>\n");
      out.write("\t\t\t<li><a href=\"login\">Login</a></li>\n");
      out.write("\t\t\t<li><a href=\"alta\">Alta</a></li>\n");
      out.write("\t\t\t<li><a href=\"login?opcion=logout\">Salir</a></li>\n");
      out.write("\t\t</ul>\n");
      out.write("\t\t<table border=\"1\">\n");
      out.write("\t<thead>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<th>Operaciones</th>\n");
      out.write("\t\t\t<th>ID producto</th>\n");
      out.write("\t\t\t<th>Nombre</th>\n");
      out.write("\t\t\t<th>Precio</th>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</tr>\n");
      out.write("\t</thead>\n");
      out.write("\t<tbody>\n");
      out.write("\t\t<c:forEach items=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.productos}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" var=\"producto\">\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t<a href=\"?op=alta&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${producto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Alta</a>\n");
      out.write("\t\t\t\t\t<a href=\"?op=modificar&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${producto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Modificar</a>\n");
      out.write("\t\t\t\t\t<a href=\"?op=borrar&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${producto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Borrar</a>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${producto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${producto.nombre}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${producto.precio}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</c:forEach>\n");
      out.write("\t\t\t\t\n");
      out.write("\t</tbody>\n");
      out.write("</table>\n");
      out.write("</html>\n");
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
