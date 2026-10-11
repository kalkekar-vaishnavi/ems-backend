package net.javamaker.ems_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:ems-test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class EmsBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void employeeCanBeCreatedReadAndDeleted() throws Exception {
        String employeeJson = mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "firstName": "Asha",
                                  "lastName": "Patel",
                                  "email": "asha.patel@example.com",
                                  "department": "IT",
                                  "jobTitle": "Developer",
                                  "salary": 45000
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Asha"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        long id = com.jayway.jsonpath.JsonPath.parse(employeeJson).read("$.id", Long.class);

        mockMvc.perform(get("/api/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("asha.patel@example.com"));

        mockMvc.perform(delete("/api/employees/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/employees/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void invalidEmployeeRequestReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").exists());
    }
}
