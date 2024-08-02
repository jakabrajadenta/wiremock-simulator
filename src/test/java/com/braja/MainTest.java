package com.braja;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import okhttp3.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.options().port(8080), false);

    private OkHttpClient client;

    @Before
    public void configureStubs() {
        // configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/test/abc"))
                .willReturn(aResponse().withBody("Test success!")));

        stubFor(delete("/test/delete").willReturn(okJson("Deleted!")));

        stubFor(get("/test/get").willReturn(okJson("{\"name\": \"John\"}")));

        stubFor(post("/test/post").withRequestBody(equalToJson("{\"name\":\"Hendro\"}")).willReturn(okJson("Posted!")));

        stubFor(post("/test/unauthorized").willReturn(unauthorized()));

        stubFor(put("/test/status-only").willReturn(status(418)));

        client = new OkHttpClient();
    }

    @Test
    public void exampleTestAbc() throws IOException {

        Request request = new Request.Builder()
                .url("http://localhost:8080/test/abc")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);

        assertNotNull(response);
        assertEquals("Test success!", responseBody);
        // verify(exactly(1), getRequestedFor(urlEqualTo("/test/abc")));
    }

    @Test
    public void exampleTestDelete() throws IOException {

        Request request = new Request.Builder()
                .url("http://localhost:8080/test/delete")
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);

        assertNotNull(response);
        assertEquals("Deleted!", responseBody);
        // verify(exactly(1), getRequestedFor(urlEqualTo("/test/abc")));
    }

    @Test
    public void exampleTestPost() throws IOException {

        String json = "{\"name\":\"Hendro\"}";
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("http://localhost:8080/test/post")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);

        assertEquals(200, response.code());
        assertNotNull(response);
        assertEquals("Posted!", responseBody);
        // verify(exactly(1), getRequestedFor(urlEqualTo("/test/abc")));
    }

    @Test
    public void exampleTestUnauthorized() throws IOException {

        RequestBody body = RequestBody.create("", MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url("http://localhost:8080/test/unauthorized")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.code());

        assertEquals(401, response.code());
        // verify(exactly(1), getRequestedFor(urlEqualTo("/test/abc")));
    }

    @Test
    public void exampleTestFileMapping() throws IOException {

        Request request = new Request.Builder()
                .url("http://localhost:8080/mappings/other")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);

        assertEquals(200, response.code());
    }

}
