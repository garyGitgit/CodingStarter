package com.gachon.app.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gachon.app.R;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by garyNoh on 2017. 6. 2..
 */

public class GroupRankAdapter extends ArrayAdapter<ParseObject> {
    private ArrayList<ParseObject> items;
    String fontName = "font1.ttf";
    Typeface typeface;

    //1,2,3위에게 메달을 부여했으면 포지션이 0,1,2 더라도 메달을 부여하지 않아야한다
    //boolean[] isMedalGiven = new boolean[3];

    public GroupRankAdapter(Context context, int resId, ArrayList<ParseObject> items){
        super(context, resId, items);
        this.items = items;
        typeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
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


            //TODO : 메달부여는 왜 안되는지 모르겠다
//            Log.e("gary", Integer.toString(position));
            //Log.e("gary", Integer.toString(position));
//            Log.e("gary", (String)this.getItem(position).get("name"));

//            Log.e("gary top1", Fragment2.top3[0]);
//            Log.e("gary top2", Fragment2.top3[1]);
//            Log.e("gary top3", Fragment2.top3[2]);


            //top3 에 있는지 확인
//            if(p.get("name").equals(Fragment2.top3[0]))
//                medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal1));
//            else if(p.get("name").equals(Fragment2.top3[1]))
//                medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal2));
//            else if(p.get("name").equals(Fragment2.top3[2]))
//                medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal3));

//            switch (position){
//                case 0 :
//                    ImageView medalImage = (ImageView)v.findViewById(R.id.medal);
//                    medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal1));
//                    Log.e("gary top1", (String)p.get("name"));
//                    break;
//                case 1:
//                    Log.e("gary top2", (String)p.get("name"));
//                    medalImage = (ImageView)v.findViewById(R.id.medal);
//                    medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal2));
//                    break;
//                case 2 :
//                    Log.e("gary top3", (String)p.get("name"));
//                    medalImage = (ImageView)v.findViewById(R.id.medal);
//                    medalImage.setImageDrawable(v.getResources().getDrawable(R.drawable.medal3));
//                    break;
//            }

            //텍스트 설정
            TextView groupRankText = (TextView) v.findViewById(R.id.group_rank);
            groupRankText.setText(Integer.toString(position+1));
            groupRankText.setTypeface(typeface);

            TextView groupNameText = (TextView) v.findViewById(R.id.group_name);
            groupNameText.setText((String)p.get("name"));
            groupNameText.setTypeface(typeface);

            TextView groupPointText = (TextView) v.findViewById(R.id.group_point);
            groupPointText.setText(Integer.toString((int)p.getNumber("points")) + "pt");
            groupPointText.setTypeface(typeface);
        }
        return v;
    }
}
