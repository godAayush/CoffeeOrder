/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

        int numberCups = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox che = (CheckBox)findViewById(R.id.whipped_check_text_view);
        boolean whippp = che.isChecked();
        CheckBox chechchoc = (CheckBox)findViewById(R.id.chocolate_check_text_view);
        boolean choc = chechchoc.isChecked();
        EditText namview = (EditText)findViewById(R.id.name_view);
        String name = namview.getText().toString();
        int prrr = calculatePrice(whippp,choc);
        String emailField = createOrderSummary(prrr,whippp,choc,name);
        String emailSubject = "JustJava order for "+name;
        composeEmail(emailSubject,emailField);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    /**
    private void displayPrice(String s) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(s);
    }
     */

    public void composeEmail( String subject, String contentText) {
        //Log.i("email","subject "+subject);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, contentText);
        //Log.i("email","line 79");
        if (intent.resolveActivity(getPackageManager()) != null) {
            //Log.i("email","line 81 true");
            startActivity(intent);
        }
    }

    private int calculatePrice(boolean whip,boolean choc)
    {
        int base = 5;
        if(whip)
            base++;
        if(choc)
            base+=2;
        return base*numberCups;
    }

    private String createOrderSummary(int prr,boolean whipp,boolean chocc,String namm)
    {

        String s ="Name: "+namm+"\nAdd whipped cream? "+whipp+"\nAdd chocolate? "+chocc+ "\nQuantity: "+numberCups+"\nTotal: "+(NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(prr)) + "\nThank you!";
        return s;
    }

    public void increment(View view)
    {
        if(numberCups<100)
            numberCups++;
        else
        {
            CharSequence text = "You cannot have more than 100 coffees!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
        display(numberCups);
    }
    public void decrement(View view)
    {
        if(numberCups>1)
            numberCups--;
        else
        {
            CharSequence text = "You cannot have less than 1 coffee!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
        display(numberCups);
    }
}