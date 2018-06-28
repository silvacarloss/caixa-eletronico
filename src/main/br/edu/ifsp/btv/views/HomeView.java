package main.br.edu.ifsp.btv.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.br.edu.ifsp.btv.models.User;
import main.br.edu.ifsp.btv.CurrentApplication;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class HomeView extends JFrame {

	private JPanel contentPane;
	
	CurrentApplication currentApplication = CurrentApplication.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView();
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
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		User currentUser = CurrentApplication.getInstance().getLoggedUser();
		
		JLabel lblClient = new JLabel("Bem vindo a sua conta, " + currentUser.getName());
		
		JButton btnTransfer = new JButton("Transferir");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferView transfer = new TransferView();
				transfer.show();
				dispose();
			}
		});
		
		JButton btnWithdraw = new JButton("Sacar");
		
		JButton btnExtrato = new JButton("Extrato");
		
		JLabel lblAgency = new JLabel("Agência: " + currentUser.getAgency());
		
		JLabel lblCc = new JLabel("Conta: " + currentUser.getCc());
		
		JLabel lblSaldo = new JLabel("Saldo: " + Double.toString(currentUser.getDisponibleValue()));
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CurrentApplication.getInstance().setLoggedUser(null);
				LoginView loginView = new LoginView();
				dispose();
				loginView.show();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(lblClient)
							.addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
							.addComponent(btnSair))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(320)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAgency)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblSaldo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblCc, Alignment.LEADING))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(87)
							.addComponent(btnTransfer)
							.addGap(18)
							.addComponent(btnWithdraw, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnExtrato, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSair)
						.addComponent(lblClient))
					.addGap(30)
					.addComponent(lblAgency)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCc)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSaldo)
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTransfer)
						.addComponent(btnWithdraw)
						.addComponent(btnExtrato))
					.addGap(55))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
