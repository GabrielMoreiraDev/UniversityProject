package cbl.project;

import ma02_resources.project.Project;
import ma02_resources.project.Status;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Edition implements ma02_resources.project.Edition{
    private String name;
    private LocalDate start;
    private LocalDate end;
    private String projectTemplate;
    private Status status;
    private Project[] projects;
    private int numberOfProjects = 0;

    public Edition(String name, LocalDate start, LocalDate end, String projectTemplate, Status status) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = status;
        this.projects = new Project[10];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public String getProjectTemplate() {
        return projectTemplate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Use the given parameters and the previously selected template (when creating the edition) to create a project and add it to the array
     * This method uses "org.json.simple" to read the JSON file
     *
     * @param s Project's name
     * @param s1 Project's description
     * @param strings List of tags
     * @throws IOException If an error occurs when reading the JSON file
     * @throws ParseException If an error occurs when parsing the JSON file
     */
    @Override
    public void addProject(String s, String s1, String[] strings) throws IOException, ParseException {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(this.projectTemplate));
            JSONObject jsonObj = (JSONObject) obj;

            //Read parameters
            int number_of_facilitators = Integer.parseInt(jsonObj.get("number_of_facilitors").toString());
            int number_of_students = Integer.parseInt(jsonObj.get("number_of_students").toString());
            int number_of_partners = Integer.parseInt(jsonObj.get("number_of_partners").toString());


            //Read array
            JSONArray tasks = (JSONArray) jsonObj.get("tasks");
            Task[] tasksArray = new Task[tasks.size()];

            int i = 0;
            for (Object taskObj : tasks) {
                JSONObject task = (JSONObject) taskObj;

                String title = task.get("title").toString();
                String description = task.get("description").toString();
                int startAt = Integer.parseInt(task.get("start_at").toString());
                int duration = Integer.parseInt(task.get("duration").toString());

                LocalDate start = this.start.plusDays(startAt);
                LocalDate end = start.plusDays(duration);

                tasksArray[i++] = new Task(start, end, title, description);
            }

            if (this.getStatus().equals(Status.ACTIVE) || this.getStatus().equals(Status.INACTIVE)) {
                Project project = new cbl.project.Project(s, s1, strings, tasksArray, number_of_facilitators,
                        number_of_students, number_of_partners);
                try {
                    this.projects[numberOfProjects] = project;
                    numberOfProjects++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    Project[] newArray = new Project[this.projects.length + 10];
                    System.arraycopy(this.projects, 0, newArray, 0, this.projects.length);
                    this.setProjects(newArray);
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            throw new ParseException(e.getMessage(), e.getPosition());
        }
    }

    /**
     * Gets the position of a project in the array
     *
     * @param s Project's name
     * @return The position of the wanted project (-1 if it doesn't exist)
     */
    private int getProjectPos(String s) {
        for (int i = 0; i < numberOfProjects; i++) {
            if (projects[i].getName().equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes a project from the array
     *
     * @param s Project's name
     */
    @Override
    public void removeProject(String s) {
        int projectPos = this.getProjectPos(s);
        if (projectPos != -1) {
            for (int i = projectPos; i < numberOfProjects - 1; i++) {
                this.projects[i] = this.projects[i + 1];
            }
        }
    }

    /**
     * Searches in the array for the project with the given name
     *
     * @param s Project's name
     * @return The project with the given name (null if it doesn't exist)
     */
    @Override
    public Project getProject(String s) {
        int projectPos = this.getProjectPos(s);
        if (projectPos != -1) {
            return this.projects[projectPos];
        } else {
            return null;
        }
    }

    @Override
    public Project[] getProjects() {
        return projects;
    }

    private void setProjects(Project[] projects) {
        this.projects = projects;
    }

    /**
     * Searches in the array for the projects with the given tag and joins them in an array
     *
     * @param s Tag which will be used to find projects
     * @return The array of projects
     */
    @Override
    public Project[] getProjectsByTag(String s) {
        Project[] array = new cbl.project.Project[numberOfProjects];
        int posArray = 0;

        for (Project project : this.projects) {
            for (String tag : project.getTags()) {
                if (tag.equals(s)) {
                    array[posArray] = project;
                    posArray++;
                    break;
                }
            }
        }
        return array;
    }

    /**
     * Searches int the array for the projects which have the given participant and joins them in an array
     *
     * @param s Participant's name
     * @return The array of projects
     */
    @Override
    public Project[] getProjectsOf(String s) {
        Project[] array = new cbl.project.Project[numberOfProjects];
        int posArray = 0;

        for (Project project : this.projects) {
            if (project.getParticipant(s) != null) {
                array[posArray] = project;
                posArray++;
            }
        }
        return array;
    }

    @Override
    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }
}
