package mobapp.lh150094.multiplicationlh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MultiplicationLH extends AppCompatActivity {
    private Calculs calc;
    private List<Boolean> result;
    private Iterator<String> iterCalc;

    String text;

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

    }

    public void verifOp(View view) {

        TextView theOperation = (TextView) findViewById(R.id.operation);
        EditText repUser      = (EditText) findViewById(R.id.rep);





        //if(view == null) return;

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