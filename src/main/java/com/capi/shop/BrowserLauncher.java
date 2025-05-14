package com.capi.shop;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import java.awt.Desktop;
import java.net.URI;

/**
 * Opens the default browser to the demo index page when Quarkus starts.
 */
@ApplicationScoped
@IfBuildProfile("dev")
public class BrowserLauncher {

    public void onStart(@Observes StartupEvent ev) {
        String url = "http://localhost:8080";
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("✅ Opening browser at: " + url);
            } else {
                System.out.println("⚠️ Desktop not supported. Please open manually: " + url);
            }
        } catch (Exception e) {
            System.err.println("❌ Could not open browser: " + e.getMessage());
        }
    }
}
