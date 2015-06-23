package controllers;

import play.Logger;
import play.libs.OpenID;
import play.libs.OpenID.UserInfo;
import play.mvc.Controller;
import play.mvc.Before;
import play.mvc.Scope;

public class Application extends Controller {

    @Before(unless={"login", "authenticate"})
    static void checkAuthenticated() {
        if(!session.contains("user")) {
            login();
        }
    }

    public static void index() {
        render();
    }

    public static void login() {
        render();
    }

    public static void logout() {
        Scope.Session currentSession = Scope.Session.current();
        currentSession.clear();
        login();
    }


    public static void authenticate(String user) {
        if(OpenID.isAuthenticationResponse()) {
            UserInfo verifiedUser = OpenID.getVerifiedID();
            if(verifiedUser == null) {
                flash.error("Oops. Authentication has failed");
                login();
            }
            session.put("user", verifiedUser.id);
            index();
        } else {
            if(!OpenID.id(user).verify()) { // will redirect the user
                flash.error("Cannot verify your OpenID");
                login();
            }
        }
    }

}
