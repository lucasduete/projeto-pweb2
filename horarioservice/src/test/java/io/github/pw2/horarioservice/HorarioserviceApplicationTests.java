package io.github.pw2.horarioservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pw2.horarioservice.repositories.HorarioAcademicoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class HorarioserviceApplicationTests {

    @Autowired
    HorarioAcademicoRepository repository;

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

    }

}
