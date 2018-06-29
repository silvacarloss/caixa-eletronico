package main.br.edu.ifsp.btv.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.br.edu.ifsp.btv.CurrentApplication;
import main.br.edu.ifsp.btv.controllers.CaixaEletronicoController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WithdrawView extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JLabel lblNotasDisponveis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawView frame = new WithdrawView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String getAvailableNotes() {
		String result = "";
		for(Integer note : CurrentApplication.getInstance().getBanknotes()) {
			result += note.toString() + ", ";
		}
		return result;
	}
	
	private boolean isValidValue() {
		try {
			Double.parseDouble(txtAmount.getText().toString());
			return true;
		}catch(NumberFormatException ex) {
			System.out.println("Valor inválido");
			return false;
		}
		
	}

	/**
	 * Create the frame.
	 */
	public WithdrawView() {
		setTitle("Sacar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String availableItems = getAvailableNotes();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDigiteQuantoVoc = new JLabel("Digite quanto você deseja sacar:");
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isValidValue()) {
					double userCash = CurrentApplication.getInstance().getLoggedUser().getDisponibleValue();
					if(userCash >= Double.parseDouble(txtAmount.getText().toString())) {
						CaixaEletronicoController controller = new CaixaEletronicoController();
						boolean success = controller.withdraw(Double.parseDouble(txtAmount.getText().toString()));
						if(success) {
							HomeView home = new HomeView();
							home.show();
							dispose();
						}
					}else {
						JOptionPane.showMessageDialog(null, 
								"Saldo insuficiente", 
								"Erro", 
								JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, 
							"Digite um valor válido", 
							"Erro", 
							JOptionPane.ERROR_MESSAGE);
				}
			}			
		});
		
		lblNotasDisponveis = new JLabel("Notas disponíveis: " + availableItems);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDigiteQuantoVoc)
						.addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(173, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(215, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNotasDisponveis)
						.addComponent(btnConfirmar))
					.addGap(155))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addComponent(lblDigiteQuantoVoc)
					.addGap(28)
					.addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addComponent(btnConfirmar)
					.addGap(32)
					.addComponent(lblNotasDisponveis)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
