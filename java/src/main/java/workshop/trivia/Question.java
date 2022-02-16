package java.src.main.java.workshop.trivia;

import java.util.*;

public class Question {
    private final Map<Category, List<String>> questionsByCategory = new HashMap<>();

    public Question(int QuestionCount, List<Category> categories) {
        for (Category c: categories) {
            questionsByCategory.put(c, new LinkedList<>());
        }
        for (int i = 0; i < QuestionCount; i++) {
            for (Category c:categories) {
                questionsByCategory.get(c).add(c + " Question " + i);
            }
        }
    }


     String nextQuestionIsAbout(Category currentCategory) {
        return questionsByCategory.get(currentCategory).remove(0);
    }
}

