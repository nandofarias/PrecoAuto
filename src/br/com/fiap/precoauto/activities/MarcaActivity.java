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

import com.google.gson.Gson;

import br.com.fiap.precoauto.R;
import br.com.fiap.precoauto.VO.Marca;
import br.com.fiap.precoauto.adapters.MarcaAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

public class MarcaActivity extends Activity implements
		SearchView.OnQueryTextListener, SearchView.OnCloseListener {
	private ListView myList;
	private SearchView searchView;
	private MarcaAdapter defaultAdapter;
	private List<Marca> marcas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_marca);
		myList = (ListView) findViewById(R.id.listMarca);

		marcas = new ArrayList<>();
		defaultAdapter = new MarcaAdapter(MarcaActivity.this, marcas);
		myList.setAdapter(defaultAdapter);
		myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = new Intent(MarcaActivity.this, ModeloActivity.class);
				i.putExtra("idMarca", ( (Marca) defaultAdapter.getItem(position)).getId());
				startActivity(i);
			}
		});

		this.init();

		searchView = (SearchView) findViewById(R.id.searchMarca);

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
		List<Marca> marcasAux =  new ArrayList<>();
		
		for (Marca marca : marcas) {
			if (marca.getNome().toLowerCase().trim().contains(query)) {
				marcasAux.add(marca);
			}
		}
		
		defaultAdapter = new MarcaAdapter(MarcaActivity.this, marcasAux);
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
			String urlString = "http://www.zillius.com.br/fipe/marcas";
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
			Marca[] marcasArray = gson.fromJson(json, Marca[].class);
			marcas = Arrays.asList(marcasArray);
			defaultAdapter = new MarcaAdapter(MarcaActivity.this, marcas);
			myList.setAdapter(defaultAdapter);

		}

	}

}
