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
import ma02_resources.participants.InstituitionType;

public class Instituition implements ma02_resources.participants.Instituition{
    private String name;
    private String email;
    private InstituitionType type;
    private Contact contact;
    private String website;
    private String description;

    public Instituition(String name, String email, InstituitionType instituitionType, Contact contact, String website, String description) {
        this.name = name;
        this.email = email;
        this.type = instituitionType;
        this.contact = contact;
        this.website = website;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public InstituitionType getType() {
        return type;
    }

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setWebsite(String s) {
        this.website = s;
    }

    @Override
    public void setDescription(String s) {
        this.description = s;
    }

    @Override
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void setType(InstituitionType instituitionType) {
        this.type = instituitionType;
    }

    @Override
    public String toString() {
        return "Instituition{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", Contact{" +
                "street='" + contact.getStreet() + '\'' +
                ", city='" + contact.getCity() + '\'' +
                ", state='" + contact.getState() + '\'' +
                ", zipCode='" + contact.getZipCode() + '\'' +
                ", country='" + contact.getCountry() + '\'' +
                ", phone='" + contact.getPhone() + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
