package io.github.pw2.ambienteservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pw2.ambienteservice.models.Ambiente;
import io.github.pw2.ambienteservice.repositories.AmbienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AmbienteserviceApplicationTests {

    @Autowired
    AmbienteRepository repository;

    final String BASE_PATH = "http://localhost:8080/ambiente";
    private RestTemplate restTemplate;
    private ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

        //repository.deleteAll();

        restTemplate = new RestTemplate();

    }

    @Test
    public void testCreateAmbiente() throws JsonProcessingException {

        Ambiente ambiente = new Ambiente("111", "sala 1");
        Ambiente ambiente2 = new Ambiente("222", "sala 2");

        Ambiente response = restTemplate.postForObject(BASE_PATH, ambiente, Ambiente.class);
        Ambiente response2 = restTemplate.postForObject(BASE_PATH, ambiente2, Ambiente.class);

        Assert.assertEquals("111", response.getCodigo());
        Assert.assertEquals("sala 1", response.getNome());

        Assert.assertEquals("222", response2.getCodigo());
        Assert.assertEquals("sala 2", response2.getNome());
    }

    @Test
    public void testFindAll() throws IOException {
        String response = restTemplate.getForObject(BASE_PATH , String.class);
        List<Ambiente> ambientes = MAPPER.readValue(response, MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Ambiente.class));


        Ambiente ambiente = restTemplate.getForObject(BASE_PATH + "/" +
                ambientes.get(0).getCodigo(), Ambiente.class);

        Assert.assertTrue(ambientes.size()>0);
        Assert.assertNotNull(ambiente);
    }

    @Test
    public void testFindById() throws IOException {
        Ambiente ambiente = new Ambiente("333", "sala 3");
        Ambiente response = restTemplate.postForObject(BASE_PATH, ambiente, Ambiente.class);
        ambiente = restTemplate.getForObject(BASE_PATH + "/" + ambiente.getCodigo(), Ambiente.class);

        Assert.assertEquals("333", ambiente.getCodigo());
        Assert.assertEquals("sala 3", ambiente.getNome());
    }

    @Test
    public void testFindByIdNotExists() throws IOException {
        Ambiente ambiente = restTemplate.getForObject(BASE_PATH + "/" + 999, Ambiente.class);
        Assert.assertEquals(null, ambiente);
    }

    @Test
    public void testFindByIdNull() throws IOException {
        Ambiente ambiente = restTemplate.getForObject(BASE_PATH + "/" + null, Ambiente.class);
        Assert.assertEquals(null, ambiente);
        //Assert.assertEquals("333", ambiente.getCodigo());
        //Assert.assertEquals("sala 3", ambiente.getNome());
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateAmbienteAtributeCodigoNull() throws JsonProcessingException {
        Ambiente ambiente = new Ambiente(null, "sala 2");
        Ambiente response = restTemplate.postForObject(BASE_PATH, ambiente, Ambiente.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateAmbienteAtributeNomeNull() throws JsonProcessingException {
        Ambiente ambiente = new Ambiente("123",null);
        Ambiente response = restTemplate.postForObject(BASE_PATH, ambiente, Ambiente.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateAmbienteNull() throws JsonProcessingException {
        Ambiente ambiente = null;
        Ambiente response = restTemplate.postForObject(BASE_PATH, ambiente, Ambiente.class);
    }

    @Test(expected = HttpServerErrorException.class)
    public void testCreateAmbienteDuplicateNome() throws JsonProcessingException {
        Ambiente ambiente = new Ambiente("000", "sala 1");
        Ambiente response = restTemplate.postForObject(BASE_PATH, ambiente, Ambiente.class);
    }

}
