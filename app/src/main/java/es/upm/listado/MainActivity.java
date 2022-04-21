package es.upm.listado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String classname = "MainActivity";
    ListView lvListadoXML, lvListadoCSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Inicialización
        //tv = findViewById(R.id.textViewData);
        lvListadoXML = findViewById(R.id.listadoXML);
        lvListadoCSV = findViewById(R.id.listadoCSV);


        // Lectura del fichero xml
        ArrayAdapter<CharSequence> adaptadorXML = ArrayAdapter.createFromResource(
                this,
                R.array.datos,
                R.layout.elemento
        );

        lvListadoXML.setAdapter(adaptadorXML);
        lvListadoXML.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
            }
        });



        // Datos csv
        List<String[]> rows = new ArrayList<>();
        CSVReader csvReader = new CSVReader(this, "telemetrias.csv");
        try {
            rows = csvReader.readCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> lst = new ArrayList<String>();
        for (int i = 0; i < rows.size()-1; i++) {
            String sRow = String.format("row %s: %s, %s", i, rows.get(i)[0], rows.get(i)[1]);
            lst.add(sRow);

            Log.d(classname, String.format("row %s: %s, %s", i, rows.get(i)[0], rows.get(i)[1]));
            //sResultados+= String.format("%s. %s | Temper: %s | Humed: %s  | CO2; %s \n", i, rows.get(i)[0], rows.get(i)[1], rows.get(i)[2], rows.get(i)[3] , rows.get(i)[3]);

        }

        ArrayAdapter<String> adapterCSV = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lst);
        lvListadoCSV.setAdapter(adapterCSV);
        lvListadoCSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = ((TextView) view).getText().toString();
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
            }
        });








    }
}