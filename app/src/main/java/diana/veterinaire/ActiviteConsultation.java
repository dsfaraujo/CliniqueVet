package diana.veterinaire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActiviteConsultation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_consultation);
        // Titre de l'activite
        setTitle("Clinique Vétérinaire");
    }
}
