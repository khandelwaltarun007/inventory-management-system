import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalabs.ims.dto.ProductRequestDTO;

public class Test {
public static void main(String[] args) throws JsonProcessingException {
	List<ProductRequestDTO> ab = new ArrayList<>();
	ab.add(new ProductRequestDTO());
	ab.add(new ProductRequestDTO());
	ab.add(new ProductRequestDTO());
	ab.add(new ProductRequestDTO());
	
	ObjectMapper ob = new ObjectMapper();
	System.out.println(ob.writeValueAsString(ab));
}
}
