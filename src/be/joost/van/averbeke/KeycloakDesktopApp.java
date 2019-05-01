package be.joost.van.averbeke;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.keycloak.adapters.installed.KeycloakInstalled;
import org.keycloak.representations.AccessToken;

public class KeycloakDesktopApp {

        public static void main(String[] args) throws Exception {

                KeycloakInstalled keycloak = new KeycloakInstalled();
                keycloak.setLocale(Locale.ENGLISH);
                keycloak.loginDesktop();

                AccessToken token = keycloak.getToken();
                Executors.newSingleThreadExecutor().submit(() -> {

                        System.out.println("Logged in...");
                        System.out.println("Token: " + token.getSubject());
                        System.out.println("Username: " + token.getPreferredUsername());
                        try {
                                System.out.println("AccessToken: " + keycloak.getTokenString());
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                        System.err.println("Hello Keycloak from " + token.getPreferredUsername());
                        int timeoutSeconds = 20;
                        System.out.printf("Logging out in...%d Seconds%n", timeoutSeconds);
                        try {
                                TimeUnit.SECONDS.sleep(timeoutSeconds);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }

                        try {
                                keycloak.logout();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }

                        System.out.println("Exiting...");
                        System.exit(0);
                });
        }
}