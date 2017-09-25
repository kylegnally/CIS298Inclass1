package edu.kvcc.cis298.cis298inclass1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "edu.kvcc.cis298.cis298inclass1.answer_is_true";
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
            }
        });
    }
}
