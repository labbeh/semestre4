<html>
Essai avec une expression : <%= new java.util.Date().toString() %> <br/>
<!-- Definition d'une methode d'instance -->
<%!
  public int affiche() {
    return 10;
  }
%>
<!-- appel de la methode -->
Appel de la méthode : <%= affiche() %> <br/>
<!-- Definition d'un attribut d'instance -->
<%! int a; %>
<!-- utilisation de la variable d'instance -->
<% a = 2; %>
Utilisation de la variable d'instance : <%= a %> <br/>
</html>
