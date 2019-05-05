package br.com.juliana.loureiro.projetofinalahp.Bean;

public class PesoCriteriosBean {
    public static String ID = "ID";
    public static String IDCRIT = "IDCRIT";
    public static String SOMA= "PESO";
    public static String YMAX= "YMAX";
    public static String TOTALDIVISAO= "TOTALDIVISAO";
    public static String TABELA = "PESO_CRITERIOS";

    private int id;
    private int idcrit;
    private float peso;
    private float ymax;
    private float totaldivisao;

    public float getTotaldivisao() {
        return totaldivisao;
    }

    public void setTotaldivisao(float totaldivisao) {
        this.totaldivisao = totaldivisao;
    }

    public float getYmax() {
        return ymax;
    }

    public void setYmax(float ymax) {
        this.ymax = ymax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcrit() {
        return idcrit;
    }

    public void setIdcrit(int idcrit) {
        this.idcrit = idcrit;
    }

    public float getSoma() {
        return peso;
    }

    public void setSoma(float soma) {
        this.peso = soma;
    }
}
