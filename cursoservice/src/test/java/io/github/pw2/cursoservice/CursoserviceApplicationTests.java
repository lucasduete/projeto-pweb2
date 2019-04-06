package io.github.pw2.cursoservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pw2.cursoservice.models.Curso;
import io.github.pw2.cursoservice.models.Disciplina;
import io.github.pw2.cursoservice.repositories.CursoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CursoserviceApplicationTests {

    @Autowired
    CursoRepository repository;

    final String BASE_PATH = "http://localhost:8082/curso";
    private RestTemplate restTemplate;
    private ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

        //repository.deleteAll();

        restTemplate = new RestTemplate();

    }

    @Test
    public void testCreateAmbiente() throws JsonProcessingException {
        List<Disciplina> disciplinas = new ArrayList<>();
        Disciplina disciplina = new Disciplina(111l, "disciplina 1", null);
        disciplinas.add(disciplina);

        Curso curso = new Curso(111L, "ads", "melhor curso", disciplinas);
        Curso response = restTemplate.postForObject(BASE_PATH, curso, Curso.class);

        Assert.assertEquals("ads", response.getNome());
        Assert.assertEquals("melhor curso", response.getDescricao());
    }

    @Test(expected = HttpServerErrorException.class)
    public void testCreateAmbienteWithDisCiplinaNull() throws JsonProcessingException {
        List<Disciplina> disciplinas = null;

        Curso curso = new Curso(111L, "ads", "melhor curso", disciplinas);
        Curso response = restTemplate.postForObject(BASE_PATH, curso, Curso.class);
    }

    @Test
    public void testFindAll() throws IOException {
        String response = restTemplate.getForObject(BASE_PATH , String.class);
        List<Curso> ambientes = MAPPER.readValue(response, MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Curso.class));


        Curso curso = restTemplate.getForObject(BASE_PATH + "/" +
                ambientes.get(0).getCodigo(), Curso.class);

        Assert.assertTrue(ambientes.size()>0);
        Assert.assertNotNull(curso);
    }

    @Test(expected = HttpServerErrorException.class)
    public void testFindById() throws IOException {
        List<Disciplina> disciplinas = null;
        Curso curso = new Curso(222L, "ec", "sem descricao", disciplinas);
        Curso response = restTemplate.postForObject(BASE_PATH, curso, Curso.class);
        curso = restTemplate.getForObject(BASE_PATH + "/" + curso.getCodigo(), Curso.class);

        Assert.assertEquals("222", curso.getCodigo());
        Assert.assertEquals("ec", curso.getNome());
        Assert.assertEquals("sem descricao", curso.getDescricao());
        Assert.assertEquals(1, curso.getDisciplinas().size());
    }

}
