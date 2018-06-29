	package main.br.edu.ifsp.btv;

import main.br.edu.ifsp.btv.constants.LoginData;
import main.br.edu.ifsp.btv.views.LoginView;
/**
 * Para a solução criada os dados alterados só existem em tempo de execução.
 */
public class Main {

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		LoginData loginData = new LoginData();
		loginData.fillMoneyNotes();
		loginView.show();
	}
}