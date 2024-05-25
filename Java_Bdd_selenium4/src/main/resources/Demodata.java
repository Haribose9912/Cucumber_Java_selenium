import org.testng.annotations.*;

public class Demodata {
    @DataProvider(name="custdata")
    public Object[][] logindata(){
        return new Object[][]{
            {"harish","hari@3344"},
            {"kumar","kum@123"},
            {"poweraj","powe@hjkan"}
        };
    }
    
    @Test(dataProvider="custdata")
    public void logintest(String username,String pass){
    	
        System.out.println("username: "+username+","+"Password: "+pass);
    }
}
