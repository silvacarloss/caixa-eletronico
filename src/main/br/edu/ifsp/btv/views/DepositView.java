package main.br.edu.ifsp.btv.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class DepositView extends JFrame {

	private JPanel contentPane;
	private JTextField txtAgency;
	private JTextField txtCc;
	private JTextField txtValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositView frame = new DepositView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean canSubmit() {
		return !txtAgency.getText().toString().equals("")
				&& !txtCc.getText().toString().equals("");
	}
	
	public boolean verifyIsDouble() {
		try {
			Double.parseDouble(txtValue.getText().toString());
			return true;
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, 
					"Digite um valor válido.", 
					"Erro na leitura do valor", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Create the frame.
	 */
	public DepositView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDestinationAg = new JLabel("Agência destino:");
		
		txtAgency = new JTextField();
		txtAgency.setColumns(10);
		
		JLabel lblContaDestino = new JLabel("Conta destino:");
		
		txtCc = new JTextField();
		txtCc.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		
		txtValue = new JTextField();
		txtValue.setColumns(10);
		
		CaixaEletronicoController caixaEletronico = new CaixaEletronicoController();
		
		JButton btnProsseguir = new JButton("Prosseguir");
		btnProsseguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canSubmit() && verifyIsDouble()) {
					if(caixaEletronico.userExists(txtAgency.getText().toString(), txtCc.getText().toString())) {
						boolean success = caixaEletronico.depositCash(txtAgency.getText().toString(), 
								txtCc.getText().toString(), 
								Double.parseDouble(txtValue.getText().toString()));
						if(success) {
							JOptionPane.showMessageDialog(null, 
									"Depósito feito com sucesso", 
									"Confirmação", 
									JOptionPane.INFORMATION_MESSAGE);
							LoginView loginView = new LoginView();
							loginView.show();
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, 
									"Não foi possível realizar o depósito", 
									"Atenção", 
									JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, 
								"A conta informada não existe. Preencha novamente com uma conta válida.", 
								"Atenção", 
								JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, 
							"Preencha todos os campos se atentando também em colocar apenas números no campo de valor", 
							"Atenção", 
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JLabel lblDepsito = new JLabel("Depósito");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(311, Short.MAX_VALUE)
					.addComponent(btnProsseguir)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValor)
						.addComponent(txtCc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContaDestino)
						.addComponent(txtAgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDestinationAg))
					.addContainerGap(293, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(175)
					.addComponent(lblDepsito)
					.addContainerGap(195, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(lblDepsito)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(lblDestinationAg)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtAgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblContaDestino)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblValor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(btnProsseguir)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

}
