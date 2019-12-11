package com.fitcheck.ui.more;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.User;
import com.fitcheck.MainActivity;
import com.fitcheck.MainMenuActivity;
import com.fitcheck.R;

import java.util.List;

public class MoreFragment extends Fragment {

    private SQLiteDatabase db;
    private Cursor cursor;

    public EditText m_name;
    public EditText m_surname;
    public EditText m_email;
    public EditText m_number;

    private MoreViewModel moreViewModel;

    protected String name, second_name, password, email;
    // protected int user_id;


     DatabaseHandler db1;

    EditText editText_name;
    EditText editText_second_name;
    EditText editText_password;
    EditText editText_email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moreViewModel =
                ViewModelProviders.of(this).get(MoreViewModel.class);
        View v = inflater.inflate(R.layout.fragment_more, container, false);

        //m_name = (EditText) findViewById(R.id.name_more);
        // m_surname = (EditText) findViewById(R.id.surname_more);
        // m_email = (EditText) findViewById(R.id.email_more);
        // m_number = (EditText) findViewById(R.id.number_more);





        db1 = new DatabaseHandler(v.getContext());
        List<User> dataUser = db1.getAllUser();
        for (User userD : dataUser){
            //user_id = userD.get_id();
            name = userD.get_name();
            second_name = userD.get_second_name();
            email = userD.get_email();
            password = userD.get_password();

        }



        editText_name   = (EditText) v.findViewById(R.id.name_more);
        editText_second_name = (EditText) v.findViewById(R.id.surname_more);
        editText_email = (EditText) v.findViewById(R.id.email_more);
        editText_password = (EditText) v.findViewById(R.id.pass_more);

        editText_name.setText(name);
        editText_second_name.setText(second_name);
        editText_email.setText(email);
        editText_password.setText(password);




      //  button2.setOnClickListener((View.OnClickListener) this);





        return v;
    }
}