package tigger;

import java.util.ArrayList;

/**
 * The Trivia class represents a collection of trivia questions and answers about Tigger.
 * It provides methods to retrieve questions and their corresponding answers.
 */
public class Trivia {

    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();

    /**
     * Constructor for the Trivia class. Initializes the questions and
     * answers lists with predefined trivia about Tigger.
     */
    public Trivia() {
        questions.add("When was 'The Tigger Movie released?");
        answers.add("2000");

        questions.add("How many stripes do I have on my tail?");
        answers.add("8");

        questions.add("What is my catchphrase when dismayed?");
        answers.add("Say it ain't so!");

        questions.add("What is the name of the second stripe on my tail?");
        answers.add("Springy");

        questions.add("What is the name of the top stripe on my tail?");
        answers.add("Curly");
    }

    public String getQuestion(int index) {
        return questions.get(index);
    }

    public String getAnswer(int index) {
        return answers.get(index);
    }
}
