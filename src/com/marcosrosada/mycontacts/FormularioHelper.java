package com.marcosrosada.mycontacts;

import com.marcosrosada.mycontacts.modelo.ContatoVO;

import android.content.ContentValues;
import android.widget.EditText;

public class FormularioHelper {

	private EditText nome;
	private EditText fone;
	private ContatoVO contato;
	
	public FormularioHelper(FormularioActivity activity) {
		
		nome = (EditText) activity.findViewById(R.id.txtNome);
		fone = (EditText) activity.findViewById(R.id.txtFone);
		
		contato = new ContatoVO();
	}
	
	public ContatoVO getContatoFromFormulario()
	{
		contato.setNome(nome.getEditableText().toString());
		contato.setFone(fone.getEditableText().toString());
		
		return contato;
	}

	
	
}
