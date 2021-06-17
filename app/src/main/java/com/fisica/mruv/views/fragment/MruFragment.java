package com.fisica.mruv.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.fisica.mruv.R;
import com.fisica.mruv.models.Unidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MruFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MruFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MruFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private EditText etDistancia;
    private EditText etTiempo;
    private EditText etVelocidad;
    private Spinner spDistancia;
    private Spinner spVelocidad;
    private Spinner spTiempo;
    public MruFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MruFragment.
     */
    public static MruFragment newInstance() {
        return new MruFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mru, container, false);
        loadViews(v);
        return v;
    }

    private void loadViews(View v) {
        Button btnCalcular;
        btnCalcular = v.findViewById(R.id.btnCalcular);
        etDistancia = v.findViewById(R.id.etDistancia);
        etTiempo = v.findViewById(R.id.etTiempo);
        etVelocidad = v.findViewById(R.id.etVelocidad);
        spDistancia = v.findViewById(R.id.spUnidadDistancia);
        spTiempo = v.findViewById(R.id.spUnidadTiempo);
        spVelocidad = v.findViewById(R.id.spUnidadVelocidad);
        btnCalcular.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCalcular){
            String distancia, tiempo, velocidad;
            distancia = etDistancia.getText().toString();
            tiempo = etTiempo.getText().toString();
            velocidad = etVelocidad.getText().toString();

            mListener.calcular(distancia, velocidad, tiempo);

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        //void convertirUnidades(int tipo, Unidades de, Unidades result);
        void calcular(String distancia, String velocidad, String tiempo);
    }
}
