package mainClasses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Serv")
public class Serv extends HttpServlet {

    @EJB
    private final Facade facade = new Facade();

    private User actualUser;

    public Serv(){
        super();
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getParameter("action");
            System.out.println("Test1");
            switch (action) {
                case "connexion": {
                    String pseudo = request.getParameter("pseudo");
                    String password = request.getParameter("password");
                    User user = facade.findUser(pseudo, password);
                    if (user == null) {
                        request.setAttribute("error", "notfound");
                        request.getRequestDispatcher("connection.jsp").forward(request, response);
                    } else {
                        this.actualUser = user;
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("personnalPage.jsp").forward(request, response);
                    }
                    break;

                }
                case "inscription": {
                    String pseudo = request.getParameter("pseudo");
                    String password = request.getParameter("password");
                    String email = request.getParameter("email");
                    facade.addUser(pseudo, email, password);
                    request.getRequestDispatcher("connection.jsp").forward(request, response);
                    break;

                }

                case "addPlaylist": {
                    facade.addPlaylist("Playlist"+Integer.toString(actualUser.getPlaylists().size()+1), actualUser);
                    request.setAttribute("user", actualUser);
                    request.getRequestDispatcher("personnalPage.jsp").forward(request, response);
                    break;
                }
            }

        } catch (PseudoInvalidException e) {
            request.setAttribute("error", "pseudoInvalid");
            request.getRequestDispatcher("inscription.jsp").forward(request, response);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
