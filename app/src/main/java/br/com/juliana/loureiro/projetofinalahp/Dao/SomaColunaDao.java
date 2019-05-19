package br.com.juliana.loureiro.projetofinalahp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.juliana.loureiro.projetofinalahp.Bean.ComparaAlternativaBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.ComparaCriterioBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.ObjetivoBean;
import br.com.juliana.loureiro.projetofinalahp.Bean.SomaColunaBean;
import br.com.juliana.loureiro.projetofinalahp.Database.ConfigDB;

public class SomaColunaDao {
    private Context context;
    private Cursor cursor;
    private SQLiteDatabase db;
    private ConfigDB banco;

    public SomaColunaDao(Context context) {
        this.context = context;
        banco = new ConfigDB(context);
        db = banco.getReadableDatabase();
    }

    public void somaColunas() {
        cursor = db.rawQuery("SELECT IDCRIT2, SUM(IMPORTANCIA) AS SOMA FROM " + ComparaCriterioBean.TABELA_temp +
                " GROUP BY IDCRIT2", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                try {
                    ContentValues valores;

                    db = banco.getWritableDatabase();
                    valores = new ContentValues();
                    valores.put(SomaColunaBean.IDCRIT, cursor.getInt(cursor.getColumnIndex(ComparaCriterioBean.IDCRIT2)));
                    valores.put(SomaColunaBean.SOMA, cursor.getFloat(cursor.getColumnIndex("SOMA")));

                    db.insert(SomaColunaBean.TABELA, null, valores);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }while (cursor.moveToNext());
        }
    }

    public void somaColunasAlternativas(int idcriterio) {
        cursor = db.rawQuery("SELECT IDALTERNATIVA2, SUM(IMPORTANCIA) AS SOMA FROM COMPARA_ALTERNATIVATEMP " +
                "WHERE IDCRITERIO = " + idcriterio + " GROUP BY IDALTERNATIVA2 ", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                try {
                    ContentValues valores;

                    db = banco.getWritableDatabase();
                    valores = new ContentValues();
                    valores.put("IDALT", cursor.getInt(cursor.getColumnIndex("IDALTERNATIVA2")));
                    valores.put("IDCRIT", idcriterio);
                    valores.put(SomaColunaBean.SOMA, cursor.getFloat(cursor.getColumnIndex("SOMA")));

                    db.insert(SomaColunaBean.SOMA_COLUNA_ALTERNATIVA, null, valores);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }while (cursor.moveToNext());
        }
    }

    public float retornaSoma(int id) {
        try {
            cursor = db.rawQuery("SELECT " + SomaColunaBean.SOMA + " FROM " + SomaColunaBean.TABELA +
                    " WHERE " + SomaColunaBean.IDCRIT + " = " + id, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                    return cursor.getFloat(cursor.getColumnIndex(SomaColunaBean.SOMA));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float retornaSomaAlternativa(int id, int idcrit) {
        try {
            cursor = db.rawQuery("SELECT " + SomaColunaBean.SOMA + " FROM " + SomaColunaBean.SOMA_COLUNA_ALTERNATIVA +
                    " WHERE IDALT = " + id +  " AND IDCRIT = " + idcrit, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                return cursor.getFloat(cursor.getColumnIndex(SomaColunaBean.SOMA));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleta() {
        db.execSQL("DELETE FROM " + SomaColunaBean.TABELA);
    }
    public void deletaAlternativa() {
        db.execSQL("DELETE FROM " + SomaColunaBean.SOMA_COLUNA_ALTERNATIVA);
    }
}
