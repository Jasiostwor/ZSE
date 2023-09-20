package com.example.szachownica;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Panel P;
    Paint tlo_pola = new Paint();
    Paint tlo_pola2 = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(P=new Panel(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.tworca:
                Context context = getApplicationContext();
                CharSequence text = "Twórca: Jan Horodecki";
                int czas = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,czas);
                toast.setGravity(Gravity.BOTTOM|Gravity.BOTTOM, 0, 0);
                toast.show();
                return true;
            case R.id.wyjscie:
                finish();
                return true;
            case R.id.kolor1:
                tlo_pola.setColor(Color.BLACK);
                tlo_pola2.setColor(Color.WHITE);
                P.postInvalidate();
                return true;
            case R.id.kolor2:
                tlo_pola.setColor(Color.RED);
                tlo_pola2.setColor(Color.YELLOW);
                P.postInvalidate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class Panel extends View{

        public Panel(Context context){
            super(context);
            tlo_pola.setColor(Color.WHITE);
            tlo_pola2.setColor(Color.BLACK);
        }

        @Override
        public void onDraw(Canvas canvas){
            canvas.drawColor(Color.GRAY);
            float width = P.getWidth();

            for (int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if((i+j)%2 == 0){
                        canvas.drawRect(i*width/8,(i+1)*width/8,(j+1)*width/8, tlo_pola);
                    }else{
                        canvas.drawRect(i*width/8,(i+1)*width/8,(j+1)*width/8, tlo_pola2);
                    }
                }
            }
        }
    }
}