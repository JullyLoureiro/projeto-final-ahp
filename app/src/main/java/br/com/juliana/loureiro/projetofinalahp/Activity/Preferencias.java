package br.com.juliana.loureiro.projetofinalahp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.juliana.loureiro.projetofinalahp.Bean.AlternativaBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.ComparaAlternativaBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.ComparaCriterioBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.ComparaSubCriterioBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.CriterioBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.MatrizCriterioNormalizadaBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.ObjetivoBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.PesoCriteriosBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.SubcriterioBean;
import br.com.juliana.loureiro.projetofinalahp.Dao.AlternativaDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.ComparaAlternativaDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.ComparaCriterioDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.ComparaSubcriterioDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.CriterioDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.MatrizCriterioNormalizadaDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.ObjetivoDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.PesoCriteriosDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.SomaColunaDao;
import br.com.juliana.loureiro.projetofinalahp.Dao.SubcriteriosDao;
import br.com.juliana.loureiro.projetofinalahp.R;
import br.com.juliana.loureiro.projetofinalahp.Util.OnSwip;
import br.com.juliana.loureiro.projetofinalahp.Util.Utils;

public class Preferencias extends AppCompatActivity {
    private Button criterio1, criterio2, alternativa1, alternativa2, subcriterio1, subcriterio2;
    private SeekBar seekBar, seekBar2, seekBar3;
    private RelativeLayout rltpreferencia, rltpreferencia2, rltpreferencia3;
    private ImageView anterior, proximo, anterior2, proximo2, anterior3, proximo3;
    private List<ComparaCriterioBean> listaComp;
    private List<ComparaAlternativaBean> listaCompAlt;
    private List<ComparaSubCriterioBean> listaCompSub;
    private TextView txvtitulo2, txvtitulo3;
    int i = 0;
    int critImportancia = 1, altimportancia = 1, subImportancia = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        declaraObjetos();
    }

    @Override
    public void onBackPressed() {
        Utils.alerta(this, "Tem certeza que deseja voltar?");
    }

    private void declaraObjetos() {
        criterio1 = findViewById(R.id.criterio1);
        criterio2 = findViewById(R.id.criterio2);
        alternativa1 = findViewById(R.id.alternativa1);
        alternativa2 = findViewById(R.id.alternativa2);
        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);
        seekBar3 = findViewById(R.id.seekBar3);

        rltpreferencia = findViewById(R.id.rltpreferencia);
        rltpreferencia2 = findViewById(R.id.rltpreferencia2);
        rltpreferencia3 = findViewById(R.id.rltpreferencia3);

        txvtitulo2 = findViewById(R.id.txvtitulo2);
        txvtitulo3 = findViewById(R.id.txvtitulo3);

        anterior = findViewById(R.id.anterior);
        anterior2 = findViewById(R.id.anterior2);
        anterior3 = findViewById(R.id.anterior3);

        proximo = findViewById(R.id.proximo);
        proximo2 = findViewById(R.id.proximo2);
        proximo3 = findViewById(R.id.proximo3);

        subcriterio1 = findViewById(R.id.subcriterio1);
        subcriterio2 = findViewById(R.id.subcriterio2);

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    criterio1.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit1()));
                    criterio2.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit2()));

                    int imp = new ComparaCriterioDao(Preferencias.this).retornaImportancia(listaComp.get(i).getIdcrit1(), listaComp.get(i).getIdcrit2());
                    seekBar.setProgress(imp);
                }
            }
        });


        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((i + 1) < listaCompAlt.size()) {
                    i++;
                    if (i < listaComp.size()) {
                        criterio1.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit1()));
                        criterio2.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit2()));

                        int imp = new ComparaCriterioDao(Preferencias.this).retornaImportancia(listaComp.get(i).getIdcrit1(), listaComp.get(i).getIdcrit2());
                        seekBar.setProgress(imp);
                    }
                }
            }
        });

        anterior2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    alternativa1.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa1()));
                    alternativa2.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa2()));

                    int imp = new ComparaAlternativaDao(Preferencias.this).retornaImportancia(listaCompAlt.get(i).getIdalternativa1(), listaCompAlt.get(i).getIdalternativa2(), listaCompAlt.get(i).getIdcriterio());
                    seekBar2.setProgress(imp);

                    txvtitulo2.setText("Entre a alternativa " + alternativa1.getText().toString() + " e a alternativa " + alternativa2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdcriterio()) + "?");
                }
            }
        });


        proximo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((i + 1) < listaCompAlt.size()) {
                    i++;
                    alternativa1.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa1()));
                    alternativa2.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa2()));
                    int imp = new ComparaAlternativaDao(Preferencias.this).retornaImportancia(listaCompAlt.get(i).getIdalternativa1(), listaCompAlt.get(i).getIdalternativa2(), listaCompAlt.get(i).getIdcriterio());
                    seekBar2.setProgress(imp);

                    txvtitulo2.setText("Entre a alternativa " + alternativa1.getText().toString() + " e a alternativa " + alternativa2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdcriterio()) + "?");
                }
            }
        });


        anterior3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    subcriterio1.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit1()));
                    subcriterio2.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit2()));

                    int imp = new ComparaSubcriterioDao(Preferencias.this).retornaImportancia(listaCompSub.get(i).getIdsubcrit1(), listaCompSub.get(i).getIdsubcrit2(), listaCompSub.get(i).getIdcriterio());
                    seekBar3.setProgress(imp);

                    txvtitulo3.setText("Entre " + subcriterio1.getText().toString() + " e " + subcriterio2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdcriterio()) + "?");
                }
            }
        });


        proximo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((i + 1) < listaCompSub.size()) {
                    i++;
                    subcriterio1.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit1()));
                    subcriterio2.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit2()));
                    int imp = new ComparaAlternativaDao(Preferencias.this).retornaImportancia(listaCompSub.get(i).getIdsubcrit1(), listaCompSub.get(i).getIdsubcrit2(), listaCompSub.get(i).getIdcriterio());
                    seekBar2.setProgress(imp);

                    txvtitulo2.setText("Entre " + subcriterio1.getText().toString() + " e " + subcriterio2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdcriterio()) + "?");
                }
            }
        });


        rltpreferencia.setOnTouchListener(new OnSwip(this) {
            public void onSwipeTop() {

            }

            public void onSwipeLeft() {
                if ((i + 1) < listaCompSub.size()) {
                    i++;
                    criterio1.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit1()));
                    criterio2.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit2()));
                    int imp = new ComparaCriterioDao(Preferencias.this).retornaImportancia(listaComp.get(i).getIdcrit1(), listaComp.get(i).getIdcrit2());
                    seekBar.setProgress(imp);
                }
            }

            public void onSwipeRight() {
                if (i > 0) {
                    i--;
                    criterio1.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit1()));
                    criterio2.setText(new CriterioDao(Preferencias.this).retornaDescricao(listaComp.get(i).getIdcrit2()));
                    int imp = new ComparaCriterioDao(Preferencias.this).retornaImportancia(listaComp.get(i).getIdcrit1(), listaComp.get(i).getIdcrit2());
                    seekBar.setProgress(imp);
                }
            }

            public void onSwipeBottom() {

            }

        });

        rltpreferencia2.setOnTouchListener(new OnSwip(this) {
            public void onSwipeTop() {

            }

            public void onSwipeLeft() {
                if ((i + 1) < listaCompAlt.size()) {
                    i++;
                    alternativa1.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa1()));
                    alternativa2.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa2()));

                    int imp = new ComparaAlternativaDao(Preferencias.this).retornaImportancia(listaCompAlt.get(i).getIdalternativa1(), listaCompAlt.get(i).getIdalternativa2(), listaCompAlt.get(i).getIdcriterio());
                    seekBar2.setProgress(imp);

                    txvtitulo2.setText("Entre a alternativa " + alternativa1.getText().toString() + " e a alternativa " + alternativa2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdcriterio()) + "?");
                }
            }

            public void onSwipeRight() {
                if (i > 0) {
                    i--;
                    alternativa1.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa1()));
                    alternativa2.setText(new AlternativaDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdalternativa2()));

                    int imp = new ComparaAlternativaDao(Preferencias.this).retornaImportancia(listaCompAlt.get(i).getIdalternativa1(), listaCompAlt.get(i).getIdalternativa2(), listaCompAlt.get(i).getIdcriterio());
                    seekBar2.setProgress(imp);

                    txvtitulo2.setText("Entre a alternativa " + alternativa1.getText().toString() + " e a alternativa " + alternativa2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompAlt.get(i).getIdcriterio()) + "?");
                }
            }

            public void onSwipeBottom() {

            }

        });

        rltpreferencia3.setOnTouchListener(new OnSwip(this) {
            public void onSwipeTop() {

            }

            public void onSwipeLeft() {
                if ((i + 1) < listaCompSub.size()) {
                    i++;
                    subcriterio1.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit1()));
                    subcriterio2.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit2()));
                    int imp = new ComparaAlternativaDao(Preferencias.this).retornaImportancia(listaCompSub.get(i).getIdsubcrit1(), listaCompSub.get(i).getIdsubcrit2(), listaCompSub.get(i).getIdcriterio());
                    seekBar2.setProgress(imp);

                    txvtitulo2.setText("Entre " + subcriterio1.getText().toString() + " e " + subcriterio2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdcriterio()) + "?");
                }
            }

            public void onSwipeRight() {
                if (i > 0) {
                    i--;
                    subcriterio1.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit1()));
                    subcriterio2.setText(new SubcriteriosDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdsubcrit2()));

                    int imp = new ComparaSubcriterioDao(Preferencias.this).retornaImportancia(listaCompSub.get(i).getIdsubcrit1(), listaCompSub.get(i).getIdsubcrit2(), listaCompSub.get(i).getIdcriterio());
                    seekBar3.setProgress(imp);

                    txvtitulo3.setText("Entre " + subcriterio1.getText().toString() + " e " + subcriterio2.getText().toString() +
                            ", qual possui maior relevância em relação ao critério " +
                            new CriterioDao(Preferencias.this).retornaDescricao(listaCompSub.get(i).getIdcriterio()) + "?");
                }
            }

            public void onSwipeBottom() {

            }

        });

        carregaDados();
    }

    private void carregaDados() {
        listaComp = new ComparaCriterioDao(this).carregaComparacoes();
        listaCompAlt = new ComparaAlternativaDao(this).carregaComparacoes();

        final String crit1 = new CriterioDao(this).retornaDescricao(listaComp.get(i).getIdcrit1());
        criterio1.setText(crit1);
        String crit2 = new CriterioDao(this).retornaDescricao(listaComp.get(i).getIdcrit2());
        criterio2.setText(crit2);

        final String alt1 = new AlternativaDao(this).retornaDescricao(listaCompAlt.get(i).getIdalternativa1());
        String alt2 = new AlternativaDao(this).retornaDescricao(listaCompAlt.get(i).getIdalternativa2());
        alternativa1.setText(alt1);
        alternativa2.setText(alt2);

        txvtitulo2.setText("Entre a alternativa " + alt1 + " e a alternativa " + alt2 +
                ", qual possui maior relevância em relação ao critério " +
                new CriterioDao(this).retornaDescricao(listaCompAlt.get(i).getIdcriterio()) + "?");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int importancia = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                switch (progresValue) {
                    case 0:
                        importancia = 9;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.nine));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 1:
                        importancia = 8;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.eight));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 2:
                        importancia = 7;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.seven));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 3:
                        importancia = 6;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.six));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 4:
                        importancia = 5;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.five));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 5:
                        importancia = 4;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.four));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 6:
                        importancia = 3;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.three));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 7:
                        importancia = 2;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.two));
                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio2.setTextColor(getResources().getColor(R.color.branco));

                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 2;
                        break;
                    case 8:
                        importancia = 1;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.one));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 9:
                        importancia = 2;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.two));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 10:
                        importancia = 3;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.three));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 11:
                        importancia = 4;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.four));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 12:
                        importancia = 5;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.five));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 13:
                        importancia = 6;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.six));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 14:
                        importancia = 7;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.seven));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 15:
                        importancia = 8;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.eight));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                    case 16:
                        importancia = 9;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.nine));
                        criterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        criterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        critImportancia = 1;
                        break;
                }

                listaComp.get(i).setImportancia(importancia);
                //Toast.makeText(Preferencias.this, String.valueOf(importancia), Toast.LENGTH_LONG).show();
                new ComparaCriterioDao(Preferencias.this).atualizaImportancia(listaComp.get(i), critImportancia);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int importancia = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                switch (progresValue) {
                    case 0:
                        importancia = 9;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.nine));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 1:
                        importancia = 8;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.eight));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 2:
                        importancia = 7;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.seven));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 3:
                        importancia = 6;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.six));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 4:
                        importancia = 5;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.five));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 5:
                        importancia = 4;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.four));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 6:
                        importancia = 3;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.three));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 7:
                        importancia = 2;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.two));
                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa2.setTextColor(getResources().getColor(R.color.branco));

                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 2;
                        break;
                    case 8:
                        importancia = 1;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.one));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 9:
                        importancia = 2;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.two));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 10:
                        importancia = 3;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.three));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 11:
                        importancia = 4;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.four));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 12:
                        importancia = 5;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.five));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 13:
                        importancia = 6;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.six));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 14:
                        importancia = 7;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.seven));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 15:
                        importancia = 8;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.eight));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                    case 16:
                        importancia = 9;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.nine));
                        alternativa1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        alternativa1.setTextColor(getResources().getColor(R.color.branco));

                        alternativa2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        alternativa2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        altimportancia = 1;
                        break;
                }

                listaCompAlt.get(i).setImportancia(importancia);
                // Toast.makeText(Preferencias.this, String.valueOf(importancia), Toast.LENGTH_LONG).show();
                new ComparaAlternativaDao(Preferencias.this).atualizaImportancia(listaCompAlt.get(i), altimportancia);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int importancia = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                switch (progresValue) {
                    case 0:
                        importancia = 9;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.nine));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 1:
                        importancia = 8;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.eight));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 2:
                        importancia = 7;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.seven));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 3:
                        importancia = 6;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.six));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 4:
                        importancia = 5;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.five));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 5:
                        importancia = 4;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.four));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 6:
                        importancia = 3;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.three));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 7:
                        importancia = 2;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.two));
                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio2.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio1.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 2;
                        break;
                    case 8:
                        importancia = 1;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.one));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 9:
                        importancia = 2;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.two));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 10:
                        importancia = 3;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.three));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 11:
                        importancia = 4;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.four));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 12:
                        importancia = 5;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.five));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        criterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        criterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 13:
                        importancia = 6;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.six));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 14:
                        importancia = 7;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.seven));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 15:
                        importancia = 8;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.eight));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                    case 16:
                        importancia = 9;
                        seekBar.setThumb(getResources().getDrawable(R.drawable.nine));
                        subcriterio1.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button));
                        subcriterio1.setTextColor(getResources().getColor(R.color.branco));

                        subcriterio2.setBackground(ActivityCompat.getDrawable(Preferencias.this, R.drawable.shape_button_cinza));
                        subcriterio2.setTextColor(getResources().getColor(R.color.cinzaescuro));
                        subImportancia = 1;
                        break;
                }

                listaCompSub.get(i).setImportancia(importancia);
                //Toast.makeText(Preferencias.this, String.valueOf(importancia), Toast.LENGTH_LONG).show();
                new ComparaSubcriterioDao(Preferencias.this).atualizaImportancia(listaCompSub.get(i), critImportancia);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        MenuItem ajuda = menu.findItem(R.id.ajuda);
        MenuItem avancar = menu.findItem(R.id.avancar);
        ajuda.setVisible(false);
        avancar.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.avancar:
                if (rltpreferencia.getVisibility() == View.VISIBLE) {
                    calculaResultados();
                } else if (rltpreferencia3.getVisibility() == View.VISIBLE) {
                    rltpreferencia2.setVisibility(View.VISIBLE);
                    rltpreferencia.setVisibility(View.GONE);
                    rltpreferencia3.setVisibility(View.GONE);
                    altimportancia = 1;
                    critImportancia = 1;
                    subImportancia = 1;
                    i = 0;
                } else {
                    Intent intent = new Intent(Preferencias.this, Resultados.class);
                    startActivity(intent);
                    finish();
                }

                break;
            default:
                onBackPressed();
                break;
        }
        return true;
    }

    private void calculaResultados() {

        int qtd = new CriterioDao(this).retornaQtdCriterios();

        Utils.calculacriteriosTemp(this);

        //CÁLCULO DE INCONSISTÊNCIA
        float YmaxMedia = new PesoCriteriosDao(this).calculaMedia();
        float CI = (YmaxMedia - qtd) / (qtd - 1);

        float CR, RI = 0;
        //TABELA RI
        switch (qtd) {
            case 1:
                RI = 0;
                break;
            case 2:
                RI = 0;
                break;
            case 3:
                RI = (float) 0.52;
                break;
            case 4:
                RI = (float) 0.9;
                break;
            case 5:
                RI = (float) 1.12;
                break;
            case 6:
                RI = (float) 1.25;
                break;
            case 7:
                RI = (float) 1.35;
                break;
            case 8:
                RI = (float) 1.42;
                break;
            case 9:
                RI = (float) 1.46;
                break;
            case 10:
                RI = (float) 1.49;
                break;
            case 11:
                RI = (float) 1.52;
                break;
            case 12:
                RI = (float) 1.54;
                break;
            case 13:
                RI = (float) 1.56;
                break;
            case 14:
                RI = (float) 1.58;
                break;
            case 15:
                RI = (float) 1.59;
                break;
            default:
                break;
        }

        CR = CI / RI;
        if (CR > 0.1) {
            String msg = "Cálculo de consistência: " + String.valueOf(CR) + "\nVolte e revise seus julgamentos!";
            alerta(this, msg, CR);
        } else {
            String msg = "Cálculo de consistência: " + String.valueOf(CR);
            alerta(this, msg, CR);
        }


    }

    public void alerta(final Activity activity, String mensag, final float cr) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alertdialog, null);

        TextView mensagem = alertLayout.findViewById(R.id.txtmensagem);
        mensagem.setText(mensag);
        Button yes = alertLayout.findViewById(R.id.yes);
        ImageView close = alertLayout.findViewById(R.id.close);

        if (cr > 0.1) {
            yes.setVisibility(View.GONE);
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        final AlertDialog dialog = alert.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cr > 0.1) {
                    dialog.dismiss();
                } else {
                    dialog.dismiss();

                    if (new SubcriteriosDao(Preferencias.this).carregaCriterios().isEmpty()) {
                        rltpreferencia2.setVisibility(View.VISIBLE);
                    } else {
                        rltpreferencia3.setVisibility(View.VISIBLE);
                    }
                    rltpreferencia.setVisibility(View.GONE);
                    altimportancia = 1;
                    critImportancia = 1;
                    subImportancia = 1;
                    i = 0;
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
