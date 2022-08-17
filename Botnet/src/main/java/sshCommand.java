import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;

public class sshCommand {
    private static sshCommand instance = null;
    private String command = null;
    private Session session = null;

    public sshCommand() {
    }

    public void setup(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public boolean sendCommand(String command) {
        this.command = command;
        try {
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();
            String responseString = responseStream.toString();
            System.out.println(responseString);
            responseStream.close();
        } catch (Exception ignored) {
            System.out.println("[!]Couldn't send command");
            return false;
        }
        return true;
    }

    public String getCommand() {
        return command;
    }

    public static sshCommand getInstance() {
        if (instance == null) {
            instance = new sshCommand();
        }
        return instance;
    }
}
