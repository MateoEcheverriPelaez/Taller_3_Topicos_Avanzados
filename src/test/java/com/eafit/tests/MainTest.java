package com.eafit.tests;

import com.eafit.api.UserService;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private UserService userService;

    private static final By USERNAME_INPUT = By.id("userName");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login");
    private static final By DELETE_ACCOUNT_BUTTON = By.xpath("//*[@class=\"text-center button\"]//button[@id=\"submit\"]");
    private static final By CONFIRM_DELETION_BUTTON = By.id("closeSmallModal-ok");
    private static final By LOGIN_ERROR = By.id("name");

    private static final String LOGIN_URL = "https://demoqa.com/login";
    private static final String USERNAME = "johndoe";
    private static final String PASSWORD = "315JWZ5oj@";
    private static final int TIMEOUT_SECONDS = 20;
    private static final String INVALID_LOGIN_MESSAGE = "Invalid username or password!";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        js = (JavascriptExecutor) driver;
        userService = new UserService();
    }

    @Test
    public void testLoginAndAccountDeletion() {
        // Crear usuario via API y validar creación
        createUser(USERNAME, PASSWORD);

        // Navegar a la página de login
        driver.get(LOGIN_URL);

        // Realizar login
        performLogin(USERNAME, PASSWORD);

        // Eliminar la cuenta
        deleteAccount();

        // Verificar mensaje de error al intentar iniciar sesión nuevamente
        performLogin(USERNAME, PASSWORD);
        verifyLoginError(INVALID_LOGIN_MESSAGE);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private void createUser(String username, String password) {
        Response response = userService.createUser(username, password);
        assertEquals(201, response.getStatusCode(), "Error al crear el usuario");
    }

    private void performLogin(String username, String password) {
        WebElement userNameInput = waitForElement(USERNAME_INPUT);
        WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
        WebElement loginButton = driver.findElement(LOGIN_BUTTON);

        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        scrollIntoViewAndClick(loginButton);
    }

    private void deleteAccount() {
        WebElement deleteAccountButton = waitForElement(DELETE_ACCOUNT_BUTTON);
        scrollIntoViewAndClick(deleteAccountButton);

        WebElement confirmDeletionButton = waitForElement(CONFIRM_DELETION_BUTTON);
        confirmDeletionButton.click();

        waitForAlertAndAccept();
    }

    private void verifyLoginError(String expectedErrorMessage) {
        WebElement loginError = waitForElement(LOGIN_ERROR);
        Assertions.assertEquals(expectedErrorMessage, loginError.getText());
    }

    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void scrollIntoViewAndClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    private void waitForAlertAndAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}