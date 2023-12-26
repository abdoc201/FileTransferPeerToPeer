package sendfile.client;

import sendfile.client.SendFile;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 * SendingFileThread - Thread for sending files via a socket.
 */
public class SendingFileThread implements Runnable {

    protected Socket socket;
    private DataOutputStream dos;
    protected SendFile form;
    protected String file;
    protected String receiver;
    protected String sender;
    protected DecimalFormat df = new DecimalFormat("##,#00");
    private final int BUFFER_SIZE = 100;

    /**
     * Creates a new thread to send a file.
     * @param soc The socket for sending the file.
     * @param file The file path to be sent.
     * @param receiver The recipient's username.
     * @param sender The sender's username.
     * @param frm The SendFile form.
     */
    public SendingFileThread(Socket soc, String file, String receiver, String sender, SendFile frm) {
        this.socket = soc;
        this.file = file;
        this.receiver = receiver;
        this.sender = sender;
        this.form = frm;
    }

    @Override
    public void run() {
        try {
            form.disableGUI(true);
            System.out.println("Sending File..!");
            dos = new DataOutputStream(socket.getOutputStream());
            /** Write filename, recipient, username  **/
            File filename = new File(file);
            int len = (int) filename.length();
            int filesize = (int)Math.ceil(len / BUFFER_SIZE); 
            String clean_filename = filename.getName();
            dos.writeUTF("CMD_SENDFILE "+ clean_filename.replace(" ", "_") +" "+ filesize +" "+ receiver +" "+ sender);
            System.out.println("From: "+ sender);
            System.out.println("To: "+ receiver);
            /** Create a stream **/
            InputStream input = new FileInputStream(filename);
            OutputStream output = socket.getOutputStream();
 
            BufferedInputStream bis = new BufferedInputStream(input);
            byte[] buffer = new byte[BUFFER_SIZE];
            int count, percent = 0;
            while((count = bis.read(buffer)) > 0){
                percent = percent + count;
                int p = (percent / filesize);
               
                form.updateProgress(p);
                output.write(buffer, 0, count);
            }
            /* Update AttachmentForm GUI */
            form.setMyTitle("File has been sent.!");
            form.updateAttachment(false); //  Update Attachment 
            JOptionPane.showMessageDialog(form, "File sent successfully.!", "Success", JOptionPane.INFORMATION_MESSAGE);
            form.closeThis();
            /* Close file sending */
            output.flush();
            output.close();
            System.out.println("File has been sent..!");
        } catch (IOException e) {
            form.updateAttachment(false); //  Update Attachment
            System.out.println("[SendFile]: "+ e.getMessage());
        }
    }
}
