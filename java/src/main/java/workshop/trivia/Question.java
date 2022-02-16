package java.src.main.java.workshop.trivia;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Question {
    LinkedList<String> popQuestions = new LinkedList();
    LinkedList<String> scienceQuestions = new LinkedList();
    LinkedList<String> sportsQuestions = new LinkedList();
    LinkedList<String> rockQuestions = new LinkedList();


    Map<String, Deque<String >> questionCategory = new HashMap<>();

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

