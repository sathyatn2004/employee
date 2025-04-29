package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.Typeface;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    LinearLayout employeeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeContainer = findViewById(R.id.employeeContainer);

        // JSON with 2 employee entries
        String jsonString = "{ \"employees\": [" +
                "{ \"id\": \"EMP10234\", \"name\": \"Alice Johnson\", \"designation\": \"Senior Android Developer\", \"department\": \"Mobile Development\", \"email\": \"alice.johnson@example.com\", \"phone\": \"+1-555-0199\", \"joining_date\": \"2020-06-15\", \"location\": \"New York\" }," +
                "{ \"id\": \"EMP20456\", \"name\": \"Bob Smith\", \"designation\": \"UI/UX Designer\", \"department\": \"Design\", \"email\": \"bob.smith@example.com\", \"phone\": \"+1-555-0421\", \"joining_date\": \"2019-11-03\", \"location\": \"San Francisco\" }" +
                "] }";

        try {
            JSONObject root = new JSONObject(jsonString);
            JSONArray employees = root.getJSONArray("employees");

            for (int i = 0; i < employees.length(); i++) {
                JSONObject emp = employees.getJSONObject(i);
                addEmployeeView(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEmployeeView(JSONObject emp) {
        Context context = this;

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 0, 0, 24);

        try {
            layout.addView(createTextView(context, "ID: " + emp.getString("id"), true));
            layout.addView(createTextView(context, "Name: " + emp.getString("name"), false));
            layout.addView(createTextView(context, "Designation: " + emp.getString("designation"), false));
            layout.addView(createTextView(context, "Department: " + emp.getString("department"), false));
            layout.addView(createTextView(context, "Email: " + emp.getString("email"), false));
            layout.addView(createTextView(context, "Phone: " + emp.getString("phone"), false));
            layout.addView(createTextView(context, "Joining Date: " + emp.getString("joining_date"), false));
            layout.addView(createTextView(context, "Location: " + emp.getString("location"), false));

        } catch (Exception e) {
            e.printStackTrace();
        }

        employeeContainer.addView(layout);
    }

    private TextView createTextView(Context context, String text, boolean isBold) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(18);
        textView.setPadding(0, 4, 0, 4);
        if (isBold) textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }
}

