package com.example.calculatorproject;

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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //buttons
    Button Button9;
    Button Button8;
    Button Button7;
    Button Button6;
    Button Button5;
    Button Button4;
    Button Button3;
    Button Button2;
    Button Button1;
    Button Button0;
    Button ButtonClear;
    Button ButtonEquals;
    Button ButtonMult;
    Button ButtonDivide;
    Button ButtonSubtract;
    Button ButtonAdd;
    //empty calc space
    TextView CalculatorSpace;
    String num1 = "";
    String num2 = "";
    String operator = "";
    Button lastclicked = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);




        CalculatorSpace = findViewById(R.id.CalcSpace);


        //get all buttons with ID's (try to optimize this later)
        Button9 = findViewById(R.id.Button9);
        Button8 = findViewById(R.id.Button8);
        Button7 = findViewById(R.id.Button7);
        Button6 = findViewById(R.id.Button6);
        Button5 = findViewById(R.id.Button5);
        Button4 = findViewById(R.id.Button4);
        Button3 = findViewById(R.id.Button3);
        Button2 = findViewById(R.id.Button2);
        Button1 = findViewById(R.id.Button1);
        Button0 = findViewById(R.id.Button0);




        View.OnClickListener numbers = new View.OnClickListener(){
            @Override
            public void onClick(View v){


                Button clickedbutton = (Button) v;
                String PreParseNumber = clickedbutton.getText().toString();
                //int FormatNum = Integer.parseInt(PreParseNumber);
                //store to num1
                if(Objects.equals(operator, "")){
                    num1 += PreParseNumber;
                    CalculatorSpace.setText(num1);
                }
                if (!operator.equals("")){

                    //reset color
                    lastclicked.setTextColor(Color.WHITE);
                    num2 += PreParseNumber;
                    CalculatorSpace.setText(num1 + " " + operator + " " +num2);
                }

            }
        };




        ButtonMult = findViewById(R.id.ButtonMult);
        ButtonDivide = findViewById(R.id.ButtonDivide);
        ButtonSubtract = findViewById(R.id.ButtonSub);
        ButtonAdd = findViewById(R.id.ButtonAdd);

        View.OnClickListener operators = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button clickedbutton = (Button) v;
                String GetOp = clickedbutton.getText().toString();
                //check if op is none and num1 is not none
                //if true -> set operator to GetOp
                if(!num1.equals("")){
                    //change color of button (Green)
                    //i have no clue of changing backgound color of the button without the app exploding
                    clickedbutton.setTextColor(Color.GREEN);
                    lastclicked = clickedbutton;
                    operator = GetOp;
                    CalculatorSpace.setText(num1 + " " + operator);


                }

               // if(num1 != null);

                //operator = GetOp;
                //else -> grey out the button


            }
        };


        ButtonClear = findViewById(R.id.ButtonClear);
        ButtonEquals = findViewById(R.id.ButtonEquals);

        View.OnClickListener functions = new View.OnClickListener(){
          @Override
          public void onClick(View v){
              Button clickedbutton = (Button) v;
              String procedure = clickedbutton.getText().toString();

              if(procedure.equals("Clear")){
                  num1 = "";
                  num2 = "";
                  operator = "";
                  //wipe
                  CalculatorSpace.setText("Type a Calculation!");

                  //fix bug that just crashes app if num1 is empty and you press =
              }else if(procedure.equals("=") && !num1.equals("")){

                  //translate num1 and num2 to integers
                  int num1calc = Integer.parseInt(num1);
                  int num2calc = Integer.parseInt(num2);



                  //add
                  if(operator.equals("+")){
                      String AnswerAdd = Integer.toString(num1calc + num2calc);
                      CalculatorSpace.setText(AnswerAdd);

                  }
                  //sub
                  if(operator.equals("-")){
                      String AnswerSub = Integer.toString(num1calc - num2calc);
                      CalculatorSpace.setText(AnswerSub);
                  }

                  //mult
                  if(operator.equals("X")){
                      String AnswerMult = Integer.toString(num1calc * num2calc);
                      CalculatorSpace.setText(AnswerMult);
                  }

                  //divide
                  if(operator.equals("/")){
                      String AnswerDivide = Float.toString((float) num1calc / num2calc);
                      CalculatorSpace.setText(AnswerDivide);
                  }
              }



              //check if text is clear or =
              //if equals -> do the calculation and display to CalculatorSpace
              //if clear, wipe variables to default value



          }
        };

        View.OnLongClickListener EqualsClear = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Button clickedbutton = (Button) v;
                String LongEqualsCheck = clickedbutton.getText().toString();
                if (LongEqualsCheck.equals("=")) {
                    num1 = "";
                    num2 = "";
                    operator = "";
                    //wipe
                    CalculatorSpace.setText("Type a Calculation!");

                    return true;
                }
                return false;
            }

        };

        //make on click listener for all buttons :
        //in theory
        //1 for numbers
        //2 for ops
        //3 for equals
        //4 for clear

        //how to handle actual calculator logic:


        //create a var for both numbers to actually use and initialize to 0
        // Int num1 = 0;
        // Int num2 = 0;
        //initialize a string object(?) to store the operator
        //String op = " "
        //once an op is selected from button
        //store it
        //once equal button is selected
        // return num1 (op) num2

        Button9.setOnClickListener(numbers);
        Button8.setOnClickListener(numbers);
        Button7.setOnClickListener(numbers);
        Button6.setOnClickListener(numbers);
        Button5.setOnClickListener(numbers);
        Button4.setOnClickListener(numbers);
        Button3.setOnClickListener(numbers);
        Button2.setOnClickListener(numbers);
        Button1.setOnClickListener(numbers);
        Button0.setOnClickListener(numbers);


        ButtonMult.setOnClickListener(operators);
        ButtonAdd.setOnClickListener(operators);
        ButtonSubtract.setOnClickListener(operators);
        ButtonDivide.setOnClickListener(operators);

        ButtonEquals.setOnClickListener(functions);
        ButtonClear.setOnClickListener(functions);
        ButtonEquals.setOnLongClickListener(EqualsClear);



    }
}