package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskTest {
    //per mappatura nella get
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    TaskService taskService;
   
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    //The Test annotation tells JUnit that the public void method to which it is attached can be run as a test case. 
    //To run the method, JUnit first constructs a fresh instance of the class then invokes 
    //the annotated method. Any exceptions thrown by the test will be reported by JUnit as a failure
    @Test
    public void getTaskById(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();//apre una porta sul localhost a caso temporanea. Ritorna un oggetto di tipo String
        ResponseEntity<String> response = testRestTemplate.getForEntity(createURLWithPort("/api/task/3142"), String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println(response.toString());
    }

    //Secondo Modo get
    
    @Test
    public void getTaskByIdRest(){
        String url = createURLWithPort("/api/task/{id}") ;
        Long taskId= 3142L;
        //in questo caso l'oggetto di ritorno è tipizzato su Task (parse del JSON)
        //getForEntity sono metodi http corrispondenti a get() post()
        ResponseEntity<Task> responseEntity = restTemplate.getForEntity(url, Task.class, taskId); 
        
        if (responseEntity.hasBody()){
            Task t = responseEntity.getBody();
            assertEquals(t.getTask_id(),taskId);
        }

    }
    //Terzo Modo get
    @Test
    public void testRetrieveEmployee() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/task/3142"),
                HttpMethod.GET, entity, String.class);

       
        String s = response.getBody();
        try {
            //Mappatura dei campi della classe Task
            Task t = new ObjectMapper().readValue(s, Task.class);
            Task t2 = taskService.findById(t.getTask_id()).get();
            //Verifico se i due oggetti coincidono
            assertEquals(t, t2);
            System.out.println(e);
        } catch (JsonProcessingException e) {
           
            e.printStackTrace();
        }

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    //Attenzione che l'ORM di hibernate quando inseriamo un utente già presente, facciamo un update
    @Test
    public void postTask(){

        Task t = new Task();
        t.setTask_id(2323L);
        t.setTask_name("SVILUPPARE METODO DI INTEGRAZIONE");
        t.setTask_status( "C");
        t.setProject_id( 2004L);
        t.setCoordinator_id( 261508L);
        LocalDate startdate = LocalDate.of(2020, 1, 8);
        t.setTask_start_date(startdate);
        LocalDate endate = LocalDate.of(2020, 1, 30);
        t.setTask_end_date(endate);
	


                 
        HttpHeaders headers = new HttpHeaders();
        //Passo l'intero oggetto
        //Attenzione in queto modo ad esempio non possiamo aggiungere una tupla nella tabella emp-task non essendoci la classe e quindi è meglio utilizzare la JSONObject con parametri custom
        //Mappatto basandosi sull'oggetto Employee della classe JSON per http
        HttpEntity<Task> request = new HttpEntity<>(t, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity( createURLWithPort("/api/task"), request, String.class);
       
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
       
    }

    

    @Test
    public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
            throws IOException {

        JSONObject employeeJsonObject = new JSONObject();
        try {
            //passo attributi specifici
            //Già oggetto JSON object chiave valore
            employeeJsonObject.put("employee_id", 2);

            employeeJsonObject.put("first_name", "f21");
            employeeJsonObject.put("last_name", "l1");
            employeeJsonObject.put("email", "a@a1");
            employeeJsonObject.put("salary", 100);
            employeeJsonObject.put("total_work_percentage", 1);
            employeeJsonObject.put("membership_count", 2);
            employeeJsonObject.put("department_id", 1000);

            System.out.println(employeeJsonObject);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<String>(employeeJsonObject.toString(), headers);

            String employeeResultAsJsonStr = restTemplate.postForObject(
                    createURLWithPort("/api/employee"), request, String.class);
            JsonNode root = objectMapper.readTree(employeeResultAsJsonStr);
            System.out.println(root);
            assertNotNull(employeeResultAsJsonStr);
            assertNotNull(root);
            assertNotNull(root.path("first_name").asText());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }



     @Test
     public void deleteEmployee_rest(){
         String url = createURLWithPort("/api/employee/{id}") ;
         Long empId= 2L;
         
         restTemplate.delete(createURLWithPort("/api/employee/"+empId), Employee.class, empId); 

         ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class, empId); 
         if (responseEntity.hasBody()){
             Employee e = responseEntity.getBody();
             assertEquals(e.getEmployee_id(),empId);
 
         }

 
     }
}
