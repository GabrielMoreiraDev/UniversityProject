/**
 * Nome: Gabriel Ferreira Moreira
 * Número: 8220225
 * Turma: 3
 * Nome: Miguel Correira Ribeiro Rangel Tavares
 * Número: 8220229
 * Turma: 3
 */

package cbl.project;

import ma02_resources.project.Submission;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Class task allow us to create and track the progress (as well as it's submissions) of a task.
 */
public class Task implements ma02_resources.project.Task{
    private LocalDate start;
    private LocalDate end;
    private String title;
    private String description;
    private Submission[] submissions;
    private int posArray = 0;

    public Task(LocalDate start, LocalDate end, String title, String description) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.submissions = new Submission[1];
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    private void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * Takes the day of the year for the start date and end date and subtracts both.
     * @return The duration of the task
     */
    @Override
    public int getDuration() {
        return start.getDayOfYear() - end.getDayOfYear();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Submission[] getSubmissions() {
        return submissions;
    }

    private void setSubmissions(Submission[] submissions) {
        this.submissions = submissions;
    }

    /**
     * Uses the variable that stores the next position of the array submissions to know how many submissions there are.
     * @return The number of submissions
     */
    @Override
    public int getNumberOfSubmissions() {
        return posArray;
    }

    /**
     * Adds a submission to the array "submissions"
     *
     * @param submission The new submission
     */
    @Override
    public void addSubmission(Submission submission) {
        try {
            submissions[posArray] = submission;
        } catch (ArrayIndexOutOfBoundsException e) {
            Submission[] newArray = new Submission[submissions.length * 10];
            System.arraycopy(submissions, 0, newArray, 0, submissions.length);
            newArray[posArray] = submission;
            this.setSubmissions(newArray);
        }
        posArray++;
    }

    /**
     * Uses the method "plusDays" from the "LocalDate" class to add a given number of days to the end
     * date.
     * @param i Number of days to add
     */
    @Override
    public void extendDeadline(int i) {
        this.setEnd(this.end.plusDays(i));
    }

    /**
     * Compares the provided submission with the instance in which it is being used by the end dates of both.
     * @param task The object to be compared.
     * @return -1 if the task end date is before, 0 if it ends at the same time, 1 if it ends after
     */
    @Override
    public int compareTo(ma02_resources.project.Task task) {
        if (task.getEnd().isBefore(this.end)) {
            return -1;
        } else if(task.getEnd().isAfter(this.end)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "start=" + start +
                ", end=" + end +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", submissions=" + Arrays.toString(submissions) +
                ", posArray=" + posArray +
                '}';
    }
}
