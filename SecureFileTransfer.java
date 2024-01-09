import com.jcraft.jsch.*;

public class SecureFileTransfer {

    public static void uploadFile(String username, String password, String host, int port, String localFilePath, String remoteFilePath) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(username, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put(localFilePath, remoteFilePath);
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        uploadFile("username", "password", "sftp.example.com", 22, "/path/to/local/file.txt", "/path/to/remote/file.txt");
    }
}