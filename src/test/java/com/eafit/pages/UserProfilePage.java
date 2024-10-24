package com.eafit.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Página de perfil del usuario.
 * <p>
 * Contiene métodos para manejar acciones específicas relacionadas con la gestión del perfil,
 * como la eliminación de la cuenta del usuario.
 * </p>
 */
public class UserProfilePage {

    private final WebDriver driver;
    private final By deleteAccountButton = By.xpath("//button[@id='submit' and text()='Delete Account']");
    private final By okModalButton = By.xpath("//button[@id='closeSmallModal-ok' and text()='OK']");

    /**
     * Constructor que configura el WebDriver para interactuar con la página.
     *
     * @param driver instancia de WebDriver para controlar la página actual.
     */
    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Realiza la acción de eliminar la cuenta del usuario desde la interfaz de perfil.
     * <p>
     * Se asegura de que el botón "Delete Account" sea visible, lo selecciona,
     * confirma la acción en el modal emergente y acepta la alerta.
     * </p>
     */
    public void deleteAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteAccountButton));

        WebElement deleteButton = driver.findElement(deleteAccountButton);
        deleteButton.click();

        WebElement okButton = driver.findElement(okModalButton);
        okButton.click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
