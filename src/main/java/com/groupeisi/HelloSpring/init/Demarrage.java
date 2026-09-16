package com.groupeisi.HelloSpring.init;

import com.groupeisi.HelloSpring.entities.Entreprise;
import com.groupeisi.HelloSpring.entities.Etudiant;
import com.groupeisi.HelloSpring.repositories.EntrepriseRepository;
import com.groupeisi.HelloSpring.repositories.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class Demarrage implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final EntrepriseRepository entrepriseRepository;


    // 100 prénoms africains : 80 sénégalais + 20 autres africains
    private String[] prenomsAfricains = {

            // 80 prénoms sénégalais
            "Amadou", "Mamadou", "Ibrahima", "Ousmane", "Cheikh",
            "Abdoulaye", "Modou", "Babacar", "Pape", "Serigne",
            "Moussa", "Samba", "Lamine", "Boubacar", "Alioune",
            "Malick", "Mansour", "Souleymane", "Daouda", "Tidiane",
            "Thierno", "Saliou", "Assane", "Abdou", "Matar",
            "Mor", "El Hadji", "Massamba", "Demba", "Balla",
            "Youssoupha", "Birame", "Makhtar", "Cheikhouna", "Ibra",
            "Fatou", "Awa", "Mariama", "Astou", "Aminata",
            "Mame", "Khady", "Ndeye", "Sokhna", "Rama",
            "Coumba", "Adama", "Bineta", "Diary", "Anta",
            "Rokhaya", "Ndèye", "Seynabou", "Sokhna", "Maguette",
            "Dieynaba", "Nabou", "Fama", "Mame Diarra", "Mame Fatou",
            "Mame Awa", "Mame Khady", "Mame Astou", "Mame Coumba",
            "Mame Mariama", "Mame Aminata", "Mame Sokhna", "Mame Anta",
            "Mame Bineta", "Mame Seynabou", "Aïssatou", "Binta",
            "Kiné", "Nafi", "Yacine", "Fari", "Sokhna",
            "Ndèye Fatou", "Ndèye Awa", "Ndèye Astou",

            // 20 autres prénoms africains
            "Kwame", "Kofi", "Chinua", "Chinedu", "Ngozi",
            "Amara", "Zuberi", "Jelani", "Amani", "Baraka",
            "Nia", "Zuri", "Thabo", "Lerato", "Sipho",
            "Nomsa", "Tendai", "Tariro", "Chipo", "Mpho"
    };


    // 50 noms de famille africains : 40 sénégalais + 10 autres africains
    private String[] nomsFamilleAfricains = {

            // 40 noms de famille sénégalais
            "Diop", "Ndiaye", "Fall", "Sow", "Ba",
            "Diallo", "Gueye", "Faye", "Sarr", "Sy",
            "Seck", "Mbaye", "Diouf", "Thiam", "Cissé",
            "Kane", "Dieng", "Niang", "Ndour", "Wade",
            "Tall", "Bâ", "Camara", "Touré", "Dramé",
            "Gassama", "Daff", "Sagna", "Badiane", "Mané",
            "Sonko", "Baldé", "Diedhiou", "Beye", "Ndao",
            "Lo", "Samb", "Gningue", "Bodian", "Coly",

            // 10 autres noms de famille africains
            "Mensah", "Okafor", "Nwosu", "Adeyemi", "Oluwole",
            "Mbeki", "Mahlangu", "Dlamini", "Chirwa", "Mwangi"
    };


    @Override
    public void run(String... args) throws Exception {

        log.info("Demarrage");


        /*
         * ============================================
         * INITIALISATION DES ETUDIANTS
         * ============================================
         */

        long nbEtudiants = etudiantRepository.count();

        log.info(
                "Il existe {} étudiant(s) en base",
                nbEtudiants
        );

        if (nbEtudiants == 0) {

            log.warn(
                    "Aucun étudiant en base, initialisation des étudiants"
            );

            int nbNewEtudiant =
                    (int) (Math.random() * 20) + 400;

            log.warn(
                    "{} étudiants seront créés",
                    nbNewEtudiant
            );

            for (int i = 0; i < nbNewEtudiant; i++) {

                int idxPrenom =
                        (int) (Math.random() * prenomsAfricains.length);

                String prenom =
                        prenomsAfricains[idxPrenom];


                int idxNom =
                        (int) (Math.random() * nomsFamilleAfricains.length);

                String nom =
                        nomsFamilleAfricains[idxNom];


                Etudiant etudiant = new Etudiant();

                etudiant.setNom(nom);
                etudiant.setPrenom(prenom);

                etudiant.setEmail(
                        prenom.charAt(0)
                                + nom
                                + i
                                + "@groupeisi.com"
                );

                etudiant.setNumCarte(
                        "2026GL" + (i + 1)
                );

                etudiantRepository.save(etudiant);
            }

        } else {

            log.info(
                    "Il y a déjà des étudiants en base, aucune initialisation"
            );
        }


        /*
         * ============================================
         * INITIALISATION DES ENTREPRISES
         * ============================================
         */

        long nbEntreprises = entrepriseRepository.count();

        log.info(
                "Il existe {} entreprise(s) en base",
                nbEntreprises
        );


        if (nbEntreprises == 0) {

            log.warn(
                    "Aucune entreprise en base, initialisation de 5 entreprises"
            );


            // Entreprise 1 : UNIVAL
            Entreprise entreprise1 = new Entreprise();

            entreprise1.setRaisonSociale("UNIVAL");
            entreprise1.setSecteurActivite(
                    "Communication"
            );
            entreprise1.setAdresse("Dakar");
            entreprise1.setEmail(
                    "unival@unival.sn"
            );
            entreprise1.setTelephone(
                    "338000001"
            );

            entrepriseRepository.save(entreprise1);


            // Entreprise 2 : SENAGRI
            Entreprise entreprise2 = new Entreprise();

            entreprise2.setRaisonSociale("SENAGRI");
            entreprise2.setSecteurActivite(
                    "Agriculture"
            );
            entreprise2.setAdresse("Dakar");
            entreprise2.setEmail(
                    "senagri@senagri.sn"
            );
            entreprise2.setTelephone(
                    "338000020"
            );

            entrepriseRepository.save(entreprise2);


            // Entreprise 3 : TERANGA TECH
            Entreprise entreprise3 = new Entreprise();

            entreprise3.setRaisonSociale(
                    "TERANGA TECH"
            );
            entreprise3.setSecteurActivite(
                    "Informatique"
            );
            entreprise3.setAdresse("Dakar");
            entreprise3.setEmail(
                    "teranga@terangatech.sn"
            );
            entreprise3.setTelephone(
                    "338420003"
            );

            entrepriseRepository.save(entreprise3);


            // Entreprise 4 : DAKAR LOGISTICS
            Entreprise entreprise4 = new Entreprise();

            entreprise4.setRaisonSociale(
                    "DAKAR LOGISTICS"
            );
            entreprise4.setSecteurActivite(
                    "Transport et logistique"
            );
            entreprise4.setAdresse("Dakar");
            entreprise4.setEmail(
                    "logistique@dakarlogistics.sn"
            );
            entreprise4.setTelephone(
                    "338004040"
            );

            entrepriseRepository.save(entreprise4);


            // Entreprise 5 : AFRICA DIGITAL
            Entreprise entreprise5 = new Entreprise();

            entreprise5.setRaisonSociale(
                    "AFRICA DIGITAL"
            );
            entreprise5.setSecteurActivite(
                    "Services numériques"
            );
            entreprise5.setAdresse("Dakar");
            entreprise5.setEmail(
                    "contact@africadigital.sn"
            );
            entreprise5.setTelephone(
                    "338220505"
            );

            entrepriseRepository.save(entreprise5);


            log.info(
                    "Les 5 entreprises ont été créées avec succès"
            );

        } else {

            log.info(
                    "Il y a déjà des entreprises en base, aucune initialisation"
            );
        }
    }
}
