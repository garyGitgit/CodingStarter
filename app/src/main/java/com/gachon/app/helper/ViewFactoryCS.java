package com.gachon.app.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gachon.app.R;

import java.util.LinkedList;

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
    //문제풀이에서의 answer list

    //blank 리스트
    LinkedList<String> userInputList;
    LinkedList<TextView > remainList;
    int type = 0;

    //question 수
    int questionCnt = 0;

    /**
     * root 와 rootContext 를 설정
     *
     * @param linearLayout : 최상단 레이아웃
     */
    public ViewFactoryCS(LinearLayout linearLayout){
        root = linearLayout;
        rootContext = root.getContext();
        widgetSet = new WidgetSet();

        userInputList = new LinkedList<>();
        remainList = new LinkedList<>();
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

        //TableLayout.LayoutParams params;
        LinearLayout.LayoutParams params;

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
        //padding 설정
        linearLayout.setPadding(
                WidgetSet.getPxFromDp(10),
                WidgetSet.getPxFromDp(10),
                WidgetSet.getPxFromDp(10),
                WidgetSet.getPxFromDp(10)
        );

        //linear layout orientation 설정
        if(isVertical) linearLayout.setOrientation(LinearLayout.VERTICAL);
        else linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //카드 배경 색깔 설정
        linearLayout.setBackgroundColor(color);

        //카드 테두리 설정
        //linearLayout.setBackground(root.getResources().getDrawable(R.drawable.cardborder));

        //카드에 linear layout 설정

        FrameLayout frameLayout = new FrameLayout(rootContext);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.addView(linearLayout);
        cardView.addView(frameLayout);
        //cardView.addView(linearLayout);

        //루트에 카드 추가
        root.addView(cardView);

        return linearLayout;
    }

    public FrameLayout createCard(float weight, int[] margins){

        //카드 생성
        CardView cardView = new CardView(rootContext);

        //TableLayout.LayoutParams params;
        LinearLayout.LayoutParams params;

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

        //프레임 레이아웃 추가
        FrameLayout frameLayout = new FrameLayout(rootContext);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //패팅 추가 : 카드도 사이즈가 작아진다 
//        frameLayout.setPadding(
//                WidgetSet.getPxFromDp(10),
//                WidgetSet.getPxFromDp(10),
//                WidgetSet.getPxFromDp(10),
//                WidgetSet.getPxFromDp(10)
//        );
        frameLayout.addView(linearLayout);
        cardView.addView(frameLayout);
        //cardView.addView(linearLayout);

        //루트에 카드 추가
        root.addView(cardView);

        return frameLayout;
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

        //padding 설정
        tableLayout.setPadding(
                WidgetSet.getPxFromDp(10),
                WidgetSet.getPxFromDp(10),
                WidgetSet.getPxFromDp(10),
                WidgetSet.getPxFromDp(10)
        );

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
//
//    public void addRow(View[] views, TableLayout tableLayout, ScrollView scrollView){
//        //table row 생성
//        TableRow tableRow = new TableRow(rootContext);
//
//        //table row 의 width & height 설정
//        TableRow.LayoutParams params = new TableRow.LayoutParams(
//                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
//        tableRow.setLayoutParams(params);
//
//        //row 에 뷰 리스트 추가
//        for(View v : views) tableRow.addView(v);
//
//        //table layout 에 table row 에 추가
//        ta
//        scrollView.addView(tableRow);
//    }

    public void addSpace(float weight){
        Space space = new Space(rootContext);
        space.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, weight));

        root.addView(space);

    }

    /**
     * 큰 텍스트를 추가 / 애니메이션 추가 가능
     *
     * @param str : text 값
     * @param size : text 크기
     * @param parent : text 가 추가될 부모 뷰
     */
    public void addSimpleText(String str, int size, ViewGroup parent){

        //textview 생성
        TextView textView = new TextView(rootContext);

        //card


        AutoResizeTextView autoResizeTextView = new AutoResizeTextView(rootContext);

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

        //TODO : 잘리지 않게 텍스트 크기 조정
        //text 와 text 크기 설정
//        textView.setText(str);
//        textView.setTextSize(size);
        autoResizeTextView.setText(str);
        autoResizeTextView.setTextSize(size);

        //부모 레이아웃에 추가
        parent.addView(autoResizeTextView);
    }

    public void addQuestion(String str, int size, TableLayout parent){
        //text parser : [[...]] 는 질문 빈칸으로 간주
        if(str.contains("[[") && str.contains("]]")){

            TextView front = (TextView) createWidget("TextView", new String[]{str.substring(0, str.indexOf("[["))});


            //답 체크할 때 비교!
            String answer = str.substring(str.indexOf("[[")+2, str.indexOf("]]"));
            final TextView blank = (TextView) createWidget("TextView", new String[]{"          "});
            //테두리 추가
            blank.setBackground(rootContext.getResources().getDrawable(R.drawable.cardborder));
            //blank answer 추가, 아이디 태그 붙여서
            blank.setTag(questionCnt++);
            remainList.add(blank);
            TextView next = (TextView) createWidget("TextView", new String[]{str.substring(str.indexOf("]]") +2)});


            front.setTextSize(size);
            blank.setTextSize(size);
            blank.setGravity(Gravity.CENTER);
            blank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    blank.setText("          ");
                    //소팅해서 추가
                    Log.e("garynoh", (blank.getTag()).toString());
                    sortByTag(blank);
                }
            });
            next.setTextSize(size);

            addRow(new View[]{front, blank, next}, parent);
        }
    }


    void sortByTag(TextView blank){
        int blankId = (Integer)blank.getTag();
        int i = 0;
        for(TextView textView : remainList){
            int tagId = (Integer)textView.getTag();
            if(blankId < tagId) break;
            i++;
        }
        remainList.add(i, blank);
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(WidgetSet.getPxFromDp(width), WidgetSet.getPxFromDp(height), 1.0f);
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


        if(viewType.equalsIgnoreCase("ImageButton")){
            ImageButton button = new ImageButton(rootContext); // 생성

            //button.setText(str[0]); //text 설정

            //이벤트 리스너
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
            return button;
        }
        //button 생성
        else if(viewType.equalsIgnoreCase("Button")){
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
        //radio button 생성
        else if(viewType.equalsIgnoreCase("RadioButton")){
            RadioGroup radioGroup = new RadioGroup(rootContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            radioGroup.setOrientation(RadioGroup.VERTICAL);
            int size = str.length;
            for(int i = 0 ; i < size; i++){
                RadioButton radioButton = new RadioButton(rootContext);
                radioButton.setText(str[i]);
                radioGroup.addView(radioButton);
            }
            return radioGroup;
        }
        else return null;
    }

    public WidgetSet getWidgetSet(){
        return widgetSet;
    }

//    /**
//     * 보기가 되는 block들을 생성
//     *
//     * @param blockTexts : block 으로 만들 버튼 텍스트
//     * @param table : block들이 배치될 레이아웃
//     * @param answerLayout : block들을 탭했을 때 탭버튼이 반영될 레이아웃
//     */
//    public void createBlocks(String[] blockTexts, TableLayout table, LinearLayout answerLayout){
//        //정답 구성 레이아웃 설정
//        this.answerLayout = answerLayout;
//
//
//        int size = blockTexts.length; //block 갯수
//        Button[] blocks = new Button[size];
//
//        ScrollView scrollView = new ScrollView(rootContext);
//        LinearLayout linearLayout = new LinearLayout(rootContext);
//        //버튼 생성
//        for(int i = 0 ; i < size; i ++){
//            blocks[i] = (Button) createWidget("Button", new String[]{blockTexts[i]});
//            blocks[i].setOnClickListener(blockClickListener);
//            linearLayout.addView(blocks[i]);
//        }
//        scrollView.addView(linearLayout);
//        addRow(new View[]{scrollView},table);
//        //레이아웃에 버튼을 추가
//        //addRow(blocks, table);
//    }

    public void createBlocks(String[] blockTexts, HorizontalScrollView scrollView, LinearLayout answerLayout, int type){
        //정답 구성 레이아웃 설정
        this.answerLayout = answerLayout;
        //문제 타입 설정
        this.type = type;

        int size = blockTexts.length; //block 갯수
        //Button[] blocks = new Button[size];
        TextView[] blocks = new TextView[size];
        LinearLayout linearLayout = new LinearLayout(rootContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(WidgetSet.getPxFromDp(5), WidgetSet.getPxFromDp(5), WidgetSet.getPxFromDp(5), WidgetSet.getPxFromDp(5));
        //버튼 생성
        for(int i = 0 ; i < size; i ++){
            //blocks[i] = (Button) createWidget("Button", new String[]{blockTexts[i]});
            blocks[i] = (TextView) createWidget("TextView", new String[]{blockTexts[i]});
            //padding 설정
            blocks[i].setPadding(50, 10, 50, 10);
            //크기 설정
            blocks[i].setTextSize(20);
            //border 배경 설정

            blocks[i].setBackground(rootContext.getResources().getDrawable(R.drawable.cardborder));
            blocks[i].setLayoutParams(params);
            blocks[i].setOnClickListener(blockClickListener);

            linearLayout.addView(blocks[i]);
        }
        scrollView.addView(linearLayout);
        //addRow(new View[]{scrollView},table);
        //레이아웃에 버튼을 추가
        //addRow(blocks, table);
    }



    public HorizontalScrollView createHorizontalScrollViewCard(float weight, int color, int[] margins){
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

        //scrollview 생성
        HorizontalScrollView  scrollView = new HorizontalScrollView(rootContext);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));

        //카드 배경색 설정
        scrollView .setBackgroundColor(color);

        //카드 테두리 설정
        //tableLayout.setBackground(root.getResources().getDrawable(R.drawable.cardborder));

        //카드에 table layout 추가
        cardView.addView(scrollView);

        //루트에 카드 추가
        root.addView(cardView);
        return scrollView ;
    }


    public ScrollView createVerticalScrollViewCard(float weight, int color, int[] margins){

        CardView cardView = new CardView(rootContext);
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

        //scrollview 생성
        ScrollView  scrollView = new ScrollView(rootContext);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));

        //카드 배경색 설정
        scrollView .setBackgroundColor(color);

        //카드 테두리 설정
        //tableLayout.setBackground(root.getResources().getDrawable(R.drawable.cardborder));

        //카드에 table layout 추가
        cardView.addView(scrollView);

        //루트에 카드 추가
        root.addView(cardView);
        return scrollView;

    }


    public void addView(View v, LinearLayout parent){

        //weight 지정
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        v.setLayoutParams(params);

        //부모 레이아웃에 추가
        parent.addView(v);
    }

    //answer block 클릭 리스너
    private Button.OnClickListener blockClickListener =  new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //Button button = (Button)v;
            TextView block = (TextView) v;
            //Toast.makeText(rootContext, block.getText(), Toast.LENGTH_SHORT).show();


            switch (type){
                case 1:
                    //block 이 생성되었을 때 answerLayout 은 null 이 아님 (밀접한 관계)
                    if(answerLayout != null){
                        //사용자가 입력한 블록 생성
                        //Button userInput = (Button)createWidget("Button", new String[]{block.getText().toString()});
                        TextView userInput = (TextView)createWidget("TextView", new String[]{block.getText().toString()});
                        userInput.setBackground(rootContext.getResources().getDrawable(R.drawable.cardborder));
                        LinearLayout.LayoutParams params =
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(WidgetSet.getPxFromDp(5),WidgetSet.getPxFromDp(5),WidgetSet.getPxFromDp(5),WidgetSet.getPxFromDp(5));
                        userInput.setLayoutParams(params);
                        userInput.setPadding(50, 10, 50, 10);
                        userInput.setTextSize(20);
                        userInput.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //블록을 제거
                                answerLayout.removeView(v);
                            }
                        });
                        answerLayout.addView(userInput);
                    }
                    break;
                case 2:
                    String userInput = block.getText().toString();
                    //입력한 답은 답안지에 추가가 된다
                    userInputList.add(userInput);
                    //가장 앞에 있는 빈칸이 채워진다
                    if(!remainList.isEmpty())
                        remainList.removeFirst().setText(userInput);
                    break;
            }

        }
    };





}
