/*
 * Alves, Pedro
 * Shimizu, Patricia
 * Soares, Diana
*/

package diana.veterinaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Accueil extends AppCompatActivity {
    ArrayAdapter<Chien> adapter;
    List<Chien> chiens = new ArrayList<Chien>();
    SQLiteHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Titre de l'activite
        setTitle("Clinique Vétérinaire");

        // Initialisation de l'acces a la base de donnees
        handler = new SQLiteHandler(this);

        // Exercice [pragma table_info(Chien)]
        //handler.pragma();

        // Initialisation du spinner contenant toutes les races
        List<String> races= handler.getAllRaces();
        Spinner s = (Spinner) findViewById(R.id.sRace);
        ArrayAdapter<String> adapterRace = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, races);
        adapterRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapterRace);

        // Initialisation du ListView contenant la liste des chiens
        ListView lvChien = (ListView) findViewById(R.id.lvChiens);

        adapter = new ArrayAdapter<Chien>(this, android.R.layout.simple_list_item_2, android.R.id.text1, chiens)
        {
            public Chien getItem(int position){
                return chiens.get(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(chiens.get(position).toString());
                text2.setText(chiens.get(position).getNomMaitre());
                //text2.setText(chiens.get(position).getNomMaitre() + "\n" + chiens.get(position).getDdn());
                return view;
            }
        };

        lvChien.setAdapter(adapter);
        lvChien.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long l)
            {
                // Si l'usager appuie sur un element de la liste de chien, on passe
                // a l'activite chien.  On lui passe l'objet chien concerne.

                Chien chien = (Chien)adapter.getItemAtPosition(position);
                Intent intent = new Intent(Accueil.this, ActiviteChien.class);
                intent.putExtra("Chien", chien);
                startActivity(intent);
            }
        });

        final EditText etNom = (EditText)findViewById(R.id.etNom);
        final EditText etDDN = (EditText)findViewById(R.id.etDDN);
        final EditText etMaitre = (EditText)findViewById(R.id.etMaitre);
        final Spinner sRace = (Spinner) findViewById(R.id.sRace);

        // Initialisation du bouton rechercher
        Button button= (Button) findViewById(R.id.bRecherche);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rechercher(etNom.getText().toString(),
                        etDDN.getText().toString(),
                        etMaitre.getText().toString(),
                        sRace.getSelectedItem().toString());
            }
        });
    }

    /**
     * rechercher fait appel au Handler de base de données pour obtenir la liste des chiens selon
     * les criteres de recherche entres. L'adapteur du listview des chien est mis à jour, ainsi
     * que la liste chiens.  Les criteres sont :
     *
     * @param nomChien
     * @param ddn
     * @param maitre
     * @param race
     */
    private void rechercher(String nomChien, String ddn, String maitre, String race)
    {
        adapter.clear();

        System.out.println(nomChien+ " " + ddn + " " + maitre + " " + race);

        // Faire appel à SQLiteHandler pour rechercher les chiens selon les informations données
        chiens =  handler.getAllChiens(nomChien, ddn, maitre, race);

        adapter.addAll(chiens);
        adapter.notifyDataSetChanged();
    }
}

