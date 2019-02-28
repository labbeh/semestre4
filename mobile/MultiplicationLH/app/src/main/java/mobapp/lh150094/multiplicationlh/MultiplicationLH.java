package mobapp.lh150094.multiplicationlh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MultiplicationLH extends AppCompatActivity {
    private Calculs calc;
    private List<Boolean> result;
    private Iterator<String> iterCalc;

    /*TextView theOperation;
    EditText repUser;*/

    String text;

    private int nbResume = 0;
    private int nbPause = 0;
    private int nbDestroy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_lh);

        this.calc = new Calculs(6);
        this.iterCalc = calc.iterator();
        this.result = new LinkedList<>();

        text = iterCalc.next();

        //this.verifOp(null);
        TextView theOperation = (TextView) findViewById(R.id.operation);
        theOperation.setText(text);

        /*Toast myToast = Toast.makeText(this, "curs: " +calc.getCurs(), Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();*/

    }

    @Override
    protected void onStart(){
        super.onStart();
        /*Toast myToast = Toast.makeText(this, "on start", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        /*Toast myToast = Toast.makeText(this, "on resume " + (++nbResume), Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();*/
        Toast myToast = Toast.makeText(this, "curs: " +calc.getCurs(), Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast myToast = Toast.makeText(this, new String("on pause " + (++nbPause)), Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
       /* Toast myToast = Toast.makeText(this, "on destroy " + (++nbDestroy), Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();*/
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        bundle.putInt("nbPause", nbPause);
        /*private Calculs calc;
        private List<Boolean> result;
        private Iterator<String> iterCalc;*/
        bundle.putSerializable("calc", calc);
        bundle.putSerializable("result", (LinkedList<Boolean>)result);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);
        nbPause = bundle.getInt("nbPause");
        calc = (Calculs) bundle.getSerializable("calc");
        result = (List<Boolean>)bundle.getSerializable("result");
        this.iterCalc = calc.iterator();
    }

    public void verifOp(View view) {

        TextView theOperation = (TextView) findViewById(R.id.operation);
        EditText repUser      = (EditText) findViewById(R.id.rep);

        if(view != null && !text.equals("fini")) {
            Scanner sc = new Scanner(theOperation.getText().toString().replaceAll(" ", ""));
            theOperation.setText(theOperation.getText().toString().replaceAll(" ", ""));

            sc.useDelimiter("x");

            int op1 = Integer.parseInt(sc.next());
            int op2 = Integer.parseInt(sc.next().replaceAll("=", ""));

            this.result.add((op1 * op2) == Integer.parseInt(repUser.getText().toString()));

            // DEBUG
            TextView tw = findViewById(R.id.affichageReponses);
            tw.setText(op1 + " x " + op2 + " = " +(op1*op2) + " rep " +Integer.parseInt(repUser.getText().toString()));
        }
        else if(text.equals("fini")) {
            Button btn = findViewById(R.id.btnValider);
            btn.setEnabled(false);

            iterCalc = calc.iterator();

            StringBuilder affResult = new StringBuilder();

            for(Boolean b: result){
                affResult.append(iterCalc.next() +": " +b+ "\n");
            }

            afficherReponses(affResult.toString());
        }

        repUser.setText("");

        if(iterCalc.hasNext()) text = iterCalc.next();
        else                   text = "fini";

        theOperation.setText(text);


    }

    public void afficherReponses(String affichage){
        TextView tw = findViewById(R.id.affichageReponses);
        tw.setText(affichage);
    }
}