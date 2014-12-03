package br.com.fiap.precoauto.activities;


import static br.com.fiap.precoauto.util.NumberUtils.currencyToDouble;
import static br.com.fiap.precoauto.util.NumberUtils.doubleToCurrency;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.precoauto.R;
import br.com.fiap.precoauto.VO.Marca;
import br.com.fiap.precoauto.VO.Modelo;
import br.com.fiap.precoauto.VO.Versao;

public class DetalheActivity extends Activity  {
	
	private Marca marca;
	private Modelo modelo;
	private Versao versao;
	
	private TextView referenciaText;
	private TextView marcaText;
	private TextView modeloText;
	private TextView anoText;
	private TextView valorMedioText;
	private TextView dataConsultaText;
	private Button graficoButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe);

		Intent i = getIntent();
		marca = (Marca) i.getSerializableExtra("marca");
		modelo = (Modelo) i.getSerializableExtra("modelo");
		versao = (Versao) i.getSerializableExtra("versao");
	
		referenciaText = (TextView) findViewById(R.id.refText);
		marcaText = (TextView) findViewById(R.id.marcaText);
		modeloText = (TextView) findViewById(R.id.modeloText);
		anoText = (TextView) findViewById(R.id.anoText);
		valorMedioText = (TextView) findViewById(R.id.valorText);
		dataConsultaText = (TextView) findViewById(R.id.dataText);
		graficoButton = (Button) findViewById(R.id.graficoButton);
		
		referenciaText.setText(versao.getRef());
		marcaText.setText(marca.getNome());
		modeloText.setText(modelo.getNome());
		anoText.setText(versao.getNome());
		Double value = currencyToDouble(versao.getValor());
		valorMedioText.setText(doubleToCurrency(value));
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
		dataConsultaText.setText(date_format.format(cal.getTime()));
		
		graficoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),"Vai pro grafico", Toast.LENGTH_LONG).show();
			}
		});
	}

}
