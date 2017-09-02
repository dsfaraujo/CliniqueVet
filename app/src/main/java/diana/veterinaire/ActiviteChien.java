/*
 * Alves, Pedro
 * Shimizu, Patricia
 * Soares, Diana
*/
package diana.veterinaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;


public class ActiviteChien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_chien);

        // On recueille les donnees envoyees par la page precedente
        Intent intent = getIntent();
        Chien chien = (Chien) intent.getSerializableExtra("Chien");

        // Initialisation de l'acces a la base de donnees
        SQLiteHandler handler = new SQLiteHandler(this);

        List<String> races = handler.getAllRaces(chien);
        System.out.println("Races: " + races);


        // On affiche les donnees du chien dans un TextView.
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(chien.toString(chien, races));
        TextView textView2 = (TextView) findViewById(R.id.textView14);
        textView2.setText(chien.toString2(chien, races));


        // Titre de l'activite
        setTitle("Clinique Vétérinaire (" + chien.getNom()+")");
    }

    //Ouvrir la page d'informations du Client
    public void ouvrirActivityClient(View view) {
        Intent intent = new Intent(this, ActiviteClient.class);
        startActivity(intent);
    }

    //Ouvrir la page d'informations du Client
    public void ouvrirActivityConsultation(View view) {
        Intent intent = new Intent(this, ActiviteClient.class);
        startActivity(intent);
    }
}
