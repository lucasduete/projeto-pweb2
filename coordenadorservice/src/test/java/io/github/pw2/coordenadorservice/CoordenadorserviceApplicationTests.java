package io.github.pw2.coordenadorservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pw2.coordenadorservice.models.Coordenador;
import io.github.pw2.coordenadorservice.repositories.CoordenadorRepository;
import io.github.pw2.coordenadorservice.services.CoordenadorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
public class CoordenadorserviceApplicationTests {

    @Autowired
    CoordenadorRepository repository;

    final String BASE_PATH = "http://localhost:8080/coordenador";
    private RestTemplate restTemplate;
    private ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreateAmbiente() throws JsonProcessingException {

        Coordenador coordenador = new Coordenador("111", "rudan", "secret");

        Coordenador response = restTemplate.postForObject(BASE_PATH, coordenador, Coordenador.class);

        Assert.assertEquals("111", response.getMatricula());
        Assert.assertEquals("rudan", response.getNome());
        Assert.assertNotNull("secret", response.getSenha());
        Assert.assertFalse(""==response.getSenha());
    }

    @Test
    public void testFindAll() throws IOException {
        String response = restTemplate.getForObject(BASE_PATH , String.class);
        List<Coordenador> coordenadores = MAPPER.readValue(response, MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Coordenador.class));


        Coordenador coordenador = restTemplate.getForObject(BASE_PATH + "/" +
                coordenadores.get(0).getMatricula(), Coordenador.class);

        Assert.assertTrue(coordenadores.size()>0);
        Assert.assertNotNull(coordenador);
    }

    @Test
    public void testFindById() throws IOException {
        Coordenador coordenador = new Coordenador("222", "alexa", "secret");
        Coordenador response = restTemplate.postForObject(BASE_PATH, coordenador, Coordenador.class);
        coordenador = restTemplate.getForObject(BASE_PATH + "/" + coordenador.getMatricula(), Coordenador.class);

        Assert.assertEquals("222", response.getMatricula());
        Assert.assertEquals("alexa", response.getNome());
        Assert.assertNotNull("secret", response.getSenha());
        Assert.assertFalse(""==response.getSenha());
    }

    @Test
    public void testFindByIdNotExists() throws IOException {
        Coordenador coordenador = restTemplate.getForObject(BASE_PATH + "/" + 999, Coordenador.class);
        Assert.assertEquals(null, coordenador);
    }

    @Test
    public void testFindByIdNull() throws IOException {
        Coordenador coordenador = restTemplate.getForObject(BASE_PATH + "/" + null, Coordenador.class);
        Assert.assertEquals(null, coordenador);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateCoordenadorAtributeNull() throws JsonProcessingException {
        Coordenador coordenador = new Coordenador(null, null, null);
        Coordenador response = restTemplate.postForObject(BASE_PATH, coordenador, Coordenador.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateCoordenadorNull() throws JsonProcessingException {
        Coordenador coordenador = null;
        Coordenador response = restTemplate.postForObject(BASE_PATH, coordenador, Coordenador.class);
    }

    @Test(expected = HttpServerErrorException.class)
    public void testCreateCoordenadorDuplicateNome() throws JsonProcessingException {
        Coordenador coordenador = new Coordenador("000", "rudan", "secret");
        Coordenador response = restTemplate.postForObject(BASE_PATH, coordenador, Coordenador.class);
    }



}
