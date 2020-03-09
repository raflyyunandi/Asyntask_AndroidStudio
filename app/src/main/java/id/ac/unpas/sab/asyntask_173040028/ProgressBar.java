package id.ac.unpas.sab.asyntask_173040028;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBar extends AppCompatActivity {
    Button button;
    TextView textView;
    Integer count = 1;
    private android.widget.ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        progressBar = (android.widget.ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(10);
        button = (Button) findViewById(R.id.btn);
        button.setText("Start");
        textView = (TextView) findViewById(R.id.btnOutput);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        count = 1;
                        progressBar.setVisibility(View.VISIBLE);
                        switch (v.getId()){
                            case R.id.btn :
                                new MyTask().execute(10);
                                break;
                }
            }
        });
    }

    class MyTask extends AsyncTask<Integer, Integer, String>{
        @Override
        protected  String doInBackground(Integer... paramInt){
            for (;count <= paramInt[0]; count++){
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return "Task Completed";
        }
        protected void  onPostExecute(String result){
            progressBar.setVisibility(View.GONE);
            textView.setText(result);
            button.setText("Restart");
        }

        @Override
        protected void onPreExecute() {
            textView.setText("Task Starting");
        }

        @Override
        protected  void onProgressUpdate (Integer... values){
            textView.setText("Running..." + values[0]);
            progressBar.setProgress(values[0]);
        }
    }
}
