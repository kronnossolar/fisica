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
import com.fisica.mruv.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MruvFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MruvFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MruvFragment extends Fragment implements View.OnClickListener{
    private OnFragmentInteractionListener mListener;

    private EditText etVelocidadI;
    private EditText etVelocidadF;
    private EditText etAceleracion;
    private EditText etDistancia;
    private EditText etTiempo;

    public MruvFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MruvFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MruvFragment newInstance() {
        return new MruvFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mruv, container, false);
        loadViews(v);
        return v;
    }

    private void loadViews(View v) {
        etAceleracion = v.findViewById(R.id.etAceleracion);
        etVelocidadI = v.findViewById(R.id.etVelocidadI);
        etVelocidadF = v.findViewById(R.id.etVelocidadF);
        etDistancia = v.findViewById(R.id.etDistancia);
        etTiempo = v.findViewById(R.id.etTiempo);
        Button btnCalcular = v.findViewById(R.id.btnCalcular);
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
            mListener.calcularMruv(etDistancia.getText().toString(), etVelocidadI.getText().toString(),
                    etVelocidadF.getText().toString(), etTiempo.getText().toString(), etAceleracion.getText().toString());
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
        //void convertirUnidades(int tipo);
        void calcularMruv(String distancia, String velocidadI, String velocidadF, String tiempo, String aceleracion);
    }
}
