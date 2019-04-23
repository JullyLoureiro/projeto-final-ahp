package br.com.juliana.loureiro.projetofinalahp.ListAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.juliana.loureiro.projetofinalahp.Activity.TelaFuncaoAHP;
import br.com.juliana.loureiro.projetofinalahp.Bean.AlternativaBean;
import br.com.juliana.loureiro.projetofinalahp.Dao.AlternativaDao;
import br.com.juliana.loureiro.projetofinalahp.R;

public class AlternativasList extends BaseAdapter {
    private final List<AlternativaBean> alternativaBeans;
    private Activity activity;

    public AlternativasList(List<AlternativaBean> alternativaBeans, Activity act) {
        this.alternativaBeans = alternativaBeans;
        this.activity = act;
    }

    @Override
    public int getCount() {
        return alternativaBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return alternativaBeans.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View v = activity.getLayoutInflater()
                .inflate(R.layout.card_criterios, viewGroup, false);

        TextView titulo = v.findViewById(R.id.titulo);
        ImageView editar = v.findViewById(R.id.editar);
        ImageView apagar = v.findViewById(R.id.apagar);

        titulo.setText(alternativaBeans.get(position).getDescricao());

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlternativaDao(activity).deletaAlternativa(alternativaBeans.get(position).getId());
                alternativaBeans.remove(position);
                TelaFuncaoAHP.listCriterios.setAdapter(new AlternativasList(alternativaBeans, activity));
            }
        });

        return v;
    }
}
