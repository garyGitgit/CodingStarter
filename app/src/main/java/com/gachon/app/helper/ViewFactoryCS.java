package com.gachon.app.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 *
 * Created by garyNoh on 2017. 5. 7..
 *
 *
 * view 를 쉽게 생성해주는 클래스임
 * 페이지는 크게 card 들로 구성
 *
 */

public class ViewFactoryCS {

    LinearLayout root; //root 는 한 페이지의 가장 최상위 root linear layout 임
    Context rootContext;
    WidgetSet widgetSet;

    LinearLayout answerLayout;

    /**
     * root 와 rootContext 를 설정
     *
     * @param linearLayout : 최상단 레이아웃
     */
    public ViewFactoryCS(LinearLayout linearLayout){
        root = linearLayout;
        rootContext = root.getContext();
        widgetSet = new WidgetSet();
    }



    /**
     * card 를 생성
     *
     * @param weight : card 가 차지하는 height 비율
     * @param color : card 배경색
     * @param isVertical : card 내부가 orientation(horizontal/vertical)
     * @return card 내부를 linearlayout 으로 만들고 그 linearlayout 을 반환
     */
    public LinearLayout createCard(float weight, int color, boolean isVertical, int[] margins){

        //카드 생성
        CardView cardView = new CardView(rootContext);

        TableLayout.LayoutParams params;

        //weight 설정
        if(weight > 0)
            params = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, weight);
        else
            params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

        //margin 설정
        params.setMargins(
                WidgetSet.getPxFromDp(margins[0]),
                WidgetSet.getPxFromDp(margins[1]),
                WidgetSet.getPxFromDp(margins[2]),
                WidgetSet.getPxFromDp(margins[3]));
        cardView.setLayoutParams(params);

        //카드 안에 넣을 linear layout 생성 후 width, height 설정
        LinearLayout linearLayout = new LinearLayout(rootContext);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));

        //linear layout orientation 설정
        if(isVertical) linearLayout.setOrientation(LinearLayout.VERTICAL);
        else linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //카드 배경 색깔 설정
        linearLayout.setBackgroundColor(color);

        //카드 테두리 설정
        //linearLayout.setBackground(root.getResources().getDrawable(R.drawable.cardborder));

        //카드에 linear layout 설정
        cardView.addView(linearLayout);

        //루트에 카드 추가
        root.addView(cardView);

        return linearLayout;
    }

    /**
     * table layout 을 생성
     *
     * @param weight : card 가 차지하는 height 비율
     * @param color : card 배경색
     * @return
     */
    public TableLayout createTableCard(float weight, int color, int[] margins){
        //카드 생성
        CardView cardView = new CardView(rootContext);

        //weight 설정
        TableLayout.LayoutParams params;

        if(weight > 0)
                params = new TableLayout.LayoutParams(0, 0, weight);
        else
            params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

        //margin 설정
        params.setMargins(
                WidgetSet.getPxFromDp(margins[0]),
                WidgetSet.getPxFromDp(margins[1]),
                WidgetSet.getPxFromDp(margins[2]),
                WidgetSet.getPxFromDp(margins[3]));

        cardView.setLayoutParams(params);

        //table layout 생성
        TableLayout tableLayout = new TableLayout(rootContext);

        //weight 가 있을 때는 weight 를 맞추고 0일 때는 wrap content 로 parameter 설정
        tableLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));

        //카드 배경색 설정
        tableLayout.setBackgroundColor(color);

        //카드 테두리 설정
        //tableLayout.setBackground(root.getResources().getDrawable(R.drawable.cardborder));

        //카드에 table layout 추가
        cardView.addView(tableLayout);

        //루트에 카드 추가
        root.addView(cardView);
        return tableLayout;
    }

    /**
     * table layout 에 행을 추가
     *
     * @param views : 행 안에 들어갈 뷰 리스트
     * @param parent : 행이 들어갈 부모 table layout
     */
    public void addRow(View[] views, TableLayout parent){
        //table row 생성
        TableRow tableRow = new TableRow(rootContext);

        //table row 의 width & height 설정
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        tableRow.setLayoutParams(params);

        //row 에 뷰 리스트 추가
        for(View v : views) tableRow.addView(v);

        //table layout 에 table row 에 추가
        parent.addView(tableRow);
    }


    /**
     * 큰 텍스트를 추가 / 애니메이션 추가 가능
     *
     * @param str : text 값
     * @param size : text 크기
     * @param parent : text 가 추가될 부모 뷰
     */
    public void addSimpleText(String str, int size, LinearLayout parent){

        //textview 생성
        TextView textView = new TextView(rootContext);

        //textView width & height 설정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        //text parser : **...** 나타나기 효과 : 고정값
        if(str.startsWith("**") && str.endsWith("**")){
            YoYo.with(Techniques.FadeIn)
                    .duration(2000)
                    .playOn(textView);
            str = str.substring(2, str.length()-2);
        }

        //text parser : [[...]] 는 질문 빈칸으로 간주
        if(str.contains("[[") && str.contains("]]")){
            String answer = str.substring(str.indexOf("[[")+2, str.indexOf("]]"));
            int s = answer.length();
            String guessWhat = "";
            Log.e("garynoh", Integer.toString(s));
            for(int i = 0; i < s; i++){
                guessWhat += "_";
            }
            //Log.e("garynoh", Integer.toString(str.indexOf("[[")) + Integer.toString(str.indexOf("]]")));
            str = str.replace(str.substring(str.indexOf("[["), str.indexOf("]]")+2), guessWhat);

        }


        //text 와 text 크기 설정
        textView.setText(str);
        textView.setTextSize(size);

        //부모 레이아웃에 추가
        parent.addView(textView);
    }

    /**
     * 카드에 이미지 추가
     *
     *
     * @param image : 이미지 리소스
     * @param parent : 이미지가 나타날 부모 뷰
     */
    public void addImage(Drawable image, LinearLayout parent){
        //이미지 뷰 생성
        ImageView imageView = new ImageView(rootContext);

        //weight 지정
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        imageView.setLayoutParams(params);

        //이미지 가져옴
        imageView.setImageDrawable(image);

        //부모 레이아웃에 추가
        parent.addView(imageView);
    }

    /**
     *
     * @param width : 이미지 가로
     * @param height : 이미지 세로
     * @param image : 이미지 리소스
     * @param parent : 이미지가 나타날 부모 뷰
     */
    public void addImage(int width, int height, Drawable image, LinearLayout parent){
        //이미지 뷰 생성
        ImageView imageView = new ImageView(rootContext);

        //weight 지정
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(width, height, 1.0f);
        imageView.setLayoutParams(params);

        //이미지 가져옴
        imageView.setImageDrawable(image);

        //부모 레이아웃에 추가
        parent.addView(imageView);
    }


    /**
     * 카드에 사용할 위젯들을 생성
     *
     * @param viewType : string 으로 입력한 view 의 type
     * @param str : view 을 구성할 때 사용할 string array (text, spinner item 등)
     * @return
     */
    public View createWidget(String viewType, String[] str){

        //button 생성
        if(viewType.equalsIgnoreCase("Button")){
            Button button = new Button(rootContext); // 생성

            button.setText(str[0]); //text 설정

            //이벤트 리스너
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return button;
        }
        //spinner 생성, str 은 spinner 를 구성할 string item
        else if(viewType.equalsIgnoreCase("Spinner")){
            Spinner spinner = new Spinner(rootContext);

            //widget set 에 저장
            widgetSet.setSpinner(spinner);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootContext, android.R.layout.simple_spinner_item, str);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            return spinner;
        }
        //edit text 생성
        else if(viewType.equalsIgnoreCase("EditText")){
            EditText editText = new EditText(rootContext);

            //widget set 에 저장
            widgetSet.setEditText(editText);

            editText.setHint(str[0]); // hint 설정
            return editText;
        }
        //text view 생성
        else if(viewType.equalsIgnoreCase("TextView")){
            TextView textView = new TextView(rootContext);
            textView.setText(str[0]); // text 설정
            return textView;
        }
        else return null;
    }

    public WidgetSet getWidgetSet(){
        return widgetSet;
    }

    /**
     * 보기가 되는 block들을 생성
     *
     * @param blockTexts : block 으로 만들 버튼 텍스트
     * @param table : block들이 배치될 레이아웃
     * @param answerLayout : block들을 탭했을 때 탭버튼이 반영될 레이아웃
     */
    public void createBlocks(String[] blockTexts, TableLayout table, LinearLayout answerLayout){
        //정답 구성 레이아웃 설정
        this.answerLayout = answerLayout;

        int size = blockTexts.length; //block 갯수
        Button[] blocks = new Button[size];

        //버튼 생성
        for(int i = 0 ; i < size; i ++){
            blocks[i] = (Button) createWidget("Button", new String[]{blockTexts[i]});
            blocks[i].setOnClickListener(blockClickListener);
        }
        //레이아웃에 버튼을 추가
        addRow(blocks, table);
    }

    //answer block 클릭 리스너
    private Button.OnClickListener blockClickListener =  new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            Toast.makeText(rootContext, button.getText(), Toast.LENGTH_SHORT).show();

            //block 이 생성되었을 때 answerLayout 은 null 이 아님 (밀접한 관계)
            if(answerLayout != null){
                //사용자가 입력한 블록 생성
                Button userInput = (Button)createWidget("Button", new String[]{button.getText().toString()});
                userInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //블록을 제거
                        answerLayout.removeView(v);
                    }
                });
                answerLayout.addView(userInput);
            }
        }
    };


}
