package com.example.dilakshika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.dilakshika.Database.DBHelper;
import com.example.dilakshika.Database.User;

public class Register extends AppCompatActivity {

    private EditText username,password;
    private Button register;
    private CheckBox student,teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        student =findViewById(R.id.student);
        teacher = findViewById(R.id.teacher);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(Register.this);

                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                if(student.isChecked()){
                    user.setType("Student");
                }else
                    user.setType("teacher");

                dbHelper.add(user);
            }
        });

    }
    public void Type(View V){
        if(((CheckBox)V).isChecked()){
            switch (V.getId()){
                case R.id.student : {
                    teacher.setEnabled(false);
                }
                case R.id.teacher : {
                    student.setEnabled(false);
                }
            }
        }

    }
}
