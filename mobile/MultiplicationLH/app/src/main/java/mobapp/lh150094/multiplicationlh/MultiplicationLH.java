package mobapp.lh150094.multiplicationlh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MultiplicationLH extends AppCompatActivity {
    private Calculs calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_lh);

        this.calc = new Calculs(6);
    }

    public void verifOp(View view) {
        TextView theOperation = (TextView) findViewById(R.id.operation);

        if(calc.hasNext()) theOperation.setText(calc.next());
        else               theOperation.setText("fini");
    }
}
