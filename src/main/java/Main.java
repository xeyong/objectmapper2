import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("lamborgini");
        car1.setCarNumber("1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("ferrari");
        car2.setCarNumber("2222");
        car2.setType("SUV");

        List<Car> cars = Arrays.asList(car1,car2);
        user.setCars(cars);
        
        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println("JSON:"+json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("name: " +  _name);
        System.out.println("age: "+ _age);

        JsonNode cars1 = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars1;

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {
        });
        System.out.println(_cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name","seyong");
        objectNode.put("age", 26);

        System.out.println(objectNode.toPrettyString());
    }
}
