package cbl;

import ma02_resources.project.Edition;
import ma02_resources.project.Status;

public class CBL implements ICBL{
    private Edition[] editions;
    private int numberOfEditions = 0;

    public CBL() {
        editions = new Edition[10];
    }

    public int getNumberOfEditions() {
        return numberOfEditions;
    }

    private void setEditions(Edition[] editions) {
        this.editions = editions;
    }

    @Override
    public Edition[] getEditions() {
        return editions;
    }

    @Override
    public void addEdition(Edition edition) {
        try {
            this.editions[numberOfEditions] = edition;
            numberOfEditions++;
            if (edition.getStatus().equals(Status.ACTIVE)) {
                this.setActiveEdition(edition.getName());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Edition[] newArray = new Edition[this.editions.length + 10];
            System.arraycopy(this.editions, 0, newArray, 0, this.editions.length);
            this.setEditions(newArray);
        }
    }

    private int getEditionPos(String name) {
        for (int i = 0; i < numberOfEditions; i++) {
            if (this.editions[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeEdition(String name) {
        int editionPos = this.getEditionPos(name);
        if (editionPos != -1) {
            for (int i = editionPos; i < numberOfEditions - 1; i++) {
                this.editions[i] = this.editions[i + 1];
            }
        }
    }

    @Override
    public Edition getEdition(String name) {
        for (Edition edition : this.getEditions()) {
            if (edition.getName().equals(name)) {
                return edition;
            }
        }
        return null;
    }

    @Override
    public void setActiveEdition(String name) {
        Edition edition = getEdition(name);
        if (edition != null) {
            for (int i = 0; i < this.numberOfEditions; i++) {
                if (this.editions[i].getStatus().equals(Status.ACTIVE)) {
                    this.editions[i].setStatus(Status.INACTIVE);
                }
            }
            edition.setStatus(Status.ACTIVE);
        }
    }

    @Override
    public Edition getActiveEdition() {
        for (Edition edition : editions) {
            if (edition.getStatus().equals(Status.ACTIVE)) {
                return edition;
            }
        }
        return null;
    }
}
