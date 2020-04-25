package br.com.facens.SmartTotem;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.error.ShouldBeAfterYear;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.validation.constraints.AssertTrue;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Steps {
    public static WebDriver driver;
    public static boolean control = true;
    public static int numDeSensores = 0;
    @Given("^Que o usuario esta na url (.*)$")
    public void queOUsuarioEstaNaUrl(String url){
        driver.navigate().to(url);
    }



    @Given("^O usuario preencher o campo Name com o valor (.*)$")
    public void input1(String input){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/div[1]/input")).sendKeys(input);
    }

    @Given("^O usuario preencher o campo Region com o valor (.*)$")
    public void input2(String input){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/div[2]/input")).sendKeys(input);
    }

    @Given("^O usuario preencher o campo Latitude com o valor (.*)$")
    public void input3(String input){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/div[3]/div[1]/input")).sendKeys(input);
    }

    @Given("^O usuario preencher o campo Longitude com o valor (.*)$")
    public void input4(String input){
        driver.findElement(By.id("inputLongitude")).sendKeys(input);
    }

    @Given("^O usuario clicar no botão Criar Device$")
    public void clickOn(){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/button")).click();
    }

    @Then("^Um device com nome (.*), Regiao (.*), Latitude/Longitude (.*) foi cadastrado no sistema$")
    public void validacao(String nome, String regiao, String lat_lon){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> trs= driver.findElements(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr"));
        int ultimaTr = trs.size();
        String txt_name = driver.findElement(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr["+ultimaTr+"]/td[1]")).getText();
        String txt_region = driver.findElement(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr["+ultimaTr+"]/td[2]")).getText();
        String txt_lat_lon =driver.findElement(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr["+ultimaTr+"]/td[3]")).getText();


        assertTrue(txt_name.equalsIgnoreCase(nome));
        assertTrue(txt_region.equalsIgnoreCase(regiao));
        assertTrue(txt_lat_lon.equalsIgnoreCase(lat_lon));




        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("^Que é armazenado o numero de sensores cadastrado$")
    public void given1(){
        List<WebElement> trs= driver.findElements(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr"));
        numDeSensores = trs.size();



    }

    @Then("^Nenhum valor deve ter sido adicionado na tabela$")
    public void validacao2(){
        List<WebElement> trs= driver.findElements(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr"));
        int ultimaTr = trs.size();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(numDeSensores == ultimaTr);
    }

    @Before
    public void initTest(){
        if(control) {
            System.setProperty("webdriver.chrome.driver", "C:\\Dutra\\t2\\SmartTotemAPI\\src\\test\\java\\br\\com\\facens\\SmartTotem\\chromedriver.exe");
            driver = new ChromeDriver();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        control = false;
    }

    @After
    public void finalizeTest(){
        if(control) {
            driver.quit();
        }
        driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
