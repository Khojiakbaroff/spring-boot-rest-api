package uz.pdp.restapiv1react;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestProduct extends RestApiV1ReactApplicationTests{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCreatedStatusCode() throws Exception {
        mockMvc.perform(getBuilder()).andExpect(status().isCreated());
    }

    public RequestBuilder getBuilder() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("lastname", "Apple");
        map.put("firstname", "ergerg");
        map.put("email", "dredthesc");
        map.put("password", "dergesc");

        return post("api/v1/employee/create").content(new ObjectMapper().writeValueAsString(map))
                .contentType(MediaType.APPLICATION_JSON);
    }
}
