package edu.kvcc.cis298.cis298inclass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // used for logging to the logcat
    private static final String TAG = "QuizActivity";

    // used as the key in a key => value object called the bundle
    // to save information between screen rotations
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    // array from which we will pull questions. We send over the resource
    // id from R.java as the first parameter of the constructor.
    // We will use this stored resource ID which references a string in strings.xml later.
    // If we were to have a string variable on the Question model
    // and try to pass the string value, it would work, but it
    // goes against the convention of Android development.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    // the array index variable for the current question.
    private int mCurrentIndex = 0;

    // Method that will be used to update the question text on the view
    private void updateQuestion() {
        // get the question from the array. This is an integer because we are
        // fetching the memory address stored in the question that points to the
        // string that we want to show.
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        // Now we set the text on the question textview to the string resource
        // located at the memory address stored in question.
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        // pull the answer from the current question
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        // declare an integer to hold the string resource id of the answer
        int messageResId = 0;
        // if the question's answer and what the user pressed are equal
        //then they got it right. Correct answers will be when both variables are the
        // same. If they're different, the answer is wrong.
        // Set the messageResId once we determine what the answer is.
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        // Make this toast like the other toast.
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        // Get a reference to the textview that displays the question
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        // Check the savedInstanceState Bundle and see if there
        // is an index that we need to fetch out so we display the correct
        // question

        // When the app first launches, there's no bundle. That only happens when
        // switching activities or on screen rotation. Therefore we need to see if
        // it is null before we try to pull info out.

        if (savedInstanceState != null){

            // get the value that is stored with the key of KEY_INDEX
            // if there is no entry with that key, use 0 as a default
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        //update the question now that we have the index
        updateQuestion();


        // Use R to reach into the view layout and pull a reference
        // to the button we want to use in the code.
        // We get access to this in an almost identical
        // fashion Javascript does.
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // When we get to mod 5 we set back to 0 so we see the questions
                // in a neverending loop.
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the answer to the current question.

                // To get the intent we need to start up the CheatActivity
                // we call the newIntent method on the CheatActivity. That
                // method returns us an Intent that is ready to start the
                // new Activity.



                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                // To start up a new activity. We then call startActivity
                // with the intent as a parameter. The intent is used by the
                // OS to determine what activity to start up.
                // Activities are started by the OS, NOT the app.
                startActivity(i);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
