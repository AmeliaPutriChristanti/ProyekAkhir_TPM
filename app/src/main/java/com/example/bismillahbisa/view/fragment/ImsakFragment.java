package com.example.bismillahbisa.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bismillahbisa.R;
import com.example.bismillahbisa.adapter.MainAdapterCatatan;
import com.example.bismillahbisa.entity.AppDatabase;
import com.example.bismillahbisa.entity.DataIbadah;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImsakFragment extends Fragment implements MainContactCatatan.view{
    private AppDatabase appDatabase;
    private MainPresenterCatatan mainPresenterCatatan;
    private MainAdapterCatatan mainAdapterCatatan;
    public static Context context;

    private Button submit;
    private RecyclerView recyclerView;
    private EditText etDate, etFardhu, etSunah, etPuasa;

    private int id = 0;
    boolean edit = false;
    public ImsakFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imsak, container, false);
        submit = view.findViewById(R.id.submit);
        etDate = view.findViewById(R.id.etDate);
        etFardhu = view.findViewById(R.id.etFardhu);
        etSunah = view.findViewById(R.id.etSunah);
        etPuasa = view.findViewById(R.id.etPuasa);
        recyclerView = view.findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mainPresenterCatatan = new MainPresenterCatatan(this);
        mainPresenterCatatan.readData(appDatabase);
        submit.setOnClickListener(this);
        return view;
    }

    @Override
    public void successAdd() {
        Toast.makeText(getActivity(), "Berhasil", Toast.LENGTH_SHORT).show();
        mainPresenterCatatan.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(getActivity(), "Berhasil Menghapus Data", Toast.LENGTH_SHORT).show();
        mainPresenterCatatan.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etDate.setText("");
        etFardhu.setText("");
        etSunah.setText("");
        etPuasa.setText("");
        submit.setText("Submit");
    }

    public void getData(List<DataIbadah> list) {
        mainAdapterCatatan = new MainAdapterCatatan(getActivity().getApplicationContext(), list, this);
        recyclerView.setAdapter(mainAdapterCatatan);
    }

    public void editData(DataIbadah item) {
        etDate.setText(item.getDate());
        etFardhu.setText(item.getFardhu());
        etSunah.setText(item.getSunah());
        etPuasa.setText(item.getPuasa());
        id = item.getId();
        edit = true;
        submit.setText("Edit Data");
    }

    @Override
    public void deleteData(final DataIbadah item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenterCatatan.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view==submit) {
            if (etDate.getText().toString().equals("") || etFardhu.getText().toString().equals("") ||
                    etSunah.getText().toString().equals("") || etPuasa.getText().toString().equals("")) {
                Toast.makeText(getActivity().getApplicationContext(), "Harap isi Semua data", Toast.LENGTH_SHORT).show();
            } else {
                if (!edit) {
                    mainPresenterCatatan.insertData(etDate.getText().toString(), etFardhu.getText().toString(), etSunah.getText().toString(),
                            etPuasa.getText().toString(), appDatabase);
                } else {
                    mainPresenterCatatan.editData(etDate.getText().toString(), etFardhu.getText().toString(), etSunah.getText().toString(),
                            etPuasa.getText().toString(), id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}
