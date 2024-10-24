package com.eafit.tests;

import com.eafit.api.UserService;
import com.eafit.pages.UserLoginPage;
import com.eafit.pages.UserProfilePage;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Prueba para validar las funcionalidades básicas de usuario en la web.
 * <p>
 * La clase combina Selenium WebDriver para la interfaz gráfica con Rest Assured
 * para interactuar con el servicio web de la API.
 * </p>
 */
class MainTest {

    WebDriver driver;
    UserService userService;
    String username;
    String password;

    /**
     * Preparación antes de cada prueba.
     * <p>
     * Configura los datos del usuario y el navegador antes de cada ejecución.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        Long time = new Date().getTime();
        username = String.format("usereafit%s", time);
        password = String.format("Usereafit%s!", time);
        initializeDriver();
        userService = new UserService();
    }

    /**
     * Realiza la prueba de creación, inicio de sesión y eliminación de un usuario.
     * <p>
     * Se asegura de que el usuario se cree correctamente, inicie sesión con éxito,
     * y luego sea eliminado. Posteriormente, verifica que el usuario eliminado no
     * pueda autenticarse de nuevo.
     * </p>
     */
    @Test
    void testUserCreationAndLogin() {
        createUser();
        performLogin();
        deleteUser();

        // Intentar iniciar sesión con el usuario eliminado
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        String errorMessage = userLoginPage.loginWithErrors(username, password);
        assertTrue(errorMessage.contains("Invalid username or password"), "El mensaje de error no es el esperado.");
    }

    /**
     * Limpieza posterior a cada prueba.
     * <p>
     * Se cierra el navegador para liberar los recursos utilizados.
     * </p>
     */
    @AfterEach
    void finishTest() {
        driver.quit();
    }

    /**
     * Crea un usuario mediante una solicitud API y verifica su correcta creación.
     * <p>
     * Envía la petición a la API para registrar un nuevo usuario y verifica
     * que el estado de la respuesta sea 201.
     * </p>
     */
    private void createUser() {
        Response response = userService.createUser(username, password);
        assertEquals(201, response.getStatusCode(), "Error al crear el usuario");
    }

    /**
     * Realiza el inicio de sesión en la aplicación con las credenciales generadas.
     * <p>
     * Emplea la página de inicio de sesión para autenticar al usuario con los datos registrados.
     * </p>
     */
    private void performLogin() {
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        userLoginPage.login(username, password);
    }

    /**
     * Elimina al usuario desde la interfaz de perfil.
     * <p>
     * Accede a la página del perfil del usuario y procede a eliminar la cuenta.
     * </p>
     */
    private void deleteUser() {
        UserProfilePage profilePage = new UserProfilePage(driver);
        profilePage.deleteAccount();
    }

    /**
     * Configura el WebDriver y prepara el entorno del navegador.
     * <p>
     * Abre Firefox, navega a la URL especificada, ajusta el zoom y maximiza la ventana.
     * </p>
     */
    private void initializeDriver() {
        driver = new FirefoxDriver();
        driver.get("https://demoqa.com/login");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.body.style.zoom='60%'");
        driver.manage().window().maximize();
    }

}