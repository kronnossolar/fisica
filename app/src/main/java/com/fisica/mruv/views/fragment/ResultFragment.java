package com.fisica.mruv.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.fisica.mruv.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "distancia";
    private static final String ARG_PARAM2 = "velocidad";
    private static final String ARG_PARAM3 = "tiempo";
    private static final String ARG_PARAM4 = "tipo";
    private static final String ARG_PARAM5 = "vInicial";
    private static final String ARG_PARAM6 = "vFinal";
    private static final String ARG_PARAM7 = "aceleracion";

    // TODO: Rename and change types of parameters
    private float mDistancia = 0;
    private float mVelocidad = 0;
    private float mTiempo = 0;
    private int mTipo = 0;
    private float mVelocidadFinal = 0;
    private float mVelocidadInicial = 0;
    private float mAceleracion = 0;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param distancia Parameter 1.
     * @param velocidad Parameter 2.
     * @param tiempo Parameter 2.
     *
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(float distancia, float velocidad, float tiempo, int tipo) {
        return newInstance(distancia, velocidad, tiempo, 0,0,0,tipo);
    }

    public static ResultFragment newInstance(float distancia, float velocidad, float tiempo, float aceleracion, float vIni, float vFin, int tipo) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putFloat(ARG_PARAM1, distancia);
        args.putFloat(ARG_PARAM2, velocidad);
        args.putFloat(ARG_PARAM3, tiempo);
        args.putInt(ARG_PARAM4, tipo);
        args.putFloat(ARG_PARAM5, vIni);
        args.putFloat(ARG_PARAM6, vFin);
        args.putFloat(ARG_PARAM7, aceleracion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDistancia = getArguments().getFloat(ARG_PARAM1);
            mVelocidad = getArguments().getFloat(ARG_PARAM2);
            mTiempo= getArguments().getFloat(ARG_PARAM3);
            mTipo = getArguments().getInt(ARG_PARAM4);
            mVelocidadInicial = getArguments().getFloat(ARG_PARAM5);
            mVelocidadFinal = getArguments().getFloat(ARG_PARAM6);
            mAceleracion = getArguments().getFloat(ARG_PARAM7);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_result, container, false);
        loadViews(v);
        return v;
    }

    private void loadViews(View v) {
        TextView tvVelocidadInicial = v.findViewById(R.id.tvVelocidadInicial);
        TextView tvVelocidadFinal = v.findViewById(R.id.tvVelocidadFinal);
        TextView tvDistancia = v.findViewById(R.id.tvDistancia);
        TextView tvVelocidad = v.findViewById(R.id.tvVelocidad);
        TextView tvTiempo = v.findViewById(R.id.tvTiempo);
        TextView tvAceleracion = v.findViewById(R.id.tvAceleracion);
        LinearLayout lLayoutVInicial, lLayoutVFinal, lLayoutVelocidad, lLayoutAceleracion;
        lLayoutAceleracion = v.findViewById(R.id.lLayoutAceleracion);
        lLayoutVInicial = v.findViewById(R.id.lLayoutVInicial);
        lLayoutVFinal = v.findViewById(R.id.lLayoutVFinal);
        lLayoutVelocidad = v.findViewById(R.id.lLayoutVelocidad);
        if(mTipo == 1){
            lLayoutAceleracion.setVisibility(View.GONE);
            lLayoutVFinal.setVisibility(View.GONE);
            lLayoutVInicial.setVisibility(View.GONE);
            tvVelocidad.setText(String.valueOf(mVelocidad));
        } else {
            lLayoutVelocidad.setVisibility(View.GONE);
            tvVelocidadFinal.setText(String.valueOf(mVelocidadFinal));
            tvVelocidadInicial.setText(String.valueOf(mVelocidadInicial));
            tvAceleracion.setText(String.valueOf(mAceleracion));
        }
        tvDistancia.setText(String.valueOf(mDistancia));
        tvTiempo.setText(String.valueOf(mTiempo));
    }
}
