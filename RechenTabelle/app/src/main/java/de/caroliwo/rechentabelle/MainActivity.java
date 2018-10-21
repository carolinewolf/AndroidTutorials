package de.caroliwo.rechentabelle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    SeekBar numberRangeBar;
    ListView numberList;

    ArrayList<Integer>numbers = new ArrayList<Integer>(asList(1,2,3,4,5,6,7,8,9,10));

    public void calculate (int q){
        numbers.clear();
        for (int i=0; i<=10; i++) {
            numbers.add(i*q);
        }
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, numbers);
        numberList.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberList = findViewById(R.id.numbersListView);
        numberRangeBar = findViewById(R.id.numberRangeBar);

        calculate(numberRangeBar.getProgress());

        numberRangeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int q, boolean b) {
               calculate(q);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

}
