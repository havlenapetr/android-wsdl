/**
 *
 */
package org.jinouts.ui;

import org.jinouts.util.WSClientGenPropertiesLoader;

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
        WSClientGenPropertiesLoader.loadProperties();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JinoutsJFrame frame = new JinoutsJFrame(title);
                frame.setVisible(true);
            }
        });

    }

}
