package co.clickcode.countdowntimercircleprogress;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText editTextCount;
    private TextView textViewCount;
    private ImageView imageViewSwitch;
    private ImageView imageViewReset;
    private CountDownTimer countDownTimer;
    boolean isRunning = false; // Determines if countdown timer is running
    long timeInMillis; // Stores the time in milliseconds for countdown timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextCount = (EditText) findViewById(R.id.editTextCount);
        textViewCount = (TextView) findViewById(R.id.textViewCount);
        imageViewSwitch = (ImageView) findViewById(R.id.imageViewSwitch);
        imageViewReset = (ImageView) findViewById(R.id.imageViewReset);

        imageViewSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {
                    if (editTextCount.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter Number Of Seconds", Toast.LENGTH_LONG).show();

                    } else {
                        start();
                    }
                } else {
                    stop();
                }
            }
        });

        imageViewReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
                isRunning = false;
                imageViewSwitch.setImageResource(R.drawable.ic_start);
                textViewCount.setText("" + timeInMillis / 1000);
                progressBar.setProgress((int) timeInMillis / 1000);
                progressBar.setMax((int) timeInMillis / 1000);
            }
        });
    }

    private void start() {

        String textInput = editTextCount.getText().toString();
        long timeInput = Long.parseLong(textInput) * 1000;
        timeInMillis = timeInput;
        progressBar.setMax((int) timeInMillis / 1000);
        imageViewSwitch.setImageResource(R.drawable.ic_stop);
        isRunning = true;

        countDownTimer = new CountDownTimer(timeInMillis, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewCount.setText("" + String.valueOf(Math.round(millisUntilFinished * 0.001f)));
                progressBar.setProgress(Math.round(millisUntilFinished * 0.001f));
            }

            @Override
            public void onFinish() {

            }
        }.start();
        countDownTimer.start();

    }

    private void stop() {
        imageViewSwitch.setImageResource(R.drawable.ic_start);
        isRunning = false;
        countDownTimer.cancel();

    }
}
