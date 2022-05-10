package com.myapp.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.myapp.R;
import com.myapp.dictionary.fragment.EnWordDetailFragment;
import com.myapp.dictionary.fragment.YourNoteFragment;
import com.myapp.dtbassethelper.DatabaseAccess;
import com.myapp.learnenglish.fragment.home.HomeFragment;
import com.myapp.model.EnWord;

public class EnWordDetailActivity2 extends AppCompatActivity {
    public int enWordId;
    EnWord savedWord;
    BottomNavigationView topNavigation;
    FragmentManager fragmentManager;
    public ImageButton btnSave_UnsaveWord;
    public ImageButton btnBackToSavedWord;

    TextView textViewTitle;
    boolean unsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_word_detail2);
        setControl();
        setEvent();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new EnWordDetailFragment(savedWord)).commit();

    }

    private void setEvent() {
        textViewTitle.setText(savedWord.getWord().trim());
        btnBackToSavedWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),YourWordActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        btnSave_UnsaveWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unsave == true) {
                    //---run unsave code
                    btnSave_UnsaveWord.setBackgroundResource(R.drawable.icons8_bookmark_outline_32px);
                    unsave = !unsave;
                } else {
                    //---run save code
                    btnSave_UnsaveWord.setBackgroundResource(R.drawable.icons8_filled_bookmark_ribbon_32px_1);
                    unsave = !unsave;
                }
            }
        });
//        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//              @Override
//              public void onBackStackChanged() {
//                  Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
//                  if (currentFragment instanceof MainFragment) {
//                      //place your filtering logic here using currentFragment
//                  }
//              }
//          });
                topNavigation.setOnItemSelectedListener(item -> {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.pageEnWordDetail:

                            Bundle bundle = new Bundle();
                            bundle.putInt("enWordId", enWordId);

                            bundle.putSerializable("enWord", savedWord);
                            selectedFragment = new EnWordDetailFragment(savedWord);
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.pageYourNote:
                            selectedFragment = new YourNoteFragment();
                            break;
                    }

                    fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                            selectedFragment, null).addToBackStack(null).commit();
                    return true;
                });
    }

    private void setControl() {
        topNavigation = findViewById(R.id.topNavigation);
        fragmentManager = getSupportFragmentManager();
        enWordId = getIntent().getIntExtra("enWordId",-1);
        DatabaseAccess databaseAccess= DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        savedWord = databaseAccess.getOneEnWord(enWordId);
        databaseAccess.close();

        textViewTitle = findViewById(R.id.textViewTitle);
        btnSave_UnsaveWord = findViewById(R.id.btnSave_UnsaveWord);
        btnBackToSavedWord = findViewById(R.id.imgBtnBackToSavedWord);
        unsave = true;
    }
}