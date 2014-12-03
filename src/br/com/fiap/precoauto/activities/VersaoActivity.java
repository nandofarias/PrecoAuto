package br.com.fiap.precoauto.activities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import br.com.fiap.precoauto.R;
import br.com.fiap.precoauto.VO.Marca;
import br.com.fiap.precoauto.VO.Modelo;
import br.com.fiap.precoauto.VO.Versao;
import br.com.fiap.precoauto.adapters.VersaoAdapter;

import com.google.gson.Gson;

public class VersaoActivity extends Activity implements
		SearchView.OnQueryTextListener, SearchView.OnCloseListener {
	private ListView myList;
	private SearchView searchView;
	private VersaoAdapter defaultAdapter;
	private List<Versao> versoes;
	private String idModelo;
	private Marca marca;
	private Modelo modelo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_versao);
		myList = (ListView) findViewById(R.id.listVersao);

		Intent i = getIntent();
		marca = (Marca) i.getSerializableExtra("marca");
		modelo = (Modelo)i.getSerializableExtra("modelo");
		idModelo = modelo.getId();
		
		versoes = new ArrayList<>();
		defaultAdapter = new VersaoAdapter(VersaoActivity.this, versoes);
		myList.setAdapter(defaultAdapter);
		myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = new Intent(VersaoActivity.this, DetalheActivity.class);
				i.putExtra("marca", marca);
				i.putExtra("modelo", modelo);
				i.putExtra("versao", (Versao)defaultAdapter.getItem(position));
				startActivity(i);
			}
		});

		this.init();

		searchView = (SearchView) findViewById(R.id.searchVersao);

		searchView.setIconifiedByDefault(false);

		searchView.setOnQueryTextListener(this);
		searchView.setOnCloseListener(this);
	}

	@Override
	public boolean onClose() {
		myList.setAdapter(defaultAdapter);
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		displayResults(query);
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (!newText.isEmpty()) {
			displayResults(newText);
		} else {
			init();
		}

		return false;
	}

	private void displayResults(String query) {
		List<Versao> versaoAux =  new ArrayList<>();
		
		for (Versao versao : versoes) {
			if (versao.getNome().toLowerCase().trim().contains(query)) {
				versaoAux.add(versao);
			}
		}
		
		defaultAdapter = new VersaoAdapter(VersaoActivity.this, versaoAux);
		myList.setAdapter(defaultAdapter);


	}

	public void init() {
		new HttpRequestTask().execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_refresh) {
			this.init();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class HttpRequestTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			String urlString = "http://zillius.com.br/fipe/versoes/"+ idModelo;
			String resultToDisplay = "";

			InputStream in = null;

			try {

				URL url = new URL(urlString);

				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();

				in = new BufferedInputStream(urlConnection.getInputStream());

				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));

				resultToDisplay = br.readLine();

				br.close();

			} catch (Exception e) {

				System.out.println(e.getMessage());

				return e.getMessage();

			}

			return resultToDisplay;
		}

		@Override
		protected void onPostExecute(String json) {

			Gson gson = new Gson();
			Versao[] versoesArray = gson.fromJson(json, Versao[].class);
			versoes = Arrays.asList(versoesArray);
			defaultAdapter = new VersaoAdapter(VersaoActivity.this, versoes);
			myList.setAdapter(defaultAdapter);

		}

	}

}
