package com.example.fragmentsexample;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/** Creating Fragment
 * 1. create subclass that extends Fragment class
 * 2. create fragment layout XML file (e.g., fragment_1.xml)
 */
public class Fragment1 extends Fragment {
    MyListener listener;
    TextView tv;
    // MainActivity mainActivity;  // reference to the main activity

    /** onCreate() : Called when creating the fragment.
     * Initialize essential components of the fragment that you want to retain
     * when the fragment is paused or stopped, then resumed
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /** onCreateView() : Called when it's time for the fragment to draw its user interface for the first time.
     * Must return a View from this method that is the root of your fragment's layout.
     * You can return null if the fragment does not provide a UI.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Associate the layout for this fragment (R.layout.fragment_1 : the root of fragment's layout)
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        // associate with textView2 with current view and assign it to reference variable tv
        tv = view.findViewById(R.id.fragmentTextView);

        Button sendMsgToActivityBtn = view.findViewById(R.id.sendMsgToActivityBtn);
        EditText fragEt = view.findViewById(R.id.fragEt);
        sendMsgToActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = fragEt.getText().toString();
                listener.sendMessageToActivity(message);
            }
        });
        return view;
    }

    // Triggered when the fragment is been attached to activity
    // (when the fragment is being created and stand to be displayed,
    // the first thing is to attach the fragment to activity, and lifecycle restarts)
    @Override
    public void onAttach(@NonNull Context context) {    // Here, context is the activity where the fragment is attached to
        super.onAttach(context);
        //mainActivity = (MainActivity) context;  // activity를 직접적으로 reference하는 것은 좋은 방법은 아님(Interface를 이용하는 것이 좋음)
        listener = (MyListener) context;    // polymorphism
    }
}