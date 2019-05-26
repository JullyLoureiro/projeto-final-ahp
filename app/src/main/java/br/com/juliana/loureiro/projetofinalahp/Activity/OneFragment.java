package br.com.juliana.loureiro.projetofinalahp.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import br.com.juliana.loureiro.projetofinalahp.R;
import br.com.juliana.loureiro.projetofinalahp.Util.Utils;

public class OneFragment extends Fragment {
    private Activity activity;

    public OneFragment() {
        // Required empty public constructor
    }

    public OneFragment(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.tela_objetivo, container, false);
        final EditText edttitulo = v.findViewById(R.id.edttitulo);
        ImageView help = v.findViewById(R.id.help);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.alerta(activity, "Objetivo ou meta que o tomador de decisões quer alcançar;\nTema do problema decisório", "OBJETIVO");
            }
        });

        edttitulo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(edttitulo.getText().length()>0) {
                    try {
                        TelaFuncaoAHP.tituloobj = edttitulo.getText().toString();
                    }catch (Exception ignored) {

                    }
                }
            }
        });

        return v;

    }

}