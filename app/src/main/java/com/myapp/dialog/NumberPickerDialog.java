package com.myapp.dialog;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myapp.R;
import com.shawnlin.numberpicker.NumberPicker;

public class NumberPickerDialog extends BottomSheetDialogFragment {
    NumberPicker numberPicker;

    public interface Listener {
        void sendDialogResult(int selectNumber);
    }

    View convertView = null;

    private NumberPickerDialog.Listener listener;
    private String title;

    public NumberPickerDialog(String title) {
        this.title = title;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.dialog_number_picker, null);
        numberPicker = (NumberPicker) convertView.findViewById(R.id.number_picker);

// Set divider color
        numberPicker.setDividerColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        numberPicker.setDividerColorResource(R.color.colorPrimary);

// Set formatter
        numberPicker.setFormatter(getString(R.string.number_picker_formatter));
        numberPicker.setFormatter(R.string.number_picker_formatter);

// Set selected text color
        numberPicker.setSelectedTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        numberPicker.setSelectedTextColorResource(R.color.colorPrimary);

// Set selected text size
        numberPicker.setSelectedTextSize(getResources().getDimension(R.dimen.selected_text_size));
        numberPicker.setSelectedTextSize(R.dimen.selected_text_size);

// Set selected typeface
        numberPicker.setSelectedTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        numberPicker.setSelectedTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        numberPicker.setSelectedTypeface(getString(R.string.roboto_light));
        numberPicker.setSelectedTypeface(R.string.roboto_light, Typeface.NORMAL);
        numberPicker.setSelectedTypeface(R.string.roboto_light);

// Set text color
        numberPicker.setTextColor(ContextCompat.getColor(getContext(), R.color.dark_grey));
        numberPicker.setTextColorResource(R.color.dark_grey);

// Set text size
        numberPicker.setTextSize(getResources().getDimension(R.dimen.text_size));
        numberPicker.setTextSize(R.dimen.text_size);

// Set typeface
        numberPicker.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        numberPicker.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        numberPicker.setTypeface(getString(R.string.roboto_light));
        numberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);
        numberPicker.setTypeface(R.string.roboto_light);

// Set value
        numberPicker.setMaxValue(59);
        numberPicker.setMinValue(0);
        numberPicker.setValue(3);

// Using string values
// IMPORTANT! setMinValue to 1 and call setDisplayedValues after setMinValue and setMaxValue
        String[] data = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(data.length);
        numberPicker.setDisplayedValues(data);
        numberPicker.setValue(7);

// Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);

// Set scroller enabled
        numberPicker.setScrollerEnabled(true);

// Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);

// Set accessibility description enabled
        numberPicker.setAccessibilityDescriptionEnabled(true);

// OnClickListener
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "Click on current value");
            }
        });

// OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                /// Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
            }
        });

// OnScrollListener
        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    // Log.d(TAG, String.format(Locale.US, "newVal: %d", picker.getValue()));
                }
            }
        });
        return convertView;
    }
}