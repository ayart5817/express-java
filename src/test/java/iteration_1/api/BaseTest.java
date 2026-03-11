package iteration_1.api;

import api.configs.Config;
import io.restassured.RestAssured;
import iteration_1.common.extensions.TimingExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimingExtension.class)
public class BaseTest {
    protected SoftAssertions softly;

    @BeforeAll
    public static void initConfig() {
        String server = Config.getProperty("server");
        String apiVersion = Config.getProperty("apiVersion");

        System.out.println("=== Test Configuration ===");
        System.out.println("server = " + server);
        System.out.println("apiVersion = " + apiVersion);
        System.out.println("Base URL = " + Config.getBaseUrl());

        if (server == null || apiVersion == null) {
            System.err.println("ERROR: Configuration is incomplete!");
            // Устанавливаем значения по умолчанию
            RestAssured.baseURI = "http://localhost:4111";
            RestAssured.basePath = "/api/v1";
        } else {
            RestAssured.baseURI = server;
            RestAssured.basePath = apiVersion;
        }
    }

    @BeforeEach
    public void setupTest() {
        this.softly = new SoftAssertions();
    }

    @AfterEach
    public void afterTest() {
        softly.assertAll();
    }
}