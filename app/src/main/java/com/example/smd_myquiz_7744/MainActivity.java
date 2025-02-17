package com.example.smd_myquiz_7744;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public abstract class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submit_button;

    int score=0;
    int totalQuestion=QuestionAnswer.questions.length;
    int currentQuestionIndex=0;
    String seletedAnswer="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submit_button=findViewById(R.id.submit_button);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);


        totalQuestionsTextView.setText("Total Questions : "+totalQuestion);
        loadNewQuestion();




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        public void onClick(View view)
        {
            ansA.setBackgroundColor(Color.WHITE);
            ansB.setBackgroundColor(Color.WHITE);
            ansC.setBackgroundColor(Color.WHITE);
            ansD.setBackgroundColor(Color.WHITE);

            Button clickedButton=(Button) view;
            if(clickedButton.getId()==R.id.submit_button){
                currentQuestionIndex++;
                loadNewQuestion();
                if(seletedAnswer.equals((QuestionAnswer.correctAnswers[currentQuestionIndex]))){
                    score++;
                }
            }
            else{
                //choices button clicked
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.MAGENTA);
            }
        }

        void loadNewQuestion(){

            if(currentQuestionIndex == totalQuestion){
                finishQuiz();
                return;
            }

            questionTextView.setText(QuestionAnswer.questions[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        }
        void finishQuiz(){
            String passStatus="";
            if(score>totalQuestion+0.60)
            {
                passStatus="Passed";
            }
            else
            {
                passStatus="Failed";
            }
            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score is " +score+ "out of"+ totalQuestion)
                    .setPositiveButton("Restart",dial)
        }
    }

}