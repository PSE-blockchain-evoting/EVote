package edu.kit.iti.formal.pse2018.evote.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConfigResourceBundle {

    /**
     * Returns the external bundle referenced by name if it exists, internal version otherwise.
     * @see ResourceBundle#getBundle(String)
     * @param name ResourceBundle#getBundle(String)
     * @return resource bundle
     */
    public static ResourceBundle loadBundle(String name) {
        File externalFile = new File(name + ".properties");
        if (externalFile.isFile() && externalFile.canRead()) {
            try {
                return new PropertyResourceBundle(new FileInputStream(externalFile));
            } catch (IOException e) {
                System.err.println("No external config file found, using defaults");
            }
        }
        return ResourceBundle.getBundle(name);
    }
}
