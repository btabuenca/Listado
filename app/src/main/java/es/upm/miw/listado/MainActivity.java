package es.upm.miw.listado;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String TAG = "MiW";
    ListView lvListado;
    // String[] datos = {"Dato 00", "Dato 01", "Dato 02"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvListado = findViewById(R.id.listado);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
                this,
                R.array.datos,
                R.layout.elemento
        );
//        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                datos
//        );
        lvListado.setAdapter(adaptador);
        lvListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = ((TextView) view).getText().toString();
                Log.i(TAG, "Texto=" + str);
            }
        });
    }
}
