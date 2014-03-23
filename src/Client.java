

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRoom;
	private JTextField txtEgn;
	private JTextField txtName;
	private JTextField txtEgn_1;
	private JTextField txtAddress;
	private JTextField txtRoom_1;
	private JTextField txtRoom_2;
	private JTable table;
	private ClientTableModel clientsTableModel;


	private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            socket = new Socket("localhost", 7145);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Listening on port 7145...");
        } catch (IOException e) {
            System.err.println("Could not listen on port 7145.");
            System.exit(-1);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                	 out.writeByte(0);
                     out.flush();
                     socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(335, 10, 89, 23);
		contentPane.add(btnOk);
		
		txtRoom = new JTextField();
		txtRoom.setText("Room №");
		txtRoom.setBounds(10, 11, 150, 20);
		contentPane.add(txtRoom);
		txtRoom.setColumns(10);
		
		txtEgn = new JTextField();
		txtEgn.setText("EGN");
		txtEgn.setBounds(10, 58, 150, 20);
		contentPane.add(txtEgn);
		txtEgn.setColumns(10);
		
		JButton btnOk_1 = new JButton("OK");
		btnOk_1.setBounds(335, 57, 89, 23);
		contentPane.add(btnOk_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 44, 414, 23);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 89, 414, 16);
		contentPane.add(separator_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(335, 116, 89, 23);
		contentPane.add(btnSearch);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 279, 414, 16);
		contentPane.add(separator_2);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(10, 287, 414, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEgn_1 = new JTextField();
		txtEgn_1.setText("EGN");
		txtEgn_1.setBounds(10, 318, 414, 20);
		contentPane.add(txtEgn_1);
		txtEgn_1.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setText("Address");
		txtAddress.setBounds(10, 349, 414, 20);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtRoom_1 = new JTextField();
		txtRoom_1.setText("Room");
		txtRoom_1.setBounds(10, 380, 130, 20);
		contentPane.add(txtRoom_1);
		txtRoom_1.setColumns(10);
		
		JButton btnReserve = new JButton("Reserve");
		btnReserve.setBounds(335, 379, 89, 23);
		contentPane.add(btnReserve);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 413, 414, 16);
		contentPane.add(separator_3);
		
		txtRoom_2 = new JTextField();
		txtRoom_2.setText("Room");
		txtRoom_2.setBounds(10, 426, 130, 20);
		contentPane.add(txtRoom_2);
		txtRoom_2.setColumns(10);
		
		JButton btnFree = new JButton("Free");
		btnFree.setBounds(335, 425, 89, 23);
		contentPane.add(btnFree);
		
		JLabel lblGuestInformation = new JLabel("Guest information");
		lblGuestInformation.setBounds(170, 61, 123, 14);
		contentPane.add(lblGuestInformation);
		
		JLabel lblRoomsInformation = new JLabel("Rooms information");
		lblRoomsInformation.setBounds(170, 14, 123, 14);
		contentPane.add(lblRoomsInformation);
		
		final JComboBox comboBox = new JComboBox(new String []{"1", "2", "3"}); 
		comboBox.setToolTipText("");
		comboBox.setBounds(10, 116, 89, 20);
		contentPane.add(comboBox);
		
		JLabel lblFloor = new JLabel("Floor");
		lblFloor.setBounds(41, 100, 46, 14);
		contentPane.add(lblFloor);
		
		JLabel lblBeds = new JLabel("Beds");
		lblBeds.setBounds(133, 100, 46, 14);
		contentPane.add(lblBeds);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(229, 100, 64, 14);
		contentPane.add(lblCategory);
		
		final JComboBox comboBox_1 = new JComboBox(new String [] {"2", "3", "4"});
		comboBox_1.setToolTipText("");
		comboBox_1.setBounds(109, 116, 89, 20);
		contentPane.add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox(new String [] {"PV", "SV"});
		comboBox_2.setToolTipText("");
		comboBox_2.setBounds(208, 116, 89, 20);
		contentPane.add(comboBox_2);
		table = new JTable();
		table.setBounds(10, 150, 412, 118);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 150, 412, 118);
		contentPane.add(scrollPane);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomInfo rm = null;
				try {
					out.writeByte(1);
			        out.writeInt(Integer.parseInt(txtRoom.getText()));
			        out.flush();
			        rm = (RoomInfo)in.readObject();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane, 
                new String[] {"Number: "+rm.getRoom_number(), "Floor: "+rm.getRoom_floor(), 
				"Beds: "+rm.getBeds(), "Category: "+rm.getCategory(), "Reserved: "+rm.getReserved()});
            }
        });
		
		btnOk_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientInfo cd = null;
				try {
					out.writeByte(2);
			        out.writeInt(Integer.parseInt(txtEgn.getText()));
			        out.flush();
			        cd = (ClientInfo)in.readObject();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(contentPane, 
                new String[] {"Name: " + cd.getName(), "EGN: " + cd.getEgn(), 
                		"Address: " + cd.getAddress(), "Room: " + cd.getRoom_id()});
            }
        });
		
		btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    out.writeByte(3);
                    out.writeInt(comboBox.getSelectedIndex() + 1);
                    out.writeInt(comboBox_1.getSelectedIndex() + 2);
                    out.writeUTF((comboBox_2.getSelectedIndex() == 0) ? "PV" : "SV");
                    out.flush();

                    clientsTableModel = new ClientTableModel((ArrayList<RoomInfo>) in.readObject());
                    table.setModel(clientsTableModel);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
		
		btnFree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    out.writeByte(4);
                    out.writeInt(new Integer(txtRoom_2.getText()));;
                    out.flush();
                    
                    JOptionPane.showMessageDialog(contentPane, new String[] {"Room freed!"});
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
		
		btnReserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	ClientInfo client = new ClientInfo(new Integer(txtRoom_1.getText()), txtName.getText(), new Integer(txtEgn_1.getText()), txtAddress.getText());
                    out.writeByte(5);
                	out.writeObject(client);
                    out.flush();
                    
                    JOptionPane.showMessageDialog(contentPane, new String[] {"Room reserved!"});
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
	}
	
}
