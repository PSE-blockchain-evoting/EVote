/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
