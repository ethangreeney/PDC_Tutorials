package Task11_2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;

public abstract class Question {
    protected String questionText;
    protected Object questionAnswer;
    protected int questionScore;
    protected String createdBy;
    protected Date createdOn;

    public Question() {
        createdBy = getHostName();
        createdOn = new Date();
    }

    public abstract boolean checkAnswer(String userAnswer);

    public abstract void printQuestion();

    public abstract void printQuestionInfo();

    private String getHostName() {
        String hostname = "Unknown";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException ex) {
            System.err.println("Hostname can not be resolved: " + ex.getMessage());
        }
        return hostname;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Object getQuestionAnswer() {
        return questionAnswer;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Question question = (Question) o;
        return Objects.equals(questionText, question.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText);
    }
}