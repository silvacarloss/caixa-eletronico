package test;

import main.br.edu.ifsp.btv.CurrentApplication;
import main.br.edu.ifsp.btv.constants.LoginData;
import main.br.edu.ifsp.btv.controllers.CaixaEletronicoController;
import main.br.edu.ifsp.btv.models.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CaixaEletronicoTeste {

    LoginData loginData = new LoginData();
    CaixaEletronicoController caixaEletronicoController = new CaixaEletronicoController();

    @Test
    public void loginSuccessTest(){
        loginData.fillLoginDataUserList();

        String agency = "001";
        String cc = "001-1";
        String password = "12345";

        assertTrue(caixaEletronicoController.login(agency, cc, password));
    }

    @Test
    public void loginFailureTest(){
        loginData.fillLoginDataUserList();
        String agency = "001";
        String cc = "001-1";
        String password = "12346";

        assertFalse(caixaEletronicoController.login(agency, cc, password));
    }

    @Test
    public void withdrawWithSuccess(){
        loginData.fillLoginDataUserList();
        double withdrawValue = 100.00;
        User mockUser = new User("Carlos", "2929", "000-0", "001-1", 300.0);
        CurrentApplication.getInstance().setLoggedUser(mockUser);

        assertTrue(caixaEletronicoController.withdraw(withdrawValue));
    }

    @Test
    public void withdrawWithoutSuccess(){

    }

    @Test
    public void withdrawFailureWithoutCash(){
    	loginData.fillLoginDataUserList();
        double withdrawValue = 400.00;
        User mockUser = new User("Carlos", "2929", "000-0", "001-1", 300.0);
        CurrentApplication.getInstance().setLoggedUser(mockUser);

        assertFalse(caixaEletronicoController.withdraw(withdrawValue));
    }

    @Test
    public void successDepositCash(){
        assertTrue(caixaEletronicoController.depositCash("001", "001-1", 200.0));
    }

    @Test
    public void failureDepositCash(){
    	assertFalse(caixaEletronicoController.depositCash("002", "004-3", 200.0));
    }

    @Test
    public void successTransactionCash(){
    	loginData.fillLoginDataUserList();
        double withdrawValue = 400.00;
        User mockUser = new User("Carlos", "12345", "001-1", "001-1", 300.0);
        CurrentApplication.getInstance().setLoggedUser(mockUser);
        
    	assertTrue(caixaEletronicoController.transferCash("001", "002-1", 100.0, "12345"));
    }

    @Test
    public void failureTransactionCash(){
    	loginData.fillLoginDataUserList();
        double withdrawValue = 400.00;
        User mockUser = new User("Carlos", "2929", "001-1", "001-1", 300.0);
        CurrentApplication.getInstance().setLoggedUser(mockUser);
        
    	assertFalse(caixaEletronicoController.transferCash("003", "002-1", 100.0, "12345"));
    }
}
