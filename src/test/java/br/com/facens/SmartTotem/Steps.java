package br.com.facens.SmartTotem;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.validation.constraints.AssertTrue;

public class Steps {
    public static WebDriver driver;

    @Given("^Que o usuario esta na url (.*)$")
    public void queOUsuarioEstaNaUrl(String url){
        driver.navigate().to(url);
    }



    @Given("^O usuario preencher o capo Name com o valor (.*)$")
    public void input1(String input){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/div[1]/input")).sendKeys(input);
    }

    @Given("^O usuario preencher o capo Region com o valor (.*)$")
    public void input2(String input){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/div[2]/input")).sendKeys(input);
    }

    @Given("^O usuario preencher o capo Latitude com o valor (.*)$")
    public void input3(String input){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/div[3]/div[1]/input")).sendKeys(input);
    }

    @Given("^O usuario preencher o capo Longitude com o valor (.*)$")
    public void input4(String input){
        driver.findElement(By.id("inputLongitude")).sendKeys(input);
    }

    @Given("^O usuario clicar no botão Criar Device$")
    public void clickOn(){
        driver.findElement(By.xpath("/html/body/app-root/app-home/div/div/div[1]/form/button")).click();
    }

    @Given("^Um device com nome (.*), Regiao (.*), Latitude (.*) e Longitude (.*) foi cadastrado no sistema$")
    public void validacao(String nome, String regiao, String lat, String lon){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String txt_name = driver.findElement(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr[4]/td[1]")).getText();
        String txt_region = driver.findElement(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr[4]/td[2]")).getText();
        String txt_lat = driver.findElement(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr[4]/td[3]")).getText();
        String txt_lon = driver.findElement(By.xpath("//html/body/app-root/app-home/div/div/div[1]/table/tbody/tr[4]/td[4]")).getText();
        assert(txt_name.equalsIgnoreCase(nome));
        assert(txt_region.equalsIgnoreCase(regiao));
        assert(txt_lat.equalsIgnoreCase(lat));
        assert(txt_lon.equalsIgnoreCase(lon));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Before
    public void initTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\Dutra\\t2\\SmartTotemAPI\\src\\test\\java\\br\\com\\facens\\SmartTotem\\chromedriver.exe");
        driver = new ChromeDriver();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void finalizeTest(){
        driver.quit();
    }

}
