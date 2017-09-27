package edu.kvcc.cis298.cis298inclass1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    // This key is used to store the data on the intent
    // that is created to get this activity started
    private static final String EXTRA_ANSWER_IS_TRUE =
            "edu.kvcc.cis298.cis298inclass1.answer_is_true";

    // This key is used to store the returned data on the returned intent
    // that is created to send the data back to the quiz activity.
    private static final String EXTRA_ANSWER_SHOWN =
            "edu.kvcc.cis298.cis298inclass1.answer_shown";
    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        // Start CheatActivity
        // Create a new intent to get the activity started.
        // Intents are objects that hold all of the data needed.
        // The intent constructor takes two parameters. The first
        // is the instance of the class that is initiating the new activity.
        // The second is the name of the activity you want to start.

        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    // this method will be called by QuizActivity to check and see if a user
    // cheated or not. QuizActivity will send the Intent that contains the
    // result data into this method, and this method will 'decode' the intent
    // and return a bool to let you know whether the person cheated or not.
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // Use the key defined at the top to get out the data from the intent
        // that was used to get this activity started.
        // getIntent() is a method on the Activity class.
        // There are multiple getXXXExtra() methods for each type of extra
        // you want to pull out from the intent. Here we are using Boolean.
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }

    // method to create and set the return data for this activity
    private void setAnswerShownResult(boolean isAnswerShown) {

        // make a new intent to hold the return data.
        // It will NOT be used to start a new activity.
        // Intent now has double duty.
        Intent data = new Intent();
        // Put an extra just like we did when we are creating an intent to start an activity
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        // Call the Activity's setResult method to attain the intent as the return data.
        // The first parameter is a CONST that says everything finished okay here.
        // There are other CONSTs for when other things happen.
        setResult(RESULT_OK, data);
    }
}
