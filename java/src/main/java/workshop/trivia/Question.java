package java.src.main.java.workshop.trivia;

import java.util.LinkedList;

public class Question {
    LinkedList<String> popQuestions = new LinkedList();
    LinkedList<String> scienceQuestions = new LinkedList();
    LinkedList<String> sportsQuestions = new LinkedList();
    LinkedList<String> rockQuestions = new LinkedList();

    public Question() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(createQuestion("Pop", i));
            scienceQuestions.addLast(createQuestion("Science",i));
            sportsQuestions.addLast(createQuestion("Sports",i));
            rockQuestions.addLast(createQuestion("Rock",i));
        }
    }

    public String createQuestion(String questionType, int index) {
        return questionType + " Question " + index;
    }
}

