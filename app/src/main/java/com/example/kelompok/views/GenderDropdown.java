package com.example.kelompok.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.kelompok.R;

import java.util.ArrayList;

public class GenderDropdown extends AppCompatTextView implements View.OnClickListener {

    private ArrayList<String> options = new ArrayList<>();
    private String selectedGender = "";

    public GenderDropdown(Context context) {
        super(context);
        initView();
    }

    public GenderDropdown(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        this.setOnClickListener(this);
    }

    private PopupWindow popupWindowSort(Context context) {
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setWidth(this.getWidth());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, options);
        ListView listViewSort = new ListView(context);

        listViewSort.post(new Runnable() {
            @Override
            public void run() {
                listViewSort.setSelected(true);
                listViewSort.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });
        listViewSort.setAdapter(adapter);

        listViewSort.setOnItemClickListener((parent, view, position, id) -> {
            this.setText(options.get(position));
            this.selectedGender = options.get(position);
            popupWindow.dismiss();
        });

        popupWindow.setFocusable(true);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(listViewSort);

        return popupWindow;
    }

    @Override
    public void onClick(View v) {
        if(v == this) {
            PopupWindow window = popupWindowSort(v.getContext());
            window.showAsDropDown(v, 0, 0);
        }
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getSelectedGender() {
        return this.selectedGender;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }
}
