package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.logging.Logger;




public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "WHY IS THIS BROKEN LOL";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);




        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                    Toast.makeText(CheatActivity.this, getString(R.string.judgement_toast), Toast.LENGTH_SHORT).show();

                } else {
                    mAnswerTextView.setText(R.string.false_button);
                    Toast.makeText(CheatActivity.this, getString(R.string.judgement_toast), Toast.LENGTH_SHORT).show();


                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "Hello from Cheat Activity");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

}