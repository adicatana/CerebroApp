package group15.cerebro.session.multi;
import group15.cerebro.entities.Question;

public class QuestionRandomizer {
    public synchronized Question randomizeOrder(Question question) {
        if (question == null) {
            return null;
        }
        int index = (int)(Math.random() * 6) + 1;
        if (index > 6) {
            index -= 1;
        }
        Question out = new Question();
        out.setQuestion(question.getQuestion());
        if (index == 1) {
            out.setAnswer(question.getAnswer());
            out.setWrong1(question.getWrong1());
            out.setWrong2(question.getWrong2());
        }
        if (index == 2) {
            out.setAnswer(question.getAnswer());
            out.setWrong1(question.getWrong2());
            out.setWrong2(question.getWrong1());
        }
        if (index == 3) {
            out.setAnswer(question.getWrong1());
            out.setWrong1(question.getAnswer());
            out.setWrong2(question.getWrong2());
        }
        if (index == 4) {
            out.setAnswer(question.getWrong1());
            out.setWrong1(question.getWrong2());
            out.setWrong2(question.getAnswer());
        }
        if (index == 5) {
            out.setAnswer(question.getWrong2());
            out.setWrong1(question.getAnswer());
            out.setWrong2(question.getWrong1());
        }
        if (index == 6) {
            out.setAnswer(question.getWrong2());
            out.setWrong1(question.getWrong1());
            out.setWrong2(question.getAnswer());
        }
        return out;
    }
}

