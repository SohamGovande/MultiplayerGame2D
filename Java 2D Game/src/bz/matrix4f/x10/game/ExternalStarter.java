package bz.matrix4f.x10.game;

import javax.swing.JOptionPane;

import bz.matrix4f.x10.game.core.Log;
import bz.matrix4f.x10.game.core.Resources;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import bz.matrix4f.x10.game.Game;
import bz.matrix4f.x10.game.core.Display;
import bz.matrix4f.x10.game.core.GameAuth;
import bz.matrix4f.x10.game.core.GameLoop;

public class ExternalStarter {

    public static void launch(String[] args) {
        if (args.length != 1) {
            return;
        }
        String loginData = "";
        try {
            loginData = GameAuth.login(args[0], "password");
            Log.print(loginData);
            JSONObject obj = (JSONObject) JSONValue.parse(loginData);
            Game.sessionID = obj.get("session_id").toString();
            Game.loginUsername = args[0];
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to produce a SessionID to login with."
                            + "\nThis is probably because of an invalid username/password.\n"
                            + "Server returned: " + loginData,
                    "Authentication error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Display.init();
        Display.resize(Game.WIDTH, Game.HEIGHT);
        Display.center();
        Display.show();

        new Game();
        GameLoop loop = new GameLoop();
        loop.run(Display.toAWTCanvas());
    }
}
