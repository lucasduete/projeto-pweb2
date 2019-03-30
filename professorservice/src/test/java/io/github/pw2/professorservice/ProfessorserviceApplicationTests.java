package io.github.pw2.professorservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pw2.professorservice.models.Professor;
import io.github.pw2.professorservice.repositories.ProfessorRepository;
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
public class ProfessorserviceApplicationTests {

    @Autowired
    ProfessorRepository repository;

    final String BASE_PATH = "http://localhost:8080/professor";
    private RestTemplate restTemplate;
    private ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

        //repository.deleteAll();

        restTemplate = new RestTemplate();

    }

    @Test
    public void testCreateProfessor() throws JsonProcessingException {
        Professor professor = new Professor("111", "Diego");
        Professor response = restTemplate.postForObject(BASE_PATH, professor, Professor.class);
        Assert.assertEquals("111", response.getMatricula());
        Assert.assertEquals("Diego", response.getNome());
    }

    @Test
    public void testFindAll() throws IOException {
        String response = restTemplate.getForObject(BASE_PATH , String.class);
        List<Professor> professores = MAPPER.readValue(response, MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Professor.class));


        Professor ambiente = restTemplate.getForObject(BASE_PATH + "/" +
                professores.get(0).getMatricula(), Professor.class);

        Assert.assertTrue(professores.size()>0);
        Assert.assertNotNull(ambiente);
    }

    @Test
    public void testFindByIdNotExists() throws IOException {
        Professor professor = restTemplate.getForObject(BASE_PATH + "/" + 999, Professor.class);
        Assert.assertEquals(null, professor);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testFindByIdNull() throws IOException {
        Professor professor = restTemplate.getForObject(BASE_PATH + "/" + null, Professor.class);
        Assert.assertEquals(null, professor);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateAtributeMatriculaNull() throws JsonProcessingException {
        Professor professor = new Professor(null, "Paulo");
        Professor response = restTemplate.postForObject(BASE_PATH, professor, Professor.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateProfessorAtributeNomeNull() throws JsonProcessingException {
        Professor professor = new Professor("222",null);
        Professor response = restTemplate.postForObject(BASE_PATH, professor, Professor.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testCreateProfessorNull() throws JsonProcessingException {
        Professor professor = null;
        Professor response = restTemplate.postForObject(BASE_PATH, professor, Professor.class);
    }

}
