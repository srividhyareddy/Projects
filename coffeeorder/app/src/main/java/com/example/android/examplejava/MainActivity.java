package com.example.android.examplejava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.name;
import static android.R.attr.numberPickerStyle;
import static android.R.attr.order;
import static android.R.attr.text;
import static android.R.attr.x;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQunatity(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }quantity = quantity - 1;

        displayQunatity(quantity);
    }


    public void submitOrder(View view) {

        EditText nameField = (EditText)findViewById(R.id.Edit_text_View);
        String name =  nameField.getText().toString();


            // Figure out if the user wants whipped cream topping
            CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
            boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

            // Figure out if the user wants whipped cream topping
            CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
            boolean hasChocolate = chocolateCheckBox.isChecked();

            // Calculate the price
            int price = calculatePrice(hasWhippedCream, hasChocolate);
              String message = createOrderSummary(name,price, hasWhippedCream, hasChocolate);
               displayMessage(message);



    }


    private int calculatePrice(boolean addWhipCream , boolean addChocoCream){
         int baseline = 10;
        if(addWhipCream) {
            baseline = baseline + 1;
        }
        if (addChocoCream){
            baseline= baseline+2;
        }
        return quantity * 10;
    }

    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(String name,int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name:"  +  name ;

        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        return priceMessage;
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQunatity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextview = (TextView) findViewById(R.id.order_summary_text_View);
        orderSummaryTextview.setText(message);

    }
}