package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int userResponse;
    private int start;
    private int end;

    private int tempStart;
    private int tempEnd;

    private String answer = null;
    private int numberOfTries ;

    boolean isNewTry(){
        return start != tempStart || end != tempEnd;
    }

    // initializeAnswer() so that the random no. is generated only once during a user session
    // if start and end are not same :)
    private int initializeAnswer(){
        boolean generateNewRandomNumber = false;
        if(isNewTry()) {
            start = tempStart;
            end = tempEnd;

            generateNewRandomNumber = true;
        }

        Random rand = new Random();
        int tempAns = rand.nextInt((end - start) + 1) + start;

        return (generateNewRandomNumber) ? tempAns : Integer.parseInt(this.answer);
    }

    private int getNumberOfTries() {
        if(isNewTry()) {
            Log.i("Tries: ", "No. of tries: " + (tempEnd - tempStart + 1)/ 2);
            return (end - start + 1)/ 2;
        }
        else {
            Log.i("Tries: ", "IN ELSE: No. of tries: " + (tempEnd - tempStart + 1)/ 2);

            return numberOfTries - 1;
        }
    }

    public void onGuessPressed(View view) {
        Log.i("Info", "Button pressed!");

        /*
        if(userResponse == 0 && start == 0 && end == 0) {
            Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
            return;
        }
         */

        EditText editTextStart = findViewById(R.id.editTextStart);
        EditText editTextEnd = findViewById(R.id.editTextEnd);
        EditText editTextUserResponse = findViewById(R.id.editTextUserResponse);

        tempStart = Integer.parseInt(editTextStart.getText().toString());
        tempEnd = Integer.parseInt(editTextEnd.getText().toString());
        userResponse = Integer.parseInt(editTextUserResponse.getText().toString());

        //numberOfTries = getNumberOfTries();
        answer = String.valueOf(initializeAnswer());

        /*
        if(numberOfTries <= 0) {
            Toast.makeText(this, "No. of tries exceeded!, Try Again", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Random number generated was: " + answer, Toast.LENGTH_LONG).show();
            return;
        }
         */
        Toast.makeText(this, "Random no. generated is " + answer, Toast.LENGTH_SHORT).show();

        String toastMessage;


        if(Integer.parseInt(answer) == userResponse) {
            toastMessage = "Right answer!";
        }
        else if(Integer.parseInt(answer) > userResponse) {
            toastMessage = "Go Higher";
        }
        else {
            toastMessage = "Go Lower";
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userResponse = 0;
        start = 0;
        end = 0;
    }
}