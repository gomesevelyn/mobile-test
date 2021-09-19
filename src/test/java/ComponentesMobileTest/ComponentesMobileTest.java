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
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ComponentesMobileTest {

	private AndroidDriver<MobileElement> driver;

	@Before
	public void inicializarAppium() throws MalformedURLException {

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		// Set your access credentials
		desiredCapabilities.setCapability("browserstack.user", "evelyngomes_OscVB5");
		desiredCapabilities.setCapability("browserstack.key", "qJAcyDz5GqdTqWqELAaq");

		// Specify device and os_version for testing
		desiredCapabilities.setCapability("device", "Google Pixel 3");
		desiredCapabilities.setCapability("os_version", "9.0");

		// Set other BrowserStack capabilities
		desiredCapabilities.setCapability("project", "Test Jenkins with BrowserStack");
		desiredCapabilities.setCapability("build", "Java Android");
		desiredCapabilities.setCapability("name", "first_test");

		/*
		 * desiredCapabilities.setCapability("deviceName", "emulator-5554");
		 * desiredCapabilities.setCapability("udid", "emulator-5554");
		 * desiredCapabilities.setCapability("platformName", "Android");
		 * desiredCapabilities.setCapability("platformVersion", "9.0");
		 * desiredCapabilities.setCapability("automationName", "UiAutomator2");
		 */

		// instrucao para que o appium faca a instalacao do APK
		desiredCapabilities.setCapability(MobileCapabilityType.APP,
				"/Users/Evely/eclipse-workspace/br.com.evelyn.Components/src/main/resources/CTAppium_1_2.apk");

		// Initialise the remote Webdriver using BrowserStack remote URL
		URL remoteUrl = new URL("http://hub.browserstack.com/wd/hub");
		driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);

	}

	@Test
	public void acessaFormulario() {
		driver.findElement(By.xpath("//*[text='Formul�rio']")).click();
	}

	@Test
	public void insereDadosFormulario() {
		// preencher campos
		driver.findElement(By.className("android.widget.EditText")).sendKeys("Teste Inclus�o");
		driver.findElement(By.className("android.widget.CheckBox")).click();
		driver.findElement(By.className("android.widget.Switch")).click();
		driver.findElement(By.className("android.widget.Snipper")).click(); // comboBox
		driver.findElement(By.xpath("android.widget.ChekedTextViewer[@text='Nintendo Switch']")).click();
		// Assert.assertEquals("Nintendo Switch", text);
		driver.findElement(By.xpath("//*[text='SALVAR']")).click();

		// Verifica��es 
		MobileElement nome = driver.findElement(By.xpath("//android.widget.TextView[@text='Nome: Teste Inclus�o']"));
		Assert.assertEquals("Nome: Teste Inclus�o", nome.getText());
		
		//outra forma de validacao de elementos 
		MobileElement combo = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]")); 
		Assert.assertEquals("Console: switch", combo.getText());
		
		//outra forma de validacao de elementos 
		MobileElement switc = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]")); 
		Assert.assertTrue(switc.getText().endsWith("Off")); // valida se o switch termina com a palavra off

		MobileElement check = driver
				.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]"));
		Assert.assertTrue(check.getText().endsWith("Marcado"));
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
