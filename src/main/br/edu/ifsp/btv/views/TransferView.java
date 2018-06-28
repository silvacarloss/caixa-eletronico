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

public class TransferView extends JFrame {

	private JPanel contentPane;
	private JTextField txtAgency;
	private JTextField txtCc;
	private JTextField txtValue;
	private JButton btnProsseguir;
	private JLabel lblMyValue;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferView frame = new TransferView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean hasSufficientCash() {
		double currentUserCash = CurrentApplication.getInstance().getLoggedUser().getDisponibleValue();
		try {
			double value = Double.parseDouble(txtValue.getText().toString());
		}catch(NumberFormatException ex) {
			System.out.println("Erro ao converter valores" + ex.getMessage());
			return false;
		}
		return  currentUserCash > Double.parseDouble(txtValue.getText().toString());
	}

	/**
	 * Create the frame.
	 */
	public TransferView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDestinationAgency = new JLabel("Agência do usuário destino:");
		
		JLabel lblDadosDoCreditado = new JLabel("Dados do creditado");
		
		JLabel lblCc = new JLabel("C/C (Com dígito)");
		
		CaixaEletronicoController caixaEletronico = new CaixaEletronicoController();
		
		txtAgency = new JTextField();
		txtAgency.setColumns(10);
		
		txtCc = new JTextField();
		txtCc.setColumns(10);
		
		JLabel lblValue = new JLabel("Valor: (R$)");
		
		txtValue = new JTextField();
		txtValue.setColumns(10);
		
		btnProsseguir = new JButton("Prosseguir");
		btnProsseguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean hasCash = hasSufficientCash();
				if(hasCash) {
					if(caixaEletronico.userExists(txtAgency.getText().toString(), txtCc.getText().toString())) {
						String password = JOptionPane.showInputDialog("Digite sua senha para confirmar: ");
						boolean success = caixaEletronico.transferCash(txtAgency.getText().toString(), 
								txtCc.getText().toString(), 
								Double.parseDouble(txtValue.getText().toString()), 
								password);
						if(success) {
							JOptionPane.showMessageDialog(null, 
									"Transação efetivada com sucesso.", 
									"Sucesso", 
									JOptionPane.INFORMATION_MESSAGE);
							HomeView homeView = new HomeView();
							dispose();
							homeView.show();
						}else {
							JOptionPane.showMessageDialog(null, 
									"Verifique todos os dados inseridos e tente novamente.", 
									"Erro na transferência", 
									JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, 
								"Os dados digitados não conferem com os de nenhum usuário cadastrado", 
								"Erro na transação", 
								JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, 
							"Você não possui dinheiro suficiente para esta transação", 
							"Erro na transação", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		lblMyValue = new JLabel("Meu saldo: R$ " + 
								CurrentApplication.getInstance().getLoggedUser().getDisponibleValue());
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeView homeView = new HomeView();
				dispose();
				homeView.show();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(lblDadosDoCreditado)
					.addGap(148))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtAgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDestinationAgency))
					.addContainerGap(221, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblMyValue)
							.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnProsseguir))
						.addComponent(txtValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValue)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblCc)
							.addComponent(txtCc)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDadosDoCreditado)
					.addGap(38)
					.addComponent(lblDestinationAgency)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtAgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(lblCc)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblValue)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnProsseguir)
							.addComponent(btnCancel))
						.addComponent(lblMyValue))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
