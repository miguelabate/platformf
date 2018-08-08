package com.kramphub.rest.client;

import com.google.common.base.Stopwatch;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JerseyClient {

    final static Logger logger = Logger.getLogger(JerseyClient.class);

    /**
     * Main method created for manual testing
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        logger.info("Requesting json...");
        JerseyClient client = new JerseyClient();
//		 client.doGet("http://localhost:8080/PlatformF/rest/myFarm/machine/tractor1");

        float opKm = 0;
        float opHours = 0;

        for (int i = 0;; i++) {
            opKm += 10.5;
            opHours += 0.4;
            String jsonRequestBody = TestUtils
                    .convertStreamToString(JerseyClient.class.getResourceAsStream("postMachine1.json"));
            jsonRequestBody = jsonRequestBody.replace("#OPERATION_KM", String.valueOf((int)opKm)).replace("#OPERATION_HOURS", String.valueOf((int)opHours));
            client.doPost("http://localhost:8080/PlatformF/rest/myFarm/machine/tractor1", jsonRequestBody);
            Thread.sleep(1000);
        }
    }

    public void doPost(String url, String jsonRequestBody) throws IOException {
        JsonNode jsonObjResult = null;
        Stopwatch stopwatch = null;
        ClientResponse response = null;

        Client client = Client.create();

        WebResource webResource = client.resource(url);
        stopwatch = Stopwatch.createStarted();

        response = webResource.type("application/json").post(ClientResponse.class, jsonRequestBody);

        stopwatch.stop();
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

    }

    public JsonNode doGet(String url) throws IOException {
        JsonNode jsonObjResult = null;
        Stopwatch stopwatch = null;
        ClientResponse response = null;
        try {

            Client client = Client.create();

            WebResource webResource = client.resource(url);
            stopwatch = Stopwatch.createStarted();

            response = webResource.accept("application/json").get(ClientResponse.class);

            stopwatch.stop();
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String output = response.getEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();
            jsonObjResult = mapper.readTree(output);


            logger.info("Received JSON response from server. Elapsed time: " + stopwatch);
            return jsonObjResult;

        } catch (IOException e) {
            logger.error(e);
            throw e;
        }
    }

}