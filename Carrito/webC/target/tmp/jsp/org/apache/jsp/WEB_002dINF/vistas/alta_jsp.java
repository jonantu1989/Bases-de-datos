package org.apache.jsp.WEB_002dINF.vistas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class alta_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/vistas/includes/cabecera.jsp");
    _jspx_dependants.add("/WEB-INF/vistas/includes/pie.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\t<html>\r\n");
      out.write("\t\t<head>\r\n");
      out.write("\t\t\t<meta charset=\"UTF-8\" />\r\n");
      out.write("\t\t\t<title>Bicis</title>\r\n");
      out.write("\t\t\t<link rel=\"shortcut icon\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/img/favicon.png\" type=\"image/png\">\r\n");
      out.write("\t\t\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/css/estilos.css\" />\r\n");
      out.write("\t\t</head>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<body>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t<header>\r\n");
      out.write("\t\t\t\t<h1>Bicis</h1>\r\n");
      out.write("\t\t\t\t<h3>Venta de bicis</h3>\r\n");
      out.write("\t\t\t\t<p class=\"bienvenido\">Bienvenido ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.usuario.username}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\r\n");
      out.write("\t\t\t</header>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<nav>\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t \t <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/catalogo\">Catálogo</a></li>\r\n");
      out.write("\t\t\t\t \t <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/login\">Login</a></li>\r\n");
      out.write("\t\t\t\t\t <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/login?op=logout\">Logout</a></li>\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<ul ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/productocrud\">Mantenimiento de productos</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/productoform?op=alta\">Alta de productos</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/usuariocrud\">Mantenimiento de usuarios</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${applicationScope.rutaBase}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/usuarioform?op=alta\">Alta de usuarios</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</nav>");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("\t<h2>Alta</h2>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<form action=\"alta\" method=\"post\">\r\n");
      out.write("<fieldset>\r\n");
      out.write("<label for=\"username\">Username</label>\r\n");
      out.write("<input id=\"username\" name=\"username\"/>\r\n");
      out.write("</fieldset>\r\n");
      out.write("<fieldset>\r\n");
      out.write("<label for=\"password\">Password</label>\r\n");
      out.write("<input id=\"password\" name=\"password\" type=\"password\"/>\r\n");
      out.write("</fieldset>\r\n");
      out.write("<fieldset>\r\n");
      out.write("<label for=\"password2\">Repita el password</label>\r\n");
      out.write("<input id=\"password2\" name=\"password2\" type=\"password\"/>\r\n");
      out.write("</fieldset>\r\n");
      out.write("<fieldset>\r\n");
      out.write("<input type=\"submit\" value=\"ALTA\">\r\n");
      out.write("</fieldset>\r\n");
      out.write("</form>\r\n");
      out.write("<div>\r\n");
      out.write("<p class=\"mensaje\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.alta}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t<footer>\r\n");
      out.write("\t\t<p>&copy;2017 Jon Antuñano....</p>\r\n");
      out.write("\t</footer>\r\n");
      out.write("</body>\r\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.usuario.id_roles != '1'}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\tstyle = \"display:none\"\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
