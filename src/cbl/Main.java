/**
 * Nome: Gabriel Ferreira Moreira
 * Número: 8220225
 * Turma: 3
 * Nome: Miguel Correira Ribeiro Rangel Tavares
 * Número: 8220229
 * Turma: 3
 */

package cbl;
import cbl.project.*;
import ma02_resources.project.Status;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        String path = "project_template.json";

        CBL cbl = new CBL();
        Edition edition = new Edition("nigga", LocalDate.now(), LocalDate.now().plusDays(5), "nigger", Status.ACTIVE);
        Edition edition2 = new Edition("nigga2", LocalDate.now(), LocalDate.now().plusDays(5), path, Status.ACTIVE);
        cbl.addEdition(edition2);
        cbl.addEdition(edition);

        try {
            cbl.getEdition("nigga2").addProject("supernigga", "sdiubgsidg", null);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println(cbl.getEdition("nigga2").getProjects()[0].getTasks()[0].getDescription());
        System.out.println(cbl.getEdition("nigga2").getStatus());
    }
}