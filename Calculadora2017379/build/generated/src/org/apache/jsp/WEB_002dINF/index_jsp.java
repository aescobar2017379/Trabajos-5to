package org.apache.jsp.WEB_002dINF;

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

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Calculadora</title>\n");
      out.write("        <style>\n");
      out.write("            body{\n");
      out.write("                background-image: linear-gradient(180deg, #6dc918b2 0, #a5ff2fc0 16.67%, #2dff6cc7 33.33%, #06f0aa 50%, #0af0d193 66.67%, #07dffcaf 83.33%, #07dffcaf 100%);\n");
      out.write("                text-align: center;\n");
      out.write("                font-family: Century725 Cn BT;\n");
      out.write("                padding: 10%;\n");
      out.write("                font-size: 60px;\n");
      out.write("                border: 6px solid #ffffff;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <script>\n");
      out.write("            alert(\"Hola Mundo\");\n");
      out.write("            alert(\"Angel Gabriel Escobar Arevalo\");\n");
      out.write("            alert(\"2017379\");\n");
      out.write("            alert(\"IN5CV\");\n");
      out.write("            alert(\"Profesor: Víctor Álvarez\");\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <script>\n");
      out.write("            var menu = (prompt(\"Lista de Opciones\\n\"+\n");
      out.write("                                \"1. Suma\\n\"+\n");
      out.write("                                \"2. Resta\\n\"+\n");
      out.write("                                \"3. División\\n\"+\n");
      out.write("                                \"4. Multiplicación\\n\"+\n");
      out.write("                                \"5. Raíz\"));\n");
      out.write("        </script>\n");
      out.write("        \n");
      out.write("    <script>\n");
      out.write("\n");
      out.write("        switch(menu){\n");
      out.write("            case \"1\":      \n");
      out.write("                let n1 = parseFloat(prompt(\"Ingrese el primer número\"));\n");
      out.write("                let n2 = parseFloat(prompt(\"Ingrese el segundo número\"));\n");
      out.write("                let n3 = parseFloat(prompt(\"Ingrese el tercer número\"));\n");
      out.write("                let n4 = parseFloat(prompt(\"Ingrese el cuarto número\"));\n");
      out.write("                let n5 = parseFloat(prompt(\"Ingrese el quinto número\"));\n");
      out.write("                let suma = n1 + n2 + n3 + n4 + n5;\n");
      out.write("                document.write(\"La suma es:\" + \" \"+ suma);\n");
      out.write("            break;\n");
      out.write("            case \"2\":      \n");
      out.write("                let nr1 = parseFloat(prompt(\"Ingrese el primer número\"));\n");
      out.write("                let nr2 = parseFloat(prompt(\"Ingrese el segundo número\"));\n");
      out.write("                let nr3 = parseFloat(prompt(\"Ingrese el tercer número\"));\n");
      out.write("                let nr4 = parseFloat(prompt(\"Ingrese el cuarto número\"));\n");
      out.write("                let nr5 = parseFloat(prompt(\"Ingrese el quinto número\"));\n");
      out.write("                let resta = nr1 - nr2 -nr3 -nr4 - nr5;\n");
      out.write("                document.write(\"La resta es:\" + \" \"+ resta);\n");
      out.write("\n");
      out.write("            break;\n");
      out.write("\n");
      out.write("            case \"3\":       \n");
      out.write("                let nd1 = parseFloat(prompt(\"Ingrese el primer número\"));\n");
      out.write("                let nd2 = parseFloat(prompt(\"Ingrese el segundo número\"));\n");
      out.write("                let nd3 = parseFloat(prompt(\"Ingrese el tercer número\"));\n");
      out.write("                let nd4 = parseFloat(prompt(\"Ingrese el cuarto número\"));\n");
      out.write("                let nd5 = parseFloat(prompt(\"Ingrese el quinto número\"));\n");
      out.write("                let division = nd1 / nd2 / nd3 /nd4 / nd5;\n");
      out.write("                document.write(\"La división es:\" + \" \"+ division);\n");
      out.write("            break;\n");
      out.write("\n");
      out.write("            case \"4\":       \n");
      out.write("                let nm1 = parseFloat(prompt(\"Ingrese el primer número\"));\n");
      out.write("                let nm2 = parseFloat(prompt(\"Ingrese el segundo número\"));\n");
      out.write("                let nm3 = parseFloat(prompt(\"Ingrese el tercer número\"));\n");
      out.write("                let nm4 = parseFloat(prompt(\"Ingrese el cuarto número\"));\n");
      out.write("                let nm5 = parseFloat(prompt(\"Ingrese el quinto número\"));\n");
      out.write("                let multiplicacion = nm1 * nm2 * nm3 * nm4 * nm5;\n");
      out.write("                document.write(\"La Multiplicación es:\" + \" \"+ multiplicacion);\n");
      out.write("            break;\n");
      out.write("            case \"5\":\n");
      out.write("                let nra = parseFloat(prompt(\"Ingrese un número\"));\n");
      out.write("                let raiz = Math.sqrt(nra);\n");
      out.write("                document.write(\"La Raíz es:\"+ \" \"+ raiz);                \n");
      out.write("            break;\n");
      out.write("            default:\n");
      out.write("                alert(\"Opción Inválida\\n\");\n");
      out.write("                document.write(\"Refresca la página y elije correctamente\");\n");
      out.write("            break;\n");
      out.write("            \n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("\n");
      out.write("    </body>\n");
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
