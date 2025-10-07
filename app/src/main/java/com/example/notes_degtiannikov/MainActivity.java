package com.example.notes_degtiannikov;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int IdNoteEdit = -1;
    public List<Notes> AllNotes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void OnAddNote(View view)
    {
        if(view.getTag().toString().isEmpty())
            IdNoteEdit = (int)view.getTag();
        else
            IdNoteEdit = -1;

        setContentView(R.layout.activity_note);

       if(IdNoteEdit != -1)
       {
           EditText etName = findViewById(R.id.etName);
           MultiAutoCompleteTextView etText = findViewById(R.id.etText);

           etName.setText(AllNotes.get(IdNoteEdit).Name);
           etText.setText(AllNotes.get(IdNoteEdit).Text);
       }
    }
    public void AddNote(View view)
    {
        EditText etName = findViewById(R.id.etName);
        MultiAutoCompleteTextView etText = findViewById(R.id.etText);
        if(IdNoteEdit == -1)
        {
            Notes newNote = new Notes();
            newNote.Name = etName.getText().toString();
            newNote.Text = etText.getText().toString();
            newNote.Date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
            AllNotes.add(newNote);
        }
        else
        {
            AllNotes.get(IdNoteEdit).Name = etName.getText().toString();
            AllNotes.get(IdNoteEdit).Text = etName.getText().toString();
            AllNotes.get(IdNoteEdit).Date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        }
        setContentView(R.layout.activity_main);
         OnLoad();
    }
    public void OnLoad()
    {
        LinearLayout llParent = findViewById(R.id.llParent);
        llParent.removeAllViews();



        for (int iNote = 0; iNote < AllNotes.size(); iNote ++)
        {
            LinearLayout LL = new LinearLayout(this);
            LL.setOrientation(LinearLayout.HORIZONTAL);
            RelativeLayout.LayoutParams Params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            LL.setLayoutParams(Params);
            LL.setTag(iNote);
            LL.setOnClickListener(this::OnAddNote);
            ImageView Logo = new ImageView(this);
            RelativeLayout.LayoutParams LogoPramas = new RelativeLayout.LayoutParams(150,150);
            Logo.setLayoutParams(LogoPramas);
            Logo.setImageResource(R.drawable.icon);
            Logo.setPadding(20,20,20,20);
            LinearLayout LL_Vertical = new LinearLayout(this);
            LL_Vertical.setLayoutParams(Params);
            LL_Vertical.setOrientation(LinearLayout.VERTICAL);

            TextView tvName = new TextView(this);
            tvName.setText(AllNotes.get(iNote).Name);

            RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            tvName.setLayoutParams(tvParams);
            tvName.setTextColor(Color.BLACK);
            tvName.setTextSize(18);

            TextView tvDate = new TextView(this);
            tvDate.setText(AllNotes.get(iNote).Name);
            tvDate.setLayoutParams(tvParams);
            tvDate.setTextColor(Color.GRAY);

            llParent.addView(LL);
            LL.addView(Logo);
            LL.addView(LL_Vertical);
            LL_Vertical.addView(tvName);
            LL_Vertical.addView(tvDate);
        }
    }
}