package com.example.lauraccs.laurademo0;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lauraccs.laurademo0.bean.Book;
import com.example.lauraccs.laurademo0.dialog.CustomDialog;
import com.example.lauraccs.laurademo0.dialog.Quiz4Dialog;
import com.example.lauraccs.laurademo0.util.UtilLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnTouchListener {
    private ImageButton bt1;
    private ImageButton bt3;
//    private ImageButton btLeft;
    private ImageButton btRight;
    private GestureDetector mGestureDetector;
    final Context  mContext = this;
    private int rdg;

    @BindView(R.id.main_fl)
    FrameLayout fl;







    @OnClick(R.id.bt_quiz4)
    public void quiz4Click(){
        final Quiz4Dialog dialog = new Quiz4Dialog(this, new Quiz4Dialog.CustomDialogEventListener() {
            @Override
            public void onClickListener() {



            }
        });
        dialog.show();




    }


    @OnClick(R.id.main_animator_bt)
    public void toAnimator(){
        toActivity(AnimatorActivity.class);
    }

    @OnClick(R.id.main_animation_bt)
    public void toAnimation(){
        toActivity(AnimationActivity.class);

    }

    @OnClick(R.id.bt2)
    public void button2Click(){
        Intent intent = new Intent(this, DialogActivity.class);
        startActivityForResult(intent, 2);
        //toActivity(DialogActivity.class);
    }

    @OnClick(R.id.main_timer_bt)
    public void timerBtClick(){
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialListener();
        ButterKnife.bind(this);
        simpleGestureListener simpleGestListener = new simpleGestureListener();
        mGestureDetector = new GestureDetector(this, new simpleGestureListener());
        fl.setOnTouchListener(this);
        mGestureDetector.setOnDoubleTapListener(simpleGestListener);
    }

    private void initialView(){
        bt1 = (ImageButton) findViewById(R.id.bt1);
        bt3 = (ImageButton) findViewById(R.id.bt3);
        btRight = (ImageButton) findViewById(R.id.button_right);
    }

    private void initialListener(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Button1 was clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(),ViewPagerActivity.class);
                intent.putExtra("key","value");
                Bundle bundle = new Bundle();
                bundle.putInt("Integer", 12345);
                Book book = new Book();
                book.setName("Android");
                book.setAuthor("Laura");
                bundle.putSerializable("book", book);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);


            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //toActivity(ListViewActivity.class);
                Intent intent = new Intent(v.getContext(),ListViewActivity.class);
                startActivityForResult(intent, 3);



            }
        });
        btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ActivityA.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                String message = data.getStringExtra("message");
                toastShort(message);
                break;
            case 2:
                toastShort("Dialog");
                break;
            case 3:

                toastShort("ListView");
                break;
            default:
        }
    }

    public void onClick(View v){
        toastLong("Button2 was clicked");
        UtilLog.logD("testD","Toast");

//        Toast.makeText(this, "Button2 was clicked", Toast.LENGTH_LONG).show();
//        Log.d("testD","Toast1");
    }

    @Override
    protected void onStart() {
        toastShort("onStart");
        super.onStart();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class simpleGestureListener extends  GestureDetector.SimpleOnGestureListener{

        public boolean onSingleTapConfirmed(MotionEvent e) {
            toastShort("onSingleTapConfirmed");
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            toastShort("onDoubleTap");
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            toastShort("onDoubleTapEvent");
            return true;
        }

        public boolean onDown(MotionEvent e) {
            toastShort("onDown");
            return true;
        }

        public void onShowPress(MotionEvent e) {
            toastShort("onShowPress");

        }

        public boolean onSingleTapUp(MotionEvent e) {
            toastShort("onSingleTapUp");
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            toastShort("onScroll");
            return true;
        }

        public void onLongPress(MotionEvent e) {
            toastShort("onLongPress");

        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            toastShort("onFling");
            return true;
        }



    }
}
