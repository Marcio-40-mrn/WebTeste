package generics.com.br;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.org.apache.bcel.internal.generic.Select;

import cucumber.api.Scenario;

public class Generics {
	
	private WebDriver driver;
	private Scenario scenario;

	public Generics(WebDriver driver, Scenario scenario) {
		this.scenario = scenario;
		this.driver = driver;
	}

	public void aguardaElemento(WebElement elemento, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);

			if (wait.until(ExpectedConditions.visibilityOfAllElements(elemento)) == null) {
				escreveRelatorio(false, "Elemento " + elemento + " nÃ£o carregou", true);
			}
		} catch (Exception e) {
			escreveRelatorio(false, "Erro inesperado elemento " + elemento + " nÃ£o carregou", true);
		}

	}

	public void escreveRelatorio(boolean status, String msg, boolean printScreen) {
		scenario.write(msg);

		if (printScreen) {
			scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
		}

		if (status == false) {
			Assert.fail();
		}
	}

	public void click(WebElement elemento) {
		aguardaElemento(elemento, 30);

		try {
			elemento.click();

		} catch (Exception e) {
			escreveRelatorio(false, "Erro inesperado ao efetuar o click", true);
		}

	}

	public void navigateList(WebElement List, WebElement Close, List<String> Itens) throws InterruptedException {

		int totalItens = List.findElements(By.tagName("li")).size();
		List<WebElement> listItens = List.findElements(By.tagName("li"));

		for (int x = 0; x < totalItens; x++) {

			String itemText = "\"" + listItens.get(x).getText() + "\"";

			int totalList = Itens.size();

			for (int i = 0; i < totalList; i++) {
				if (itemText.equals(Itens.get(i))) {
					click(listItens.get(x));

					if (aguardaElementoVisivel(Close, 10)) {
						Close.click();
					}

					Thread.sleep(3);
					driver.navigate().refresh();
					break;
				}
			}
		}
	}

	public boolean aguardaElementoVisivel(WebElement elemento, int timeOut) {

		boolean flag = true;

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);

			if (wait.until(ExpectedConditions.visibilityOfAllElements(elemento)) == null) {
				flag = false;
			}

			return flag;

		} catch (Exception e) {
			escreveRelatorio(false, "Erro inesperado elemento " + elemento + " nÃ£o carregou", true);
			return false;
		}

	}

	public void preecheCampo(WebElement campo, String texto) {
		try {
			campo.sendKeys(texto);

		} catch (Exception e) {
			escreveRelatorio(false, "Erro inexperado nÃ£o foi possivel preencher " + campo + " com o valor " + texto,
					true);
		}

	}

	public void selecionaComboBox(WebElement comboBox, String texto) throws InterruptedException {

		try {

			comboBox.click();
			Thread.sleep(1000);
			new Select(comboBox).selectByVisibleText(texto);

		} catch (Exception e) {
			escreveRelatorio(false, "Erro inexperado nÃ£o foi possivel preencher " + comboBox + " com o valor ", true);
		}
	}

	public void validacaoMsg(WebElement elemento, String msg) {
		aguardaElemento(elemento, 20);
		try {
			String texto = elemento.getText();
			if (texto.equals(msg)) {
				escreveRelatorio(true, "Mensagem Enviada com Sucesso", true);
			} else {
				escreveRelatorio(false, "Texto atual diferente do Esperado", true);
			}

		} catch (Exception e) {
			escreveRelatorio(false, "NÃ£o Foi possivel obter o texto", true);
		}
	}

	public void fechaScript() {
		driver.switchTo().alert().dismiss();
	}

	public void localizaValorTabela(WebElement lista, String item) {

		boolean flag = false;
		int linhas = lista.findElements(By.tagName("div")).size();

		for (int i = 0; i < linhas; i++) {
			WebElement objLinha = lista.findElements(By.tagName("div")).get(i);		
				if (objLinha.getText().equals(item)) {
					flag = true;
					break;
				}
		}

		if (flag == false) {
			Assert.fail();
		}
	}
	
	public void localizaPoduto(WebElement elemento, String produto) throws InterruptedException {
		boolean flag = false;
		List<WebElement> itens = elemento.findElements(By.xpath("//div[@class='item-card card-desktop card-with-rating lazy-price item-desktop--3']"));
		for (WebElement item : itens) {
			String descricao = item.getText();
			if (descricao.contains(produto)) {
				click(item);
				Thread.sleep(5000);
				flag = true;
				break;
			}		
		}
		if (flag == true) {
			escreveRelatorio(true, "O produto "+ produto +" foi localizado", true);
		}else {
			escreveRelatorio(false, "O produto "+ produto +" nÃ£o foi localizado", true);
		}
	}
	
	public void localizaNumero(WebElement elemento, String produto) throws InterruptedException {
		boolean flag = false;
		List<WebElement> itens = elemento.findElements(By.tagName("li"));
		for (WebElement item : itens) {
			String descricao = item.getText();
			if (descricao.contains(produto)) {
				click(item);
				Thread.sleep(5000);
				flag = true;
				break;
			}		
		}
		if (flag == true) {
			escreveRelatorio(true, "O Numero "+ produto +" foi localizado", true);
		}else {
			escreveRelatorio(false, "O Numero "+ produto +" nÃ£o foi localizado", true);
		}
	}
	
	public void localizaNoCarrinho(WebElement elemento, String produto) throws InterruptedException {
		boolean flag = false;
		List<WebElement> itens = elemento.findElements(By.tagName("h3"));
		for (WebElement item : itens) {
			String descricao = item.getText();
			if (descricao.contains(produto)) {
				Thread.sleep(5000);
				flag = true;
				break;
			}		
		}
		if (flag == true) {
			escreveRelatorio(true, "O produto "+ produto +" esta no carrinho", true);
		}else {
			escreveRelatorio(false, "O produto "+ produto +" nÃ£o esta no carrinho", true);
		}
	}

}
