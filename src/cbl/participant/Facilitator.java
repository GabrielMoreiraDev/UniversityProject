/**
 * Nome: Gabriel Ferreira Moreira
 * Número: 8220225
 * Turma: 3
 * Nome: Miguel Correira Ribeiro Rangel Tavares
 * Número: 8220229
 * Turma: 3
 */

package cbl.participant;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;

public class Facilitator extends Participant implements ma02_resources.participants.Facilitator{
    private String areaOfExpertise;

    public Facilitator(String name, String email, Contact contact, Instituition instituition, String areaOfExpertise) {
        super(name, email, contact, instituition);
        this.areaOfExpertise = areaOfExpertise;
    }

    @Override
    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    @Override
    public void setAreaOfExpertise(String s) {
        this.areaOfExpertise = s;
    }
}
