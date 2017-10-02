package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mSubmitButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private RadioGroup mQuestiongroup;
    private RadioButton mChoice1RadioButton;
    private RadioButton mChoice2RadioButton;
    private RadioButton mChoice3RadioButton;
    private RadioButton mChoice4RadioButton;

    // array from which we will pull questions. We send over the resource
    // id from R.java as the first parameter of the constructor.
    // We will use this stored resource ID which references a string in strings.xml later.
    // If we were to have a string variable on the Question model
    // and try to pass the string value, it would work, but it
    // goes against the convention of Android development.
    private Question[] mQuestionBank = new Question[] {
            // first parameter is the string that is the question text
            // second parameter is the id of the correct answer in Strings.xml
            // third is the array holding the possible answers.
            new Question(R.string.question_1_multiple, R.id.multiple_choice_3, new int[] {R.string.question_1_choice_1,
            R.string.question_1_choice_2, R.string.question_1_choice_3, R.string.question_1_choice_4}),
            new Question(R.string.question_2_multiple, R.id.multiple_choice_2, new int[] {R.string.question_2_choice_1,
                    R.string.question_2_choice_2, R.string.question_2_choice_3, R.string.question_2_choice_4}),
    };

    // the array index variable for the current question.
    private int mCurrentIndex = 0;

    // ,ethod that will be used to update the question text on the view
    private void updateQuestion() {
        // get the question from the array. This is an integer because we are
        // fetching the memory address stored in the question that points to the
        // string that we want to show.
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        // Now we set the text on the question textview to the string resource
        // located at the memory address stored in question.
        mQuestionTextView.setText(question);

        // fetch the question choice strings from the question object
        int[] choices = mQuestionBank[mCurrentIndex].getChoiceResId();

        // assign each choice to the text property of the radio button
        mChoice1RadioButton.setText(choices[0]);
        mChoice2RadioButton.setText(choices[1]);
        mChoice3RadioButton.setText(choices[2]);
        mChoice4RadioButton.setText(choices[3]);

    }

    private void checkAnswer(int selectedRadioButtonId) {
        // pull the answer from the current question
        int correctAnswer = mQuestionBank[mCurrentIndex].getCorrectAnswerResId();
        // declare an integer to hold the string resource id of the answer
        int messageResId = 0;
        // if the question's resId and correctAnswer resId are equal
        //then they got it right. Correct answers will be when both variables are the
        // same. If they're different, the answer is wrong.
        // Set the messageResId once we determine what the answer is.
        if (selectedRadioButtonId == correctAnswer) {
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
        setContentView(R.layout.activity_quiz);

        // Get a reference to the textview that displays the question
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        // Get out the radio button the user clicked from the view
        mQuestiongroup = (RadioGroup) findViewById(R.id.multiple_group);
        mChoice1RadioButton = (RadioButton) findViewById(R.id.multiple_choice_1);
        mChoice2RadioButton = (RadioButton) findViewById(R.id.multiple_choice_2);
        mChoice3RadioButton = (RadioButton) findViewById(R.id.multiple_choice_3);
        mChoice4RadioButton = (RadioButton) findViewById(R.id.multiple_choice_4);

        updateQuestion();

        // Use R to reach into the view layout and pull a reference
        // to the button we want to use in the code.
        // We get access to this in an almost identical
        // fashion Javascript does.
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // query the radio button group to find out which radio button was selected. Store the
                // id of the selected one the variable selectedAnswerId
                // this will get the ID of the radiobutton that was selected. It
                // will NOT return any string resource Ids about the question.
                // It is operating on the widget, and thus returns the Id of the widget
                int selectedAnswerId = mQuestiongroup.getCheckedRadioButtonId();
                // call checkAnswer sending in the selectedAnswerId
                checkAnswer(selectedAnswerId);
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
    }
}
