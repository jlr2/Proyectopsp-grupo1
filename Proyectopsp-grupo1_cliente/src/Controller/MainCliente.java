package Controller;

import Model.VO.Incidence;
import View.ViewClient;
import View.ViewClientLogin;
import View.WriteIncidence;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase arranca y controla la aplicación
 */
public class MainCliente implements ActionListener, ListSelectionListener, WindowListener {

    public static MainCliente controller;
    private ViewClientLogin viewClientLogin;
    private ViewClient viewClient;
    private WriteIncidence writeIncidence;
    private String mail;
    private ArrayList<Incidence> incidences;
    private static Socket s = null;
    private ObjectOutputStream oop;
    private ObjectInputStream oip;

    private MainCliente(){
    }
    public static MainCliente getClientController() {
        if (controller == null) {
            controller = new MainCliente();
        }
        return controller;
    }

    public static void main(String[] args) {
        getClientController();

        controller.viewClientLogin = new ViewClientLogin();
        controller.viewClientLogin.show();
    }







    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){

            case "Enter": startApp();break;
            case "openChat": viewClient.openChat();break;
            case "closeChat": viewClient.closeChat();break;
            case "replyIncidence": this.replyIncidence();break;
            case "newIncidence":
                                writeIncidence = new WriteIncidence();
                                writeIncidence.show();break;

            case "sendNewIncidence": this.sendIncidence();break;

        }

    }


    private void startApp(){

        int puerto = 13300;
        try {
            //CREATE SOCKET AND SEND INT 1 TO SERVER
            s = new Socket("localhost",puerto);
            DataOutputStream foutput = new DataOutputStream(s.getOutputStream());

            ArrayList<Incidence> incidences;
            foutput.writeInt(31);

            //CREATE OBJECTOUTPUT WRITER, SEND CONSULT INCIDENCE AND WAITS TO RECIEVE INCIDENCES RELATED
            oop = new ObjectOutputStream(s.getOutputStream());
            Incidence incidence = new Incidence(controller.viewClientLogin.getEmail(),"Consult");

            oop.writeObject(incidence);

            try {
                oip = new ObjectInputStream(s.getInputStream());
                try {
                    incidences = (ArrayList<Incidence>) oip.readObject();
                    if (!(incidences == null)) {
                        controller.mail = controller.viewClientLogin.getEmail();
                        controller.viewClient = new ViewClient(incidences);
                        controller.viewClientLogin.dispose();
                        controller.incidences = incidences;
                        controller.viewClient.resize(516,viewClient.getHeight());
                        controller.viewClient.show();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "EL EMAIL INTRODUCIDO YA ESTÁ CONECTADO AL SERVIDOR\nPRUEBE A CONECTARSE MÁS TARDE\n",
                                "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "EL EMAIL INTRODUCIDO YA ESTÁ CONECTADO AL SERVIDOR\nPRUEBE A CONECTARSE MÁS TARDE\n" + e.getMessage(),
                            "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }

    }



    private void replyIncidence(){
        int idx = viewClient.getSelectedIncidenceListId();
        if (idx == -1){
            JOptionPane.showMessageDialog(null, "Select an incidence to reply\n",
                    "<<MENSAJE DE ERROR:5>>", JOptionPane.ERROR_MESSAGE);
        }else if(viewClient.getTextReplyToString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Insert a reply\n",
                    "<<MENSAJE DE ERROR:4>>", JOptionPane.ERROR_MESSAGE);
        }else {
            Incidence tmpIncidence =
                    incidences.get(idx);
            tmpIncidence.setBody(tmpIncidence.getBody()+
                    "\n<------------------ "+getHour()+" -------------------->\n"+
                    viewClient.getTextReplyToString());
            try {
                incidences.set(incidences.indexOf(findById(tmpIncidence.getId())),tmpIncidence);
                viewClient.updateElement(tmpIncidence,idx);
                tmpIncidence.setType("answer");

                System.out.println(tmpIncidence.getBody());
                oop.reset();
                oop.writeObject(tmpIncidence);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error sending reply\n",
                        "<<MENSAJE DE ERROR:6>>", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void sendIncidence(){
        if (writeIncidence.getNewIncidenceBody().isEmpty()){
            JOptionPane.showMessageDialog(null, "Insert an incidence\n",
                    "<<MENSAJE DE ERROR:8>>", JOptionPane.ERROR_MESSAGE);
        }else {
            Incidence tmpIncidence = new Incidence(controller.mail,"new",writeIncidence.getNewIncidenceBody());
            try {
                oop.reset();
                oop.writeObject(tmpIncidence);
                tmpIncidence = (Incidence) oip.readObject();
                incidences.add(tmpIncidence);
                viewClient.addToList(tmpIncidence);
                writeIncidence.dispose();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Error sending incidence\n",
                        "<<MENSAJE DE ERROR:6>>", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public Incidence findById(int id){
        return incidences.stream().filter(i -> i.getId() == id).findFirst().get();
    }



    public String getHour(){
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        Date date = new Date();
        String hora = dateFormat.format(date);
        return hora;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        viewClient.setIncidencesDetail(((JList<Incidence>)e.getSource()).getSelectedValue().getBody());
    }

    //Modify windonwClosing method for sending an int -1 to server before close it.
    @Override
    public void windowClosing(WindowEvent e) {
        try {
            System.out.println("ENTRA");
            oop.writeObject(new Incidence("","-1"));
            ((JFrame)e.getSource()).dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
    @Override
    public void windowClosed(WindowEvent e) {
    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}