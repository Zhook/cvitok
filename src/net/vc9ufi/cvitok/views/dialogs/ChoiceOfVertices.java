package net.vc9ufi.cvitok.views.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import net.vc9ufi.cvitok.App;
import net.vc9ufi.cvitok.R;
import net.vc9ufi.cvitok.data.Flower;
import net.vc9ufi.cvitok.data.SelectedVertices;


public class ChoiceOfVertices {
    Context context;
    App app;
    final SelectedVertices left;
    final SelectedVertices right;

    CheckBox cb_tip;

    CheckBox cb_leftUpSide;
    Button b_upSide;
    CheckBox cb_rightUpSide;

    CheckBox cb_leftDownSide;
    Button b_downSide;
    CheckBox cb_rightDownSide;

    CheckBox cb_leftFoot;
    Button b_foot;
    CheckBox cb_rightFoot;

    public ChoiceOfVertices(Context context) {
        this.context = context;
        this.app = (App) context.getApplicationContext();
        Flower flower = app.getFlower();
        left = flower.getLeft();
        right = flower.getRight();
    }

    public void show() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(R.string.dialog_vertices_title);

        View view = View.inflate(context, R.layout.dialog_choice_of_vertices, null);

        buttonsInit(view);

        dialog.setView(view);
        dialog.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                app.getFlower().setSelectedVertices(left, right);
            }
        });
        dialog.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void buttonsInit(View view) {
        cb_tip = (CheckBox) view.findViewById(R.id.cofv_checkbox_tip);
        cb_tip.setChecked(left.finish);
        cb_tip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                left.finish = isChecked;
                right.finish = isChecked;
            }
        });


        cb_leftUpSide = (CheckBox) view.findViewById(R.id.cofv_checkbox_left_upside);
        cb_leftUpSide.setChecked(left.p2);
        cb_leftUpSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                left.p2 = isChecked;
            }
        });

        b_upSide = (Button) view.findViewById(R.id.cofv_button_upside);
        b_upSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (left.p2 && right.p2) {
                    cb_leftUpSide.setChecked(false);
                    cb_rightUpSide.setChecked(false);
                } else {
                    cb_leftUpSide.setChecked(true);
                    cb_rightUpSide.setChecked(true);
                }
            }
        });

        cb_rightUpSide = (CheckBox) view.findViewById(R.id.cofv_checkbox_right_upside);
        cb_rightUpSide.setChecked(right.p2);
        cb_rightUpSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                right.p2 = isChecked;
            }
        });


        cb_leftDownSide = (CheckBox) view.findViewById(R.id.cofv_checkbox_left_downside);
        cb_leftDownSide.setChecked(left.p1);
        cb_leftDownSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                left.p1 = isChecked;
            }
        });

        b_downSide = (Button) view.findViewById(R.id.cofv_button_downside);
        b_downSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (left.p1 && right.p1) {
                    cb_leftDownSide.setChecked(false);
                    cb_rightDownSide.setChecked(false);
                } else {
                    cb_leftDownSide.setChecked(true);
                    cb_rightDownSide.setChecked(true);
                }
            }
        });

        cb_rightDownSide = (CheckBox) view.findViewById(R.id.cofv_checkbox_right_downside);
        cb_rightDownSide.setChecked(right.p1);
        cb_rightDownSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                right.p1 = isChecked;
            }
        });


        cb_leftFoot = (CheckBox) view.findViewById(R.id.cofv_checkbox_left_foot);
        cb_leftFoot.setChecked(left.start);
        cb_leftFoot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                left.start = isChecked;
            }
        });

        b_foot = (Button) view.findViewById(R.id.cofv_button_foot);
        b_foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (left.start && right.start) {
                    cb_leftFoot.setChecked(false);
                    cb_rightFoot.setChecked(false);
                } else {
                    cb_leftFoot.setChecked(true);
                    cb_rightFoot.setChecked(true);
                }
            }
        });
        cb_rightFoot = (CheckBox) view.findViewById(R.id.cofv_checkbox_right_foot);
        cb_rightFoot.setChecked(right.start);
        cb_rightFoot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                right.start = isChecked;
            }
        });
    }
}
