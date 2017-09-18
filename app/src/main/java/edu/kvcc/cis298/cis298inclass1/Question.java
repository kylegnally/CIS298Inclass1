package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by kyleg on 9/18/2017.
 */

public class Question {

    // this is an int because we are referencing a memory address
    // in R.java which points to the string resource in strings.xml
    // for this particular question
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
