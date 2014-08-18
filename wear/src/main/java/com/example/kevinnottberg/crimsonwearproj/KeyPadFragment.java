package com.example.kevinnottberg.crimsonwearproj;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Kevin Nottberg on 7/8/2014.
 */
public class KeyPadFragment extends Fragment{

    View view;
    String currText;

    public KeyPadFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("FragmentManager", "KeyPadFrag onCreate() hit");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.keypad_fragment, container, false);

        Button button0 = (Button) view.findViewById(R.id.button0);
        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);
        Button button4 = (Button) view.findViewById(R.id.button4);
        Button button5 = (Button) view.findViewById(R.id.button5);
        Button button6 = (Button) view.findViewById(R.id.button6);
        Button button7 = (Button) view.findViewById(R.id.button7);
        Button button8 = (Button) view.findViewById(R.id.button8);
        Button button9 = (Button) view.findViewById(R.id.button9);
        Button buttonBack = (Button) view.findViewById(R.id.buttonBack);
        Button buttonCheck = (Button) view.findViewById(R.id.buttonCheck);
        final EditText editText = (EditText) view.findViewById(R.id.editText);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "1";
                editText.setText(currText);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "2";
                editText.setText(currText);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "3";
                editText.setText(currText);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "4";
                editText.setText(currText);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "5";
                editText.setText(currText);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "6";
                editText.setText(currText);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "7";
                editText.setText(currText);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "8";
                editText.setText(currText);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "9";
                editText.setText(currText);
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currText += "0";
                editText.setText(currText);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currText.equals("")) {
                    // Do Nothing
                } else {
                    currText = currText.substring(0, currText.length() - 1);
                    editText.setText(currText);
                }
            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FragmentManager", currText);
                ((MyActivity)getActivity()).setCurrDot(currText);
            }
        });

        return view;
    }

    public static KeyPadFragment newInstance() {
        Log.d("FragmentManager", "KeyPadFrag newInstance() hit");
        KeyPadFragment keys = new KeyPadFragment();
        Bundle args = new Bundle();
        return keys;
    }
}
