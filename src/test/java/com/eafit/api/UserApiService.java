package com.eafit.api;

import io.restassured.response.Response;

/**
 * Servicio para manejar las interacciones con la API de gestión de usuarios.
 * <p>
 * Contiene métodos para llevar a cabo operaciones relacionadas con los usuarios,
 * como la creación de nuevos usuarios en el sistema.
 * </p>
 */
public class UserApiService {

    /**
     * Realiza la creación de un usuario nuevo en la API usando el nombre de usuario
     * y la contraseña proporcionados.
     * <p>
     * Envía una solicitud HTTP POST a la ruta "/Account/v1/User" con los datos
     * del usuario en formato JSON dentro del cuerpo de la solicitud.
     * Se considera que la creación es exitosa si la respuesta de la API tiene
     * un código de estado 201.
     * </p>
     *
     * @param username el nombre del usuario que se va a registrar.
     * @param password la clave de acceso para el nuevo usuario.
     * @return un objeto {@link Response} con la respuesta devuelta por la API.
     * @throws AssertionError si la respuesta no tiene un código de estado 201.
     */
    public Response createUser(String username, String password) {
        String body = """
            {
                "userName": "%s",
                "password": "%s"
            }
        """;
        return ApiClient.getRequestSpecification()
                .body(String.format(body, username, password))
                .when()
                .post("/Account/v1/User")
                .then()
                .statusCode(201)
                .extract().response();
    }

}
