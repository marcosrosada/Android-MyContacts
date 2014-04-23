package com.marcosrosada.mycontacts;

import java.util.List;

import com.marcosrosada.mycontacts.dao.ContatoDAO;
import com.marcosrosada.mycontacts.modelo.ContatoVO;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaContatosActivity extends Activity {

		private ListView listaContatos;
		private List<ContatoVO> contatos;
		protected ContatoVO contatoSelected;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.listagem_contatos);
			
			listaContatos = (ListView) findViewById(R.id.listaContatos);
			carregaLista();
			
			registerForContextMenu(listaContatos);
			
			listaContatos.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
						
					contatoSelected = (ContatoVO) parent.getItemAtPosition(position);
					return false;
				}
			});
		}
		
		
		private void carregaLista()
		{
			ContatoDAO dao = new ContatoDAO(this);
			contatos = dao.getListaContatos();
			dao.close();
			
			int layout = android.R.layout.simple_list_item_1;
			
			ArrayAdapter<ContatoVO> adapter = new ArrayAdapter<ContatoVO>(this,  layout, contatos);
			
			listaContatos.setAdapter(adapter);
		}
		
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.menu_action_bar, menu);
		
			return super.onCreateOptionsMenu(menu);
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			
			switch (item.getItemId()) {
			case R.id.btnNovo:
				
				Intent intent = new Intent(ListaContatosActivity.this, FormularioActivity.class);
				startActivity(intent);
				return false;

			default:
				return super.onOptionsItemSelected(item);
			}
			
		}
		
		
		@Override
		protected void onResume() {
			carregaLista();
			super.onResume();
		}
		
		
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,
				ContextMenuInfo menuInfo) {
			
			//super.onCreateContextMenu(menu, v, menuInfo);
			menu.add("Alterar");
			MenuItem deletar = menu.add("Deletar");
			
			deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					
					new AlertDialog.Builder(ListaContatosActivity.this)
					.setTitle("Deletar")
					.setMessage("Deletar contato?")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton("Sim", 
							new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) 
								{
									ContatoDAO dao = new ContatoDAO(ListaContatosActivity.this);
									dao.deletar(contatoSelected);
									dao.close();
									carregaLista();
								}
							}).setNegativeButton("Não", null).show();
					
					
					return false;
				}
			});
		}
}
