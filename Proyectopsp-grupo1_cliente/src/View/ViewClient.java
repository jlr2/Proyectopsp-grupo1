package View;

import Controller.MainCliente;
import Model.VO.Incidence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Alexis
 */
public class ViewClient extends JFrame {

    private JButton btnChat;
    private JButton btnReply;
    private JButton btnClose;
    private JTextArea incidencesDetail;
    private JButton btnSendIncidence;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JList<Incidence> jList1;
    private JMenu jMenu1;
    private JMenuItem btnNewIncidence;
    private JMenuItem btnOpenChat;
    private JMenuItem btnCloseChat;
    private JPanel jPanel1;
    private JSeparator jSeparator1;
    private JTextArea jTextArea1;
    private JTextArea textReply;
    private JScrollPane listIncidences;
    private JMenuBar menuBar;
    private JTextArea txtChat;
    private DefaultListModel<Incidence>  listModel;


    /**
     * Creates new form ClientView
     */
    public ViewClient(ArrayList<Incidence> incidences) {
        initComponents(incidences);
    }


    private void initComponents(ArrayList<Incidence> incidences) {

        jPanel1 = new JPanel();
        listIncidences = new JScrollPane();
        jList1 = new JList<Incidence>();
        incidencesDetail = new JTextArea();
        btnSendIncidence = new JButton();
        btnChat = new JButton();
        btnReply = new JButton();
        btnClose = new JButton();
        jSeparator1 = new JSeparator();
        txtChat = new JTextArea();
        jTextArea1 = new JTextArea();
        textReply = new JTextArea();
        menuBar = new JMenuBar();
        jMenu1 = new JMenu();
        btnNewIncidence = new JMenuItem();
        btnOpenChat = new JMenuItem();
        btnCloseChat = new JMenuItem();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();

        chargeLayout(incidences);

        this.addWindowListener(MainCliente.getClientController());

        //Configure Button actions


        //CHAT BUTTONS
        btnChat.setActionCommand("sendChat");
        btnChat.addActionListener(MainCliente.getClientController());

        btnClose.setActionCommand("closeChat");
        btnClose.addActionListener(MainCliente.getClientController());

        btnOpenChat.setActionCommand("openChat");
        btnOpenChat.addActionListener(MainCliente.getClientController());


        //INCIDENCE BUTTONS
        btnReply.setActionCommand("replyIncidence");
        btnReply.addActionListener(MainCliente.getClientController());

        btnNewIncidence.setActionCommand("newIncidence");
        btnNewIncidence.addActionListener(MainCliente.getClientController());



        jList1.addListSelectionListener(MainCliente.getClientController());

    }

    public void chargeLayout(ArrayList<Incidence> incidences) {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);


        listModel = new DefaultListModel<Incidence>();
        for(Incidence i : incidences){
            listModel.addElement(i);
        }
        jList1.setModel(listModel);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listIncidences.setViewportView(jList1);


        incidencesDetail.setEditable(false);


        btnReply.setText("Send");
        btnChat.setText("Send");

        jSeparator1.setOrientation(SwingConstants.VERTICAL);

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        txtChat.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        textReply.setColumns(20);
        textReply.setLineWrap(true);
        textReply.setRows(5);
        textReply.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        textReply.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        jLabel1.setText("Incidences");

        jLabel2.setText("Detail");

        btnClose.setText("Close");


        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(listIncidences, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(incidencesDetail, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(btnReply, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(textReply))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(btnClose)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnChat))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jTextArea1)
                                                                        .addComponent(txtChat))))
                                                .addGap(12, 12, 12))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(284, 284, 284)
                                                .addComponent(jLabel2)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(incidencesDetail)
                                                        .addComponent(listIncidences, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                                .addComponent(textReply, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtChat)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextArea1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnChat)
                                                .addComponent(btnClose))
                                        .addComponent(btnReply, GroupLayout.Alignment.TRAILING))
                                .addGap(1, 1, 1))
        );

        jMenu1.setText("File");

        btnNewIncidence.setText("New Incidence");
        jMenu1.add(btnNewIncidence);

        btnOpenChat.setText("Chat Support");
        jMenu1.add(btnOpenChat);

        btnCloseChat.setText("Exit");
        jMenu1.add(btnCloseChat);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public int getSelectedIncidenceListId(){
        if (jList1.getSelectedValue() != null) {
            return jList1.getSelectedIndex();
        }else
            return -1;
    }

    public String getTextReplyToString(){
        return this.textReply.getText();
    }
    public void setIncidencesDetail(String detail){
        this.incidencesDetail.setText(detail);
    }
    public void addToList(Incidence i){
        listModel.addElement(i);
    }
    public void updateElement(Incidence i, int id){
        listModel.add(id,i);
        listModel.remove(id);
    }

    public void openChat(){
        this.resize(760,getHeight());
    }
    public void closeChat(){
        this.resize(516,getHeight());
    }
}