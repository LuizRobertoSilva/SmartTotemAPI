package br.com.facens.SmartTotem;


import org.apache.coyote.Response;
import org.apache.http.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SmartTotemApplicationTests {
private CloseableHttpClient httpClient = HttpClients.createDefault();
private ConfigurableApplicationContext context = null;
private ApiUtils api = new ApiUtils("http://localhost:8080");

    @BeforeEach
    public void openAPI(){
        context = SpringApplication.run(SmartTotemApplication.class);
    }

    @AfterEach
    public void closeAPI(){
        context.close();
    }

    @Test
    @Order(1)
    void adicionarDevice()  {
        try {
            String json = api.makeJsonDevice(1,"DEVICE_0",69.2,29.1, 15,"21.3232","23.323232","TESTE2");
            HttpResponse response = api.apiPost("/devices", json);
            response.getEntity().getContent().toString();
            int code = response.getStatusLine().getStatusCode();
            assertTrue("Api retornou "+ code + " ao invés de 200",code == 200);

        } catch (IOException e) {
            e.printStackTrace();
            fail("Erro ao conectar com a API");
        }
    }

	@Test
    @Order(2)
	void tentarAdicionarUmDeviceQueJaExiste() {
        try {
            String json = api.makeJsonDevice(1,"DEVICE_1",69.2,29.1, 15,"21.3232","23.323232","TESTE2");
            HttpResponse response = api.apiPost("/devices",json);
            response = api.apiPost("/devices", json);
            int code = response.getStatusLine().getStatusCode();

            assertTrue("Api retornou "+ code + " ao invés de 500",code == 500);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Erro ao conectar com a API");
        }
	}

    @Test
    @Order(3)
    void tentarAdicionarUmDeviceComValorInvalido() {

        try {
            String json = api.makeWrongJsonDevice(1,"DEVICE_10",69.2,29.1, 15,"21.3232","23.323232","TESTE2");
            HttpResponse response = api.apiPost("/devices",json);
            int code = response.getStatusLine().getStatusCode();

            assertTrue("Api retornou "+ code + " ao invés de 400",code == 400);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Erro ao conectar com a API");
        }
    }


    @Test
    @Order(4)
	void atualizarOsDadosDeUmDevice() {

        try {
            String json = api.makeJsonDevice(1,"DEVICE_3",69.2,29.1, 15,"21.3232","23.323232","TESTE2");
            HttpResponse response = api.apiPost("/devices",json);
            int code = response.getStatusLine().getStatusCode();

            assertTrue("Api retornou "+ code + " ao invés de 200",code == 200);

            json = api.makeJsonDevice(1,"DEVICE_3",169.2,229.1, 150,"21.3232","23.323232","TESTE2");
            response = api.apiPut("/devices", json);
            code = response.getStatusLine().getStatusCode();

            assertTrue("Api retornou "+ code + " ao invés de 200",code == 200);

        } catch (IOException e) {
            e.printStackTrace();
            fail("Erro ao conectar com a API");
        }
	}

	@Test
    @Order(5)
    void listarTodoOHistorico() {

        try {
            HttpResponse response = api.apiGet("/devices/all");
            int code = response.getStatusLine().getStatusCode();

            assertTrue("Api retornou "+ code + " ao invés de 200",code == 200);

        } catch (IOException e) {
            e.printStackTrace();
            fail("Erro ao conectar com a API");
        }
    }

	@Test
    @Order(6)
    void listarHistoricoDoDevice() {
        try {
            HttpResponse response = api.apiGet("/devices/1");
            int code = response.getStatusLine().getStatusCode();

            assertTrue("Api retornou "+ code + " ao invés de 200",code == 200);

        } catch (IOException e) {
            e.printStackTrace();
            fail("Erro ao conectar com a API");
        }
	}


}
