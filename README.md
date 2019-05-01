# Securing a GUI application with Keycloak

## Getting started
Following instruction in the Keycloak documentation.

### Java Adapters
Keycloak comes with a range of different adapters for Java application. Selecting the correct adapter depends on the target platform.

#### CLI / Desktop Applications
Read the instruction specified in chapter 2.1.13. CLI / Desktop Applications 
[CLI / Desktop Applications](https://www.keycloak.org/docs/4.3/securing_apps/index.html#_installed_adapter)

##### How it works

To authenticate a user with the desktop variant the KeycloakInstalled adapter opens a desktop browser window where a user uses the regular Keycloak login pages to login when the loginDesktop() method is called on the KeycloakInstalled object.

The login page URL is opened with redirect parameter that points to a local ServerSocket listening on a free ephemeral port on localhost which is started by the adapter.

After a successful login the KeycloakInstalled receives the authorization code from the incoming HTTP request and performs the authorization code flow. Once the code to token exchange is completed the ServerSocket is shutdown.

**Note:**

> If the user already has an active Keycloak session then the login form is not shown but the code to token exchange is continued, which enables a smooth Web based SSO experience.

The client eventually receives the tokens (access_token, refresh_token, id_token) which can then be used to call backend services.

##### Client Configuration

The application needs to be configured as a public OpenID Connect client with Standard Flow Enabled and http://localhost:* as an allowed Valid Redirect URI.