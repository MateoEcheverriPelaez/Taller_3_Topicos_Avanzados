package com.eafit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Página de inicio de sesión de la aplicación.
 * <p>
 * Incluye métodos para realizar el proceso de autenticación y para manejar
 * posibles errores relacionados con el inicio de sesión.
 * </p>
 */
public class UserLoginPage {
    private final WebDriver driver;
    private final By usernameField = By.xpath("//*[@id='userName']");
    private final By passwordField = By.xpath("//*[@id='password']");
    private final By loginByButton = By.xpath("//button[@id='login' and text()='Login']");
    private final By labelByError = By.xpath("//p[@id='name' and text()='Invalid username or password!']");

    /**
     * Inicializa la página de inicio de sesión con el WebDriver especificado.
     *
     * @param driver instancia de WebDriver para interactuar con la página.
     */
    public UserLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Envía las credenciales proporcionadas para realizar el inicio de sesión.
     * <p>
     * Completa los campos de nombre de usuario y contraseña, y luego selecciona el botón de inicio de sesión.
     * </p>
     *
     * @param username nombre de usuario utilizado para el inicio de sesión.
     * @param password contraseña asociada al usuario.
     */
    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        WebElement loginButton = driver.findElement(loginByButton);
        loginButton.click();
    }

    /**
     * Intenta autenticar al usuario y devuelve el mensaje de error si las credenciales son incorrectas.
     * <p>
     * Ejecuta el proceso de inicio de sesión con las credenciales dadas, esperando a que
     * aparezca el mensaje de error en caso de fallar.
     * </p>
     *
     * @param username nombre de usuario a autenticar.
     * @param password contraseña para el inicio de sesión.
     * @return mensaje de error mostrado si el inicio de sesión no es exitoso.
     */
    public String loginWithErrors(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelByError));
        return driver.findElement(labelByError).getText();
    }
}
