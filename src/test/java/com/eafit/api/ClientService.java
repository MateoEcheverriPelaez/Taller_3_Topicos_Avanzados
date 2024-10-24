package com.eafit.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/**
 * Clase auxiliar para la configuración de las solicitudes HTTP
 * con Rest Assured.
 * <p>
 * Proporciona una especificación de solicitud base, incluyendo la
 * configuración de la URL y los encabezados necesarios para la comunicación
 * con la API.
 * </p>
 */
public class ClientService {

    /**
     * Obtiene la configuración predefinida de una solicitud para interactuar
     * con la API.
     * <p>
     * Establece la URL base y el tipo de contenido en "application/json".
     * </p>
     *
     * @return la configuración de solicitud predeterminada para Rest Assured.
     */
    public static RequestSpecification getRequestSpecification() {
        return RestAssured
                .given()
                .baseUri("https://demoqa.com")
                .header("Content-Type", "application/json");
    }
}
