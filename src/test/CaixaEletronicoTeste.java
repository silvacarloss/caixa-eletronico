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

//    @Test
//    public void withdrawWithSuccess(){
//        loginData.fillLoginDataUserList();
//        double withdrawValue = 100.00;
//        User mockUser = new User("Carlos", "2929", "000-0", "001-1", 300.0);
//        CurrentApplication.getInstance().setUser(mockUser);
//
//        assertTrue(caixaEletronicoController.withdrawFromUser(withdrawValue));
//    }

    @Test
    public void withdrawWithoutSuccess(){

    }

    @Test
    public void withdrawFailureWithoutCash(){

    }

    @Test
    public void successDepositCash(){

    }

    @Test
    public void failureDepositCash(){

    }

    @Test
    public void successTransactionCash(){

    }

    @Test
    public void failureTransactionCash(){

    }

    @Test
    public void successHavingToken(){

    }

    @Test
    public void failureHavingToken(){

    }
}
