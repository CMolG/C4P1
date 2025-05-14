package com.capi.shop;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import java.awt.Desktop;
import java.net.URI;

/**
 * Opens the default browser to the demo index page when Quarkus starts.
 */
public class BrowserLauncher {

    public void onStart(@Observes StartupEvent ev) {
        String url = "http://localhost:9090/index.html";
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
