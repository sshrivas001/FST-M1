import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GithubProjectRestAssured {
    // Declare request specification
    private RequestSpecification requestSpec;

    //The String variable will hold the SSH key that will be added to the GitHub account
    public String sshKey="ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIGokLqRFSrJeysb79qSCr0t4dOSEd2QOUhmRzRVUD9ud";

    //the int variable will hold the id that is generated for the SSH key
   public int sshKeyId;

    @BeforeClass
    public void setUp() {
        //GitHub access token
        String accessToken = "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";

        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com") //Set Base URL
                .setContentType(ContentType.JSON) //Content type set to JSON
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "token " + accessToken) //A Authorization header that has the value “token <GitHub access token>”
                .build();

    }

    @Test(priority=1)
    //  POST request to add a SSH key to your account
    public void addSSHKey() {
        //Create request body and pass sshKey declare above
        String requestBody = "{\"title\": \"TestAPIKey\",\"key\":\""+sshKey+"\"}";

        //Store the response
        Response response = given()
               .spec(requestSpec)  //calling Request spec object
               .contentType(ContentType.JSON) // Set headers
               .basePath("/user/keys") //
                .body(requestBody) // Send request body
                .when().post(); // Send POST request

        // Get response body
        System.out.println(response.getBody().asPrettyString());

        //From the response, extract the id of the SSH key that was just added and save it in the integer variable that was declared at the beginning
        sshKeyId = response.then()
                .extract()
                .path("id");
        System.out.println("SSHKeyID:"+sshKeyId);

        // Assertions
        //Fetching the response code from the request and validating the same
        System.out.println("The response code - " +response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),201);

    }

    //sending a GET request to get all the SSH keys attached to your account
    @Test(priority=2)
    public void getSSHKey() {
       //Store the response .Add a path parameter for keyId
        Response response = given().spec(requestSpec)
                .contentType(ContentType.JSON)// Use requestSpec
                .pathParam("keyId", sshKeyId) // Add path parameter
                .when().get("/user/keys/{keyId}"); // Send GET request

        // Print response
        // Get response body
        System.out.println("Get Response:");
        System.out.println(response.getBody().asPrettyString());
        Reporter.log("GET SSH Key Response:\n" + response);
        // Assertions
        System.out.println("The response code - " +response.getStatusCode());
         Assert.assertEquals(response.getStatusCode(), 200);

    }

    //sending a DELETE request to delete the key attached to your account
    @Test(priority=3)
    public void deleteSSHKey() {
        ////Store the response .Add a path parameter for keyId
        Response response = given().spec(requestSpec) // Use requestSpec
                .contentType(ContentType.JSON)// Use requestSpec
                .pathParam("keyId", sshKeyId) // Add path parameter
                .when().delete("/user/keys/{keyId}"); // Send Delete request

        // Get response body and print it
        System.out.println("Delete response:");
        System.out.println(response.getBody().asPrettyString());
        Reporter.log("Delete SSH Key Response:\n" + response);
        // Assertions
        System.out.println("The response code - " +response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
