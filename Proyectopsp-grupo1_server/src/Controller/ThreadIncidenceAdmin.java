package Controller;

import Model.VO.Incidence;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadIncidenceAdmin extends Thread {

    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private Server s;
    private Incidence i;
    private Socket socket;
    private boolean running;

    public ThreadIncidenceAdmin(Socket socket) {
        this.socket = socket;
        s = Server.getServer();

        try {
            objectInput = new ObjectInputStream(socket.getInputStream());
            objectOutput = new ObjectOutputStream(socket.getOutputStream());
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        run();
    }

    @Override
    public void run() {

        try {
            String username = (String) objectInput.readObject();

            ArrayList<Incidence> adminIncidence = s.getAdminIncidences(username);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (running) {

            try {

                //SE LEE EL PRIMER BYTE QUE DETERMI1NA LA ACCIÓN A REALIZAR
                byte action = dataInput.readByte();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}