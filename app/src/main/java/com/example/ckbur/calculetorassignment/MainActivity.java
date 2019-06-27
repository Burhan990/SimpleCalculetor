package com.example.ckbur.calculetorassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    public String display="";
    public String currentOperator="";
    public String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen=(TextView)findViewById(R.id.textView2);
       screen.setText(display);
    }

    public void onClicknumber(View v){
        if (result != ""){
            clear();
            updateScreen();
        }

        Button b=(Button) v;
        display +=b.getText();
        updateScreen();
    }

    public void updateScreen() {
        screen.setText(display);
    }
    private boolean isoperator(char op){
        switch(op){

            case '+':
            case '-':
            case 'x':
            case '/':return true;
            default: return false;
        }

    }

    public void onClickoperator(View v){
        if (display== "")return;
        Button b=(Button)v;
        if (result != ""){
            String _display=result;
            clear();
            display=_display;
        }
        if (currentOperator != ""){

            Log.d("calc",""+display.charAt(display.length()-1));
           if (isoperator(display.charAt(display.length()-1))){

              display= display.replace(display.charAt(display.length()-1),b.getText().charAt(0));
              updateScreen();
               return;

            }
            else {
               getResult();
               display=result;
               result="";//

           }
            currentOperator= b.getText().toString();
        }

        display +=b.getText();
        currentOperator=b.getText().toString();
        updateScreen();

    }
    public void clear(){
        display="";
        currentOperator="";
       result="";


    }
    public void onClickClear(View v){
        clear();
        updateScreen();

    }
    //,String c,String d,String e,String f,String g,String h,String i,String j
    public double operate(String a,String b,String op){

        switch (op){

            case "+": return Double.valueOf(a) + Double.valueOf(b);//+ Double.valueOf(c)+ Double.valueOf(d)+ Double.valueOf(e)+ Double.valueOf(f)+ Double.valueOf(g)+ Double.valueOf(h)+ Double.valueOf(i)+ Double.valueOf(j);
            case "-": return Double.valueOf(a) - Double.valueOf(b);//- Double.valueOf(c)- Double.valueOf(d)- Double.valueOf(e)- Double.valueOf(f)- Double.valueOf(g)- Double.valueOf(h)- Double.valueOf(i)- Double.valueOf(j);
            case "x": return Double.valueOf(a) * Double.valueOf(b);//* Double.valueOf(c)* Double.valueOf(d)* Double.valueOf(e)* Double.valueOf(f)* Double.valueOf(g)* Double.valueOf(h)* Double.valueOf(i)* Double.valueOf(j);
            case "/":try {
                return Double.valueOf(a) / Double.valueOf(b);// Double.valueOf(c)/ Double.valueOf(d)/ Double.valueOf(e)/ Double.valueOf(f)/ Double.valueOf(g)/ Double.valueOf(h)/ Double.valueOf(i)/ Double.valueOf(j);
            }catch (Exception e){

                Log.d("calc",e.getMessage());
            }
            default:return -1;
        }

    }
    private boolean  getResult(){
        if (currentOperator=="")return false;
        String[] operation=display.split(Pattern.quote(currentOperator));
        if (operation.length<2)return false;

        result=String.valueOf(operate(operation[0],operation[1],currentOperator));
        return true;
    }
   public void onClickEqual(View v){
        if (display=="")return;

        if(!getResult())return;
        screen.setText(display + "\n" + String.valueOf(result));

    }
}
