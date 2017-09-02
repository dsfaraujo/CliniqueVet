/*
 * Alves, Pedro
 * Shimizu, Patricia
 * Soares, Diana
*/

package diana.veterinaire;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler extends SQLiteAssetHelper
{
    // Version de la base de données
    private static final int DATABASE_VERSION = 3;

    // Nom de la base de données
    private static final String DATABASE_NAME = "bdClinique3";


    // ************************* CHIEN ************************************
    // Nom de la table: Chien
    private static final String TABLE_CHIEN = "Chien";

    // Nom des attributs Chien
    private static final String ID_CHIEN = "id_chien";
    private static final String NOM_CHIEN = "nom";
    private static final String DDN = "dtNaissance";
    private static final String POIDS = "poids";
    private static final String GRANDEUR = "grandeur";
    private static final String SEXE = "sexe";
    private static final String STERILISE = "sterilise";
    private static final String DDD = "dtDeces";


    // ************************* RACE ***********************************
    // Nom de la table: Race
    private static final String TABLE_RACE = "Race";

    // Nom des attributs Race
    private static final String ID_RACE = "id_race";
    private static final String NOM_RACE = "nom";


    // ******************* VIEW_TP********************************
    // Nom de la view: ChienClient
    private static final String VIEW_TP = "ViewTP";

    // Nom des attributs ChienClient
    private static final String VIEW_TP_NOM_CHIEN = "ChienNom";
    private static final String VIEW_TP_NOM_RACE = "nomRace";
    private static final String VIEW_TP_MAITRE = "maitre";
    private static final String VIEW_TP_DATE_NAISSANCE = "ddn";
    private static final String VIEW_TP_DATE_DECES = "ddd";
    private static final String VIEW_TP_PRENOM_CLIENT = "prenom";

    // ************************* CLIENT ***********************************
    // Nom de la table: Client
    private static final String TABLE_CLIENT = "Client";

    // Nom des attributs Client
    private static final String ID_CLIENT = "id_client";
    private static final String PRENOM_CLIENT = "prenom";
    private static final String NOM_CLIENT = "nom";
    private static final String TELEPHONE = "telephone";



    public SQLiteHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * getAllChien batit un ArrayList contenant les attributs de tous les chiens dans la base de
     * donnees.
     * @return Une liste d'objets Chien
     */
    public List<Chien> getAllChiens(String nomChien, String ddn, String maitre, String race)
    {
        List<Chien> chiens = new ArrayList<Chien>();

        final String SELECT_ALL_CHIENS = "SELECT * FROM " + VIEW_TP;
        final String S_RACE = VIEW_TP_NOM_RACE + " = " + "\"" + race + "\"";
        final String S_NOM_CHIEN = VIEW_TP_NOM_CHIEN + " LIKE " + "\"%" + nomChien + "%\"";
        final String S_NOM_MAITRE = VIEW_TP_MAITRE + " LIKE " + "\"%" + maitre + "%\"";
        final String S_DATE_NAISSANCE = VIEW_TP_DATE_NAISSANCE + " LIKE " + "\"%" + ddn + "%\"";
        String selectQuery = SELECT_ALL_CHIENS;

        // Recherche par Race
        if(!race.equals("Non spécifiée"))
        {
            selectQuery = SELECT_ALL_CHIENS + " WHERE " + S_RACE;

            if(nomChien.length() > 0)
            {
                selectQuery = selectQuery + " AND " + S_NOM_CHIEN;
            }

            if(ddn.length() > 0)
            {
                selectQuery = selectQuery + " AND " + S_DATE_NAISSANCE;
            }

            if(maitre.length() > 0)
            {
                selectQuery = selectQuery + " AND " + S_NOM_MAITRE;
            }
        }

        // Recherche par Nom Chien
        else if(nomChien.length() > 0)
        {
            selectQuery = SELECT_ALL_CHIENS + " WHERE " + S_NOM_CHIEN;

            if(ddn.length() > 0)
            {
                selectQuery = selectQuery + " AND " + S_DATE_NAISSANCE;
            }

            if(maitre.length() > 0)
            {
                selectQuery = selectQuery + " AND " + S_NOM_MAITRE;
            }

        }

        // Recherche par Date de Naissance
        else if(ddn.length() > 0)
        {
            selectQuery = SELECT_ALL_CHIENS + " WHERE " + S_DATE_NAISSANCE;

            if(maitre.length() > 0)
            {
                selectQuery = selectQuery + " AND " + S_NOM_MAITRE;
            }
        }

        /// Recherche par Nom Maitre
        else if(maitre.length() > 0)
        {
            selectQuery = SELECT_ALL_CHIENS + " WHERE " + S_NOM_MAITRE;

        }


        System.out.println("Query = " + selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Chien chien = new Chien();
                System.out.println(cursor.getString(2));

                chien.setId(Integer.parseInt(cursor.getString(0)));
                chien.setId_client(Integer.parseInt(cursor.getString(1)));
                chien.setNom(cursor.getString(2));
                chien.setDdn(Date.valueOf(cursor.getString(3)));
                chien.setPoids(Double.parseDouble(cursor.getString(4)));
                chien.setGrandeur(Double.parseDouble(cursor.getString(5)));
                chien.setSexe(cursor.getString(6));
                chien.setSterilise(cursor.getString(7));
                if(cursor.getString(8) != null)
                {
                    chien.setDdd(Date.valueOf(cursor.getString(8)));
                }
                chien.setId_race(Integer.parseInt(cursor.getString(9)));
                chien.setRace(cursor.getString(10));
                chien.setPrenom(cursor.getString(11));
                chien.setNomMaitre(cursor.getString(12));

                chiens.add(chien);
            } while (cursor.moveToNext());
        }
        return chiens;
    }


    /**
     * getAllRaces batit un ArrayList contenant le nom de toutes les races qui apparaissent dans
     * la base de donnees
     * @return Une liste de noms de race
     */
    public List<String> getAllRaces()
    {
        List<String> races = new ArrayList<String>();

        String selectQuery = "SELECT " + ID_RACE + ", " + NOM_RACE + " FROM " + TABLE_RACE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        races.add("Non spécifiée");
        if (cursor.moveToFirst())
        {
            do
            {
                races.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return races;
    }



    /**
     * getAllRaces(Chien chien) cherche toutes les races d'un chien specifique et retourne une Liste
     * @param chien à faire la recherche
     * @return races, la liste avec les races du chien
     */
    public List<String> getAllRaces(Chien chien)
    {
        List<String> races = new ArrayList<String>();

        String selectQuery = "SELECT " + VIEW_TP_NOM_RACE + " FROM " + VIEW_TP +
                " WHERE " + VIEW_TP_NOM_CHIEN + " = " + "\"" + chien.getNom() + "\"";
        System.out.println("Query Races: " + selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                races.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return races;
    }


    /*
    * Exercice: listez les colonnes de la table Chien
    */
    public void pragma()
    {
        String selectQuery = "PRAGMA TABLE_INFO(" + TABLE_CHIEN + ")";
        System.out.println("Query PRAGMA: " + selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                //System.out.println(cursor.getString(1) + " / " + cursor.getString(4));
                System.out.println(cursor.getString(cursor.getColumnIndex("name")) + " / " + cursor.getString(cursor.getColumnIndex("dflt_value")));
            } while (cursor.moveToNext());
        }

    }

}