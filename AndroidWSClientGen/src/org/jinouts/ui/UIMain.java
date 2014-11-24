/**
 *
 */
package org.jinouts.ui;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author asraf
 */
public class UIMain {

    static final String title = "Jinos Android WS Client Generator";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Load the properties
        loadProperties();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JinoutsJFrame frame = new JinoutsJFrame(title);
                frame.setVisible(true);
            }
        });
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        try {
            // load properties
            properties.load(new FileInputStream("conf/andWSClientGen.properties"));

            // now get the properties
            //AndroidWSClientGenProp.wsClientGenCommand = (String) properties.get("cxf.ws.Client.Command");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
