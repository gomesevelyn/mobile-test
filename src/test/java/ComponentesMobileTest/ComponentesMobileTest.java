package ComponentesMobileTest;


import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
//import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


//@RunWith(Parameterized.class)
public class ComponentesMobileTest {
	
	private AndroidDriver<MobileElement> driver;

	  @Before
	  @Parameters({"deviceName","udid", "platformVersion","url"})

	  public void inicializarAppium(String deviceName, String udid,String platformVersion,String url) throws MalformedURLException {
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", deviceName);
		desiredCapabilities.setCapability("udid", udid);
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability("platformVersion", platformVersion);
	    desiredCapabilities.setCapability("automationName", "UiAutomator2");
	    
	    //instruções para que o appium faça a instalação do APK
	   // desiredCapabilities.setCapability("app", "C:\\Users\\Evely\\eclipse-workspace\\br.com.evelyn.Components\\src\\test\\resources\\CTAppium_1_2.apk");
	   //desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/Evely/eclipse-workspace/br.com.evelyn.Components/src/main/resources/CTAppium_1_2.apk");
	    

	    URL remoteUrl = new URL(url);

	    driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
	  }

	  @Test
	  public void acessaFormulario() {
	     driver.findElement(By.xpath("//*[text='Formulário']")).click();
	  }
	  
	 @Test
	 public void insereDadosFormulario() {
		 //preencher campos
		 driver.findElement(By.className("android.widget.EditText")).sendKeys("Teste Inclusão");
		 driver.findElement(By.className("android.widget.CheckBox")).click();
		 driver.findElement(By.className("android.widget.Switch")).click();
		 driver.findElement(By.className("android.widget.Snipper")).click(); //comboBox
		 driver.findElement(By.xpath("android.widget.ChekedTextViewer[@text='Nintendo Switch']")).click();
		// Assert.assertEquals("Nintendo Switch", text);
		 driver.findElement(By.xpath("//*[text='SALVAR']")).click();
		 
		 //Verificações 
		 MobileElement nome = driver.findElement(By.xpath("//android.widget.TextView[@text='Nome: Teste Inclusão']"));
		 Assert.assertEquals("Nome: Teste Inclusão", nome.getText());
		 
		 MobileElement combo = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]")); //outra forma de validacao de elementos 
		 Assert.assertEquals("Console: switch", combo.getText());
		 
		 
		 MobileElement switc = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]")); //outra forma de validacao de elementos 
		 Assert.assertTrue(switc.getText().endsWith("Off")); //valida se o switch termina com a palavra off
		 
		 MobileElement check = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]"));  
		 Assert.assertTrue(check.getText().endsWith("Marcado")); 
	 }
	  

	  @After
	  public void tearDown() {
	    driver.quit();
	  }	

}
