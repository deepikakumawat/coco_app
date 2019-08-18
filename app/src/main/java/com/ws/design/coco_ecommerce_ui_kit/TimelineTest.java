package com.ws.design.coco_ecommerce_ui_kit;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TimelineTest extends AppCompatActivity {

    //Create Timeline Rows List
    private ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_test);


        // Add Random Rows to the List
        for (int i = 0; i < 4; i++) {
            //add the new row to the list
            timelineRowsList.add(createRandomTimelineRow(i));
        }

        //Create the Timeline Adapter
        myAdapter = new TimelineViewAdapter(this, 0, timelineRowsList,
                //if true, list will be sorted by date
                true);


        //Get the ListView and Bind it with the Timeline Adapter
        ListView myListView = (ListView) findViewById(R.id.timeline_listView);
        myListView.setAdapter(myAdapter);


        //if you wish to handle list scrolling
//        myListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            private int currentVisibleItemCount;
//            private int currentScrollState;
//            private int currentFirstVisibleItem;
//            private int totalItem;
//            private LinearLayout lBelow;
//
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                // TODO Auto-generated method stub
//                this.currentScrollState = scrollState;
//                this.isScrollCompleted();
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//                // TODO Auto-generated method stub
//                this.currentFirstVisibleItem = firstVisibleItem;
//                this.currentVisibleItemCount = visibleItemCount;
//                this.totalItem = totalItemCount;
//
//
//            }
//
//            private void isScrollCompleted() {
//                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
//                        && this.currentScrollState == SCROLL_STATE_IDLE) {
//
//                    ////on scrolling to end of the list, add new rows
//                    for (int i = 0; i < 4; i++) {
//                        myAdapter.add(createRandomTimelineRow(i));
//                    }
//
//                }
//            }
//
//
//        });

        //if you wish to handle the clicks on the rows
        AdapterView.OnItemClickListener adapterListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimelineRow row = timelineRowsList.get(position);
                Toast.makeText(TimelineTest.this, row.getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
        myListView.setOnItemClickListener(adapterListener);


    }

    //Method to create new Timeline Row
    private TimelineRow createRandomTimelineRow(int id) {

        TimelineRow myRow = new TimelineRow(id);

        myRow.setDate(getRandomDate());
        myRow.setTitle("Title " + id);
        myRow.setDescription("Description " + id);
//        myRow.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.richkart + getRandomNumber(0, 10)));
        myRow.setBellowLineColor(getRandomColor(id));
//        myRow.setBellowLineSize(getRandomNumber(2, 25));
        myRow.setBellowLineSize(2);
//        myRow.setImageSize(getRandomNumber(5, 5));
        myRow.setBackgroundColor(getRandomColor(id));
        myRow.setBackgroundSize(15);
        myRow.setDateColor(getRandomColor(id));
        myRow.setTitleColor(getRandomColor(id));
        myRow.setDescriptionColor(getRandomColor(id));

        return myRow;
    }


    //Random Methods
    public int getRandomColor(int id) {
      /*  Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        */

        int color;
        if (id == 0 || id == 1) {
            color = getResources().getColor(R.color.green);

        } else {
            color = getResources().getColor(R.color.gray);

        }
        return color;
    }

    public int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * max);
    }


    public Date getRandomDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = new Date();
        try {
            startDate = sdf.parse("16/08/2019");
            long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
            endDate = new Date(random);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

}
