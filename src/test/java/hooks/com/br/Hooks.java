package hooks.com.br;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
	private static WebDriver driver;
	private static Scenario scenario;
	

	@Before
	public void IniciaBrowser(Scenario cenarioEmExecucao) {
		Hooks.scenario = cenarioEmExecucao;
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void Finalizar() {
		driver.quit();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static Scenario getScenario() {
		return scenario;
	}

}
