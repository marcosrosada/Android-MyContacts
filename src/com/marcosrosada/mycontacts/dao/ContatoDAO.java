package com.marcosrosada.mycontacts.dao;

import java.util.ArrayList;
import java.util.List;

import com.marcosrosada.mycontacts.modelo.ContatoVO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ContatoDAO extends SQLiteOpenHelper {

	private static final int VERSAO = 1;
	private static final String TABELA = "Contatos";
	private static final String DATABASE = "MyContacts";
	private static final String[] COLUNAS = {"id", "nome", "fone"};
	
	public ContatoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sqlCreate = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, " +
				" nome TEXT UNIQUE NOT NULL, " +
				" fone TEXT);";
		
		db.execSQL(sqlCreate);
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);
	}

	public List<ContatoVO> getListaContatos()
	{
		List<ContatoVO> listaContatos = new ArrayList<ContatoVO>();
		
		Cursor c = getWritableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);
		
		while (c.moveToNext()) {
			ContatoVO contato = new ContatoVO();
			
			contato.setId(c.getLong(0));
			contato.setNome(c.getString(1));
			contato.setFone(c.getString(2));
			
			listaContatos.add(contato);
		}
		
		c.close();
		
		return listaContatos;
	}
	
	
	
	public void insere(ContatoVO contato)
	{
		ContentValues values = new ContentValues();
		values.put("nome", contato.getNome());
		values.put("fone", contato.getFone());
		
		getWritableDatabase().insert(TABELA, null, values);
	}
	
	
	
	public void deletar(ContatoVO contato)
	{
		String[] args = { contato.getId().toString() };
		
		getWritableDatabase().delete(TABELA, "id=?", args);
	}
}
