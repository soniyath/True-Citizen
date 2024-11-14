package com.example.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.truecitizen.databinding.ActivityMainBinding;
import com.example.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private int currentIndexId = 0;

    private Question[] questionBank = new Question[]{
            //create/ instantiate questions and object
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.questionTextView.setText(questionBank[0].getAnswerResId());
        binding.setLifecycleOwner(this);

        binding.trueButton.setOnClickListener(view -> checkAnswer(true));
        binding.falseButton.setOnClickListener(view -> checkAnswer(false));

        binding.nextButton.setOnClickListener(view -> {
            currentIndexId = (currentIndexId + 1) % questionBank.length;
            updateQuestion();
        });

        binding.prevButton.setOnClickListener(view -> {
            if(currentIndexId > 0){
                currentIndexId = (currentIndexId - 1) % questionBank.length;
                updateQuestion();
            }
        });
    }
    private void checkAnswer(boolean userChoseCorrect){
        boolean answerIsCorrect = questionBank[currentIndexId].isAnswerTrue();
        int messageId;
        if(answerIsCorrect == userChoseCorrect){
            messageId = R.string.correct_answer;
        }else{
            messageId = R.string.wrong_answer;
        }
        Snackbar.make(binding.imageView, messageId, Snackbar.LENGTH_SHORT)
                .show();

    }
    private void updateQuestion() {
        binding.questionTextView.setText(questionBank[currentIndexId].getAnswerResId());

    }
}