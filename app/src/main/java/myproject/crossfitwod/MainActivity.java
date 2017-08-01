package myproject.crossfitwod;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Wod> wodList;
    ScrollView sView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
           = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.wodList:
                    break;
                case R.id.calendarView:
                    Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.todaysWod:
                    Intent inputIntent = new Intent(MainActivity.this, InputActivity.class);
                    startActivity(inputIntent);
                    break;
            }
            return false;
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wodList = new ArrayList<Wod>();
        sView = (ScrollView) findViewById(R.id.scrollView);
        BottomNavigationView bNMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bNMenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        renderInitScreen();
    }

    public void renderInitScreen(){
        TableLayout main = (TableLayout) findViewById(R.id.mainLayout);
        parseDataFile();
        int i = 0;
        for (Wod ex: wodList){
            TextView name = new TextView(this);
            name.setText(ex.getName());
            TextView exercise = new TextView(this);
            exercise.setText(ex.getExercise());
            TextView rep = new TextView(this);
            rep.setText(ex.getRep());

            TableRow line = new TableRow(this);
            line.addView(name);
            line.addView(exercise);
            line.addView(rep);
            if (i%2 ==0){
                line.setBackgroundColor(Color.LTGRAY);
            }else{
                line.setBackgroundColor(Color.WHITE);
            }
            i++;
            main.addView(line);
        }
    }

    public void parseDataFile(){
        int idNum = 0;
        try{
            InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.data));
            BufferedReader reader = new BufferedReader(is);
            CSVReader read = new CSVReader(reader);
            String[] record = null;
            while ((record = read.readNext()) != null){
                if (idNum==0){
                    Wod item = new Wod(record[0], record[1], record[2]);
                    wodList.add(item);
                    idNum++;
                }else if(!record[0].isEmpty()){
                    Wod item = new Wod(record[0], record[1], record[2]);
                    wodList.add(item);
                }else {
                    wodList.get(wodList.size()-1).addExtraInfo(record[1], record[2]);
                }
            }
        }catch  (Exception e){

        }
    }
}
