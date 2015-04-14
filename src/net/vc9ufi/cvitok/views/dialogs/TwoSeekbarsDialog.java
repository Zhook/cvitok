package net.vc9ufi.cvitok.views.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import net.vc9ufi.cvitok.R;


public abstract class TwoSeekbarsDialog extends TwoIntValuesDialog {

    private SeekBar seekBar1;
    private SeekBar seekBar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(title);

        View view = inflater.inflate(R.layout.dialog_2seekbars, container, false);

        TextView textViewMin1 = (TextView)view.findViewById(R.id.dialog_2seekBar_textView_min1);
        textViewMin1.setText(String.valueOf(min1));
        TextView textViewMin2 = (TextView)view.findViewById(R.id.dialog_2seekBar_textView_min2);
        textViewMin2.setText(String.valueOf(min2));
        TextView textViewMax1 = (TextView)view.findViewById(R.id.dialog_2seekBar_textView_max1);
        textViewMax1.setText(String.valueOf(max1));
        TextView textViewMax2 = (TextView)view.findViewById(R.id.dialog_2seekBar_textView_max2);
        textViewMax2.setText(String.valueOf(max2));
        final TextView textViewProgres1 = (TextView)view.findViewById(R.id.dialog_2seekBar_textView_progres1);
        textViewProgres1.setText(String.valueOf(value1));
        final TextView textViewProgres2 = (TextView)view.findViewById(R.id.dialog_2seekBar_textView_progres2);
        textViewProgres2.setText(String.valueOf(value2));

        seekBar1 = (SeekBar) view.findViewById(R.id.dialog_2seekBar_value1);
        seekBar1.setMax(max1);
        seekBar1.setProgress(value1);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewProgres1.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBar2 = (SeekBar) view.findViewById(R.id.dialog_2seekBar_value2);
        seekBar2.setMax(max2);
        seekBar2.setProgress(value2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewProgres2.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        TextView textViewDes1 = (TextView) view.findViewById(R.id.dialog_2value_des1);
        textViewDes1.setText(des1);
        TextView textViewDes2 = (TextView) view.findViewById(R.id.dialog_2value_des2);
        textViewDes2.setText(des2);

        Button buttonOk = (Button) view.findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOk(seekBar1.getProgress(), seekBar2.getProgress());
                dismiss();
            }
        });

        Button buttonCancel = (Button) view.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    public abstract void onClickOk(int value1, int value2);
}
