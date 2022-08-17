import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class UiController {
    public Button btnLogin;
    public PasswordField txtPassword;
    public Label lblMessage;

    public boolean verifyLogin(String str){
        try{
            URL url = new URL("https://raw.githubusercontent.com/JonasNihal/Botnet/main/logins");
            Scanner sc = new Scanner(url.openStream());
            while(sc.hasNextLine()){
                String allStr = sc.nextLine();
                String[] all = allStr.split(" ");
                if(all[0].equals(str)){
                    switch (all[1]){
                        case "Owner":
                            Constant.ROLE.setup(0);
                            break;
                        case "Admin":
                            Constant.ROLE.setup(1);
                            break;
                        case "Vip":
                            Constant.ROLE.setup(2);
                            break;
                        case "Normal":
                            Constant.ROLE.setup(3);
                            break;
                        case "Free":
                            Constant.ROLE.setup(4);
                            break;
                    }
                    return true;
                }
            }
        }catch(Exception ignored){
            return false;
        }
        return false;
    }

    public void onActionbtnLogin(ActionEvent actionEvent) throws InterruptedException, IOException {
        boolean loginVerify = verifyLogin(txtPassword.getText());
        if (loginVerify) {
            btnLogin.setText("");
            lblMessage.setVisible(false);
            Main.switchScene(actionEvent,"menu.fxml");
        } else {
            btnLogin.setText("LOGIN");
            lblMessage.setText("Login Failed");
            lblMessage.setVisible(true);
        }
    }
}
