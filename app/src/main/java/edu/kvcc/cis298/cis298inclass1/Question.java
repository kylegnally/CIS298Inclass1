package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by kyleg on 9/18/2017.
 */

public class Question {

    // this is an int because we are referencing a memory address
    // in R.java which points to the string resource in strings.xml
    // for this particular question
    private int mTextResId;
    // resource ID for the correct answer
    private int mCorrectAnswerResId;
    // resource ID of all possible answers for the question
    private int[] mChoiceResId;

    public Question(int textResId, int correctAnswerResId, int[] choiceResId){
        mTextResId = textResId;
        mCorrectAnswerResId = correctAnswerResId;
        mChoiceResId = choiceResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public int getCorrectAnswerResId() {
        return mCorrectAnswerResId;
    }

    public void setCorrectAnswerResId(int correctAnswerResId) {
        mCorrectAnswerResId = correctAnswerResId;
    }

    public int[] getChoiceResId() {
        return mChoiceResId;
    }

    public void setChoiceResId(int[] choiceResId) {
        mChoiceResId = choiceResId;
    }
}
