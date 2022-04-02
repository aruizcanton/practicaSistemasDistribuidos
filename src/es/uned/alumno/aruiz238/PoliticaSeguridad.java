//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////

package es.uned.alumno.aruiz238;

import java.io.*;

public class PoliticaSeguridad {
    public static final String POLICY_FILE_NAME = "./abierto.policy";

    public static String getLocationOfPolicyFile () {
        final String policyFileName = POLICY_FILE_NAME;
        try {
            File tempFile = File.createTempFile("serviciodatos",".policy");
            InputStream is = PoliticaSeguridad.class.getResourceAsStream(policyFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            int read = 0;
            while ((read = is.read()) != -1) {
                writer.write(read);
            }
            writer.close();
            tempFile.deleteOnExit();
            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return policyFileName;
        }
    }
}
