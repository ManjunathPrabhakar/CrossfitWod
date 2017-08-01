package myproject.crossfitwod;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputActivity extends AppCompatActivity {

    TextView todayDate;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.wodList:
                    Intent wodList = new Intent(InputActivity.this, MainActivity.class);
                    startActivity(wodList);
                    break;
                case R.id.calendarView:
                    Intent calendarIntent = new Intent(InputActivity.this, CalendarActivity.class);
                    startActivity(calendarIntent);
                    break;
                case R.id.todaysWod:
                    break;
            }
            return false;
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        todayDate = (TextView) findViewById(R.id.today);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        todayDate.setText(date);
    }
}
