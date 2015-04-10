package net.vc9ufi.cvitok.views.fragments;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import net.vc9ufi.cvitok.App;
import net.vc9ufi.cvitok.R;
import net.vc9ufi.cvitok.views.MainActivity;
import net.vc9ufi.cvitok.views.dialogs.NameDialog;

import java.util.List;

public class FragmentPetals extends Fragment {
    App app;
    Context context;
    MainActivity mainActivity;

    Spinner sp_petals;

    public void setAppNMainActivity(App app, MainActivity mainActivity) {
        this.app = app;
        this.mainActivity = mainActivity;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petals, container, false);
        context = inflater.getContext();
        app = (App) context.getApplicationContext();

        sp_petals = (Spinner) view.findViewById(R.id.fragment_petals_spinner_petals);
        sp_petals.setPrompt(getString(R.string.petal));
        sp_petals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                app.getFlower().setSelectedPetals(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fillSpinner();

        ImageButton b_add = (ImageButton) view.findViewById(R.id.fragment_petals_imageButton_addPetal);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameDialog();
            }
        });

        ImageButton b_del = (ImageButton) view.findViewById(R.id.fragment_petals_imageButton_delPetal);
        b_del.setOnClickListener(onDeleteClickListener);

        ImageButton b_vertices = (ImageButton) view.findViewById(R.id.fragment_petals_button_vertices);
        b_vertices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.addVerticesFragment();
            }
        });

        return view;
    }

    void fillSpinner() {
        List<String> names = app.getFlower().getPetalsNames();
        if (names != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, names);
            sp_petals.setAdapter(adapter);
        }

        int index = app.getFlower().getSelectedIndex();
        if (index < 0)
            showNameDialog();
        else
            sp_petals.setSelection(index);
    }

    void showNameDialog() {
        final NameDialog nameDialog = new NameDialog(context, getString(R.string.dialog_petal_name_title), getString(R.string.petal)) {
            @Override
            protected boolean onPositiveClick(String name) {
                if (name.length() > 0) {
                    if (app.getFlower().addPetal(name)) {
                        fillSpinner();
                        return true;
                    }
                    this.setMsg(context.getString(R.string.msg_petal_exists));
                    return false;
                }
                this.setMsg(context.getString(R.string.msg_input_name));
                return false;
            }
        };
        nameDialog.show();
    }

    View.OnClickListener onDeleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
            deleteDialog.setTitle(R.string.dialog_delete_petal_title);
            TextView field = new TextView(context);
            field.setText(app.getFlower().getSelectedName());
            deleteDialog.setView(field);
            deleteDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    app.getFlower().delPetal();
                    fillSpinner();
                }
            });
            deleteDialog.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            deleteDialog.show();
        }
    };
}
