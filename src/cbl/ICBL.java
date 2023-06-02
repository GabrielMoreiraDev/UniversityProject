package cbl;

import ma02_resources.project.Edition;

public interface ICBL {
    Edition[] getEditions();
    void addEdition(Edition edition);
    void removeEdition(String name);
    Edition getEdition(String name);
    void setActiveEdition(String name);
    Edition getActiveEdition();

    int getNumberOfEditions();
}
