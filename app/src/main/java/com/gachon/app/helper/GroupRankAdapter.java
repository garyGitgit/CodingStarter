package com.gachon.app.helper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gachon.app.R;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by garyNoh on 2017. 6. 2..
 */

public class GroupRankAdapter extends ArrayAdapter<ParseObject> {
    private ArrayList<ParseObject> items;

    public GroupRankAdapter(Context context, int resId, ArrayList<ParseObject> items){
        super(context, resId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.rank_row, null);
        }
        ParseObject p = items.get(position);
        if (p != null) {

            //1,2,3 순위에게 메달 부여
            ImageView medalImage = (ImageView)v.findViewById(R.id.medal);
            Log.e("gary", Integer.toString(position));
            switch (position){
                case 0:
                    if(this.getItem(position) == this.getItem(0))
                        medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal1));
                    break;
                case 1:
                    if(this.getItem(position) == this.getItem(1))
                        medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal2));
                    break;
                case 2:
                    if(this.getItem(position) == this.getItem(2))
                        medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal3));
                    break;
            }
            
            TextView groupRankText = (TextView) v.findViewById(R.id.group_rank);
            groupRankText.setText(Integer.toString(position+1));

            TextView groupNameText = (TextView) v.findViewById(R.id.group_name);
            groupNameText.setText((String)p.get("name"));

            TextView groupPointText = (TextView) v.findViewById(R.id.group_point);
            groupPointText.setText(Integer.toString((int)p.getNumber("points")) + "pt");

        }
        return v;
    }
}
