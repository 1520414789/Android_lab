package com.chenzewei.lab2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.head_portrait);
        alertDialog.setItems(R.array.get_photo,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        if(i == 0){
                            Toast.makeText(getApplicationContext(), R.string.take_phote_click,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), R.string.photo_album_click,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        alertDialog.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        Toast.makeText(getApplicationContext(), R.string.cancel_click,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        alertDialog.create();
        ImageView ima = (ImageView) findViewById(R.id.image_SYSU);
        if (ima != null) {
            ima.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    alertDialog.show();
                }
            });
        }

        class myaffirm implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),R.string.affirm_click,Toast.LENGTH_SHORT).show();
            }
        }
        RadioGroup RG = (RadioGroup) findViewById(R.id.group_occupation);
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Snackbar snackbar;
                if(i == R.id.button_student){
                    snackbar = Snackbar.make(radioGroup,R.string.student_click,Snackbar.LENGTH_SHORT);
                }
                else{
                    snackbar = Snackbar.make(radioGroup,R.string.teacher_click,Snackbar.LENGTH_SHORT);
                }
                snackbar.setAction(R.string.affirm,new myaffirm());
                snackbar.show();
            }
        });

        Button sign_in = (Button) findViewById(R.id.button_sign_in);
        final TextInputLayout input_SID = (TextInputLayout) findViewById(R.id.Input_SID);
        final TextInputLayout input_password = (TextInputLayout) findViewById(R.id.Input_password);
        sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String student_ID = input_SID.getEditText().getText().toString();
                final String password = input_password.getEditText().getText().toString();
                if(TextUtils.isEmpty(student_ID)){
                    input_SID.setErrorEnabled(true);
                    input_SID.setError("学号不能为空");
                }
                else if(TextUtils.isEmpty(password)){
                    input_SID.setError("");
                    input_SID.setErrorEnabled(false);
                    input_password.setErrorEnabled(true);
                    input_password.setError("密码不能为空");
                }
                else{
                    input_SID.setErrorEnabled(false);
                    input_password.setErrorEnabled(false);
                    if(student_ID.equals("123456") && password.equals("6666") ){
                        Snackbar.make(view, R.string.success, Snackbar.LENGTH_SHORT).setAction(R.string.affirm, new myaffirm()).show();
                    }
                    else {
                        Snackbar.make(view,R.string.error,Snackbar.LENGTH_SHORT).setAction(R.string.affirm,new myaffirm()).show();
                    }
                }
            };
        });

        Button sign_up = (Button) findViewById(R.id.button_sign_up);
        sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                RadioButton Rbs = (RadioButton) findViewById(R.id.button_student);
                if(Rbs.isChecked()){
                    Snackbar.make(view,R.string.student_sign_up,Snackbar.LENGTH_SHORT).show();
                }
                else{
                    Snackbar.make(view,R.string.teacher_sign_up,Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

}
