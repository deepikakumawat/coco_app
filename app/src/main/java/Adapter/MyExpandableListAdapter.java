package Adapter; /**
 * Created by Wolf Soft on 11/24/2016.
 */


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.HashMap;
import java.util.List;


public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    RadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;


    public MyExpandableListAdapter(Context context, List<String> listDataHeader,
                                   HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);


    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {



        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.item_list, null);

            btn1 = (RadioButton) convertView.findViewById(R.id.button1);
            btn2 = (RadioButton) convertView.findViewById(R.id.button2);
            btn3 = (RadioButton) convertView.findViewById(R.id.button3);
            btn4 = (RadioButton) convertView.findViewById(R.id.button4);
            btn5 = (RadioButton) convertView.findViewById(R.id.button5);
            btn6 = (RadioButton) convertView.findViewById(R.id.button6);
            btn7 = (RadioButton) convertView.findViewById(R.id.button7);
            btn8 = (RadioButton) convertView.findViewById(R.id.button8);

            Typeface font = Typeface.createFromAsset(_context.getAssets(), "fonts/Roboto-Medium.ttf");

            btn1.setText("Apple");
            btn2.setText("Sony");
            btn3.setText("Asus");
            btn4.setText("LG");
            btn5.setText("Lenovo");
            btn6.setText("Samsung");
            btn7.setText("Xiaomi");
            btn8.setText("HTC");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btn1.isSelected()) {
                        btn1.setSelected(false);
                        btn1.setChecked(false);
                    } else {
                        btn1.setSelected(true);
                        btn1.setChecked(true);
                    }
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btn2.isSelected()) {
                        btn2.setSelected(false);
                        btn2.setChecked(false);
                    } else {
                        btn2.setSelected(true);
                        btn2.setChecked(true);
                    }
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btn3.isSelected()) {
                        btn3.setSelected(false);
                        btn3.setChecked(false);
                    } else {
                        btn3.setSelected(true);
                        btn3.setChecked(true);
                    }
                }
            });


            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btn4.isSelected()) {
                        btn4.setSelected(false);
                        btn4.setChecked(false);
                    } else {
                        btn4.setSelected(true);
                        btn4.setChecked(true);
                    }
                }
            });

            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btn5.isSelected()) {
                        btn5.setSelected(false);
                        btn5.setChecked(false);
                    } else {
                        btn5.setSelected(true);
                        btn5.setChecked(true);
                    }
                }
            });


            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn6.isSelected()) {
                        btn6.setSelected(false);
                        btn6.setChecked(false);
                    } else {
                        btn6.setSelected(true);
                        btn6.setChecked(true);
                    }
                }
            });

            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn7.isSelected()) {
                        btn7.setSelected(false);
                        btn7.setChecked(false);
                    } else {
                        btn7.setSelected(true);
                        btn7.setChecked(true);
                    }
                }
            });

            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn8.isSelected()) {
                        btn8.setSelected(false);
                        btn8.setChecked(false);
                    } else {
                        btn8.setSelected(true);
                        btn8.setChecked(true);
                    }
                }
            });

            btn1.setTypeface(font);
            btn2.setTypeface(font);
            btn3.setTypeface(font);

            btn4.setTypeface(font);
            btn5.setTypeface(font);
            btn6.setTypeface(font);

            btn7.setTypeface(font);
            btn8.setTypeface(font);
        }

        LinearLayout txtListChild = (LinearLayout) convertView.findViewById(R.id.lblListItem);

//        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);

//        final ExpandableListView lvExp = (ExpandableListView) convertView.findViewById(R.id.lvExp);

        ImageView indicatorImg = convertView.findViewById(R.id.indicatorImg);

        if (isExpanded) {
            indicatorImg.setImageResource(R.drawable.ic_left_arrow_3);
        } else {
            indicatorImg.setImageResource(R.drawable.ic_down_arrow);
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}


