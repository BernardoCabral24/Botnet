
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class sshConnection {
    private String username;
    private String password;
    private String host;
    private String port;
    private Session session;

    public sshConnection() {
    }
    public void setup(String username, String password, String host, String port){
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        try{
            session = new JSch().getSession(username, host, Integer.parseInt(port));
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (Exception ignored) {
            System.out.println("[!]Server didn't connect");
        }
    }

    public Session getSession() {
        return session;
    }

    public String getPassword() {
        return password;
    }

    public void closeConnection(){
        getSession().disconnect();
    }
    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }
}
