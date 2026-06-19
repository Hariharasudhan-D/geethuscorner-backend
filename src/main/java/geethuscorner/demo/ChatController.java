package geethuscorner.demo;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller to handle AI Chatbot requests.
 * It exposes an endpoint /api/chat that the React frontend calls.
 * It forwards the user's message to the FastAPI AI service and returns the response.
 */
@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Allow requests from any frontend origin (like Vercel or localhost)
public class ChatController {

    // The external URL of the FastAPI Cake Shop AI agent
    private static final String AI_SERVICE_URL = "https://cake-shop-ai.onrender.com/chat";

    @PostMapping
    public ResponseEntity<Map<String, String>> askAI(@RequestBody Map<String, String> requestBody) {
        String userMessage = requestBody.get("message");
        
        // 1. Prepare request payload for the FastAPI AI server
        Map<String, String> payload = new HashMap<>();
        payload.put("message", userMessage);

        // 2. Set up headers (Content-Type: application/json)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);

        // 3. Initialize RestTemplate to send the HTTP request
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            // 4. Send POST request to FastAPI /chat endpoint
            ResponseEntity<Map> response = restTemplate.postForEntity(AI_SERVICE_URL, requestEntity, Map.class);
            
            // 5. Extract response text and wrap it in a clean format for the frontend
            Map<String, String> responseBody = new HashMap<>();
            if (response.getBody() != null && response.getBody().containsKey("response")) {
                responseBody.put("response", response.getBody().get("response").toString());
            } else {
                responseBody.put("response", "Sorry, I received an empty response from the AI assistant.");
            }
            
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
            
        } catch (Exception e) {
            // Fallback response if the FastAPI service is down or times out
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("response", "I'm having trouble connecting to my AI brain right now. Please try again later!");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
