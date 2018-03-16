package br.com.pointstore.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

import br.com.pointstore.Adapter.UsuarioLogin;
import br.com.pointstore.model.Usuario;
import retrofit2.Callback;

/**
 * Created by Oliveira on 05/12/2016.
 */
//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Arley Laurindo de Oliveira
//Esta classe tem função de criar operacoes de banco de dados, com apenas 2 metodos inserir e listar
//******************************************************

public class DataAccessObject  extends SQLiteOpenHelper {

    private static final String TAG_LOGIN_OK = "LOGIN AUTORIZADO";
    private static final String TAG_ERRO_AUTENTICAR = "LOGIN NÃO REALIZADO";
    private static final String TAG_V = "VERIFICANDO_LOGIN";
    private static final String DATABASE_NAME = "login_salvo.db";

    private static final String TABLE_USUARIO_LOGADO = "usuario";

    private static final int DATABASE_VERSION = 1;


    // Criando as TAGS para imprimir o Log de cada operação
    private static final String TAG_D = "DELETAR REGISTRO";
    private static final String TAG_U = "UPDATE DATA";
    private static final String TAG_I = "INSERIR REGISTRO";
    private static final String TAG_S = "SELECIONAR REGISTROS";

    public DataAccessObject(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //comandos e operacoes sql criando tabela
    public void onCreate(SQLiteDatabase db) {

        Log.d("Teste ","criando Banco");

        String sql = "CREATE TABLE " + TABLE_USUARIO_LOGADO
                + "(id INTEGER PRIMARY KEY, " +
                "login TEXT, " +
                "senha TEXT);";

        db.execSQL(sql);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_USUARIO_LOGADO;

        db.execSQL(sql);

        onCreate(db);

    }
    public void deletar(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USUARIO_LOGADO, null, null);
        db.close();
    }
    //metodo de busca os dados de acordo com a tabela e receber  o resultado jogando dentro de uma lista
    public ArrayList<UsuarioLogin> CarregarPessoasDoBanco() {

        // Passo 01 - Criar o arraylist de PessoaFisica
        ArrayList<UsuarioLogin> listaPessoasFisicas = new ArrayList<UsuarioLogin>();

        // Passo 02 - Criar o SQL para selecionar os registros do banco
        String sql = "SELECT * FROM " + TABLE_USUARIO_LOGADO;

        // Passo 03 - Recuperar os registros
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        // Passo 04 - Percorrer o cursor e salvar os registros de PessoaFisica
        while (cursor.moveToNext()) {
            // Criação da instancia de PessoaFisica utilizando informações
            // provenientes da base de dados
            UsuarioLogin usuarioLogin = new UsuarioLogin();

            // Construindo o objeto a partir dos registros da base de dados
            usuarioLogin.setLogin(cursor.getString(0));
            usuarioLogin.setSenha(cursor.getString(1));





            listaPessoasFisicas.add(usuarioLogin);
           // Log.i(TAG_S, "O registro de id: "+usuarioLogin.get()+" foi selecionado");
        }

        return listaPessoasFisicas;
    }

    /*esse método tem como função receber os dados de um objeto e em seguida
     jogar dentro da função a nivel de banco o qual persiste os dados.*/


    public void inserirUsuarioLogado(UsuarioLogin usuarioLogin){

        ContentValues contentValues = new ContentValues();

        contentValues.put("login", usuarioLogin.getLogin());
        contentValues.put("senha ", usuarioLogin.getSenha());





        getWritableDatabase().insert(TABLE_USUARIO_LOGADO, null, contentValues);

    }


    public UsuarioLogin montaUsuario(Cursor cursor) {
        if (cursor.getCount() == 0) {

            Log.i(TAG_ERRO_AUTENTICAR, "DADOS FORNECIDO INVÁLIDOS");

            return null;
        }

        String login = cursor.getString(cursor.getColumnIndex("login"));
        String senha = cursor.getString(cursor.getColumnIndex("senha"));

        UsuarioLogin novo = new UsuarioLogin();
        novo.setLogin(login);
        novo.setSenha(senha);
        Log.i(TAG_LOGIN_OK, "Usuario : " + login + "  e senha  :" + senha);

        return novo;
    }



    public  UsuarioLogin procurarLoginSalvoSQLlite() {
        String sql = "SELECT * FROM " + TABLE_USUARIO_LOGADO +";";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        cursor.moveToFirst();
        return montaUsuario(cursor);
    }

    public void atualizarSenhaDoUsuarioLogado(Usuario usuario){

        UsuarioLogin usuarioLogin = new UsuarioLogin();

        usuarioLogin.setLogin(usuario.getLogin());
        usuarioLogin.setSenha(usuario.getSenha());



        ContentValues valores = new ContentValues();

        valores.put("senha", usuarioLogin.getSenha());


        String[] args = new String[]{usuarioLogin.getLogin()};

        getWritableDatabase().update(TABLE_USUARIO_LOGADO, valores, "login=?", args);

        Log.i(TAG_I, "Senha atualizada no SQL, nova senha é: : "+ args);

    }


}
