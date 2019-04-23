package br.com.juliana.loureiro.projetofinalahp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.juliana.loureiro.projetofinalahp.Bean.ComparaCriterioBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.CriterioBean;
import br.com.juliana.loureiro.projetofinalahp.Database.ConfigDB;

public class ComparaCriterioDao {

    private Context context;
    private Cursor cursor;
    private SQLiteDatabase db;
    private ConfigDB banco;

    public ComparaCriterioDao(Context context) {
        this.context = context;
        banco = new ConfigDB(context);
        db = banco.getReadableDatabase();
    }

    public boolean insereComparacoes(ComparaCriterioBean comparaCriterioBean) {
        try {

            cursor = db.rawQuery("SELECT * FROM " + ComparaCriterioBean.TABELA_temp + " WHERE "
                    + ComparaCriterioBean.IDCRIT1 + " = " + comparaCriterioBean.getIdcrit1() +
                    " AND " + ComparaCriterioBean.IDCRIT2 + " = " + comparaCriterioBean.getIdcrit2(), null);

            if (cursor.getCount() <= 0) {
                ContentValues valores;

                db = banco.getWritableDatabase();
                valores = new ContentValues();
                valores.put(ComparaCriterioBean.IDCRIT1, comparaCriterioBean.getIdcrit1());
                valores.put(ComparaCriterioBean.IDCRIT2, comparaCriterioBean.getIdcrit2());
                valores.put(ComparaCriterioBean.IMPORTANCIA, comparaCriterioBean.getImportancia());
                db.insert(ComparaCriterioBean.TABELA_temp, null, valores);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }

    public List<ComparaCriterioBean> carregaComparacoes() {
        List<ComparaCriterioBean> lista = new ArrayList<>();

        cursor = db.rawQuery("SELECT * FROM " + ComparaCriterioBean.TABELA_temp, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {

                ComparaCriterioBean comparaCriterioBean = new ComparaCriterioBean();
                comparaCriterioBean.setIdcrit1(cursor.getInt(cursor.getColumnIndex(ComparaCriterioBean.IDCRIT1)));
                comparaCriterioBean.setIdcrit2(cursor.getInt(cursor.getColumnIndex(ComparaCriterioBean.IDCRIT2)));
                comparaCriterioBean.setId(cursor.getInt(cursor.getColumnIndex(ComparaCriterioBean.ID)));
                comparaCriterioBean.setImportancia(cursor.getInt(cursor.getColumnIndex(ComparaCriterioBean.IMPORTANCIA)));

                if(comparaCriterioBean.getIdcrit1()!= comparaCriterioBean.getIdcrit2()) {
                    lista.add(comparaCriterioBean);
                }

            } while (cursor.moveToNext());
        }


        return lista;
    }

}
