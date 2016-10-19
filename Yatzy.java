// spelaSiffror() - kolla om du spara siffror av samma värde
// spelaSiffror() - bonus
import java.util.Random;
import java.util.*;

public class Yatzy {


    public static void main(String[] arg) {

        Spelare spelare1 = new Spelare();

        spelare1.spelaSiffror();
        //spelare1.spelaPoker();

    }
}


class Tärning {

    int kastatvärde;

    public Tärning() {

    }

    int kastaTärning() {
        Random generator = new Random();
        int rnd = generator.nextInt(6) + 1;

        kastatvärde = rnd;
        return kastatvärde;
    }

}


class Spelare {

    int siffrorKastSumma;
    Vector sparadKastSumma;
    Vector värdenPåTärningskast;
    Vector tärningar;
    Vector sparadeVärdenPåTärningskast;
    Vector tillfälligPokerSumma;
    Vector sparadPokerSumma;
    int antalTärningar = 5;

    public Spelare() {

        sparadKastSumma = new Vector();
        värdenPåTärningskast = new Vector();
        sparadeVärdenPåTärningskast = new Vector();
        tillfälligPokerSumma = new Vector();
        sparadPokerSumma = new Vector();

    }

    void skapaTärningar() {

        // Skapa ny vektor för varje spelomgång, då man kastar nya tärningar
        tärningar = new Vector();

        for(int i = 0; i < antalTärningar; i++) {

            Tärning tärning = new Tärning();
            tärningar.add(tärning);

            //System.out.println(tärningar.get(i));
        }
    }

    void kastaTärningar() {

        for(int i = 0; i < tärningar.size(); i++) {

            int värde = ((Tärning) tärningar.get(i)).kastaTärning();
            // Spara värdet av kastade tärningar
            värdenPåTärningskast.add(värde);
            // Skriv ut tärningen id
            //System.out.println((Tärning) tärningar.get(i));
            // Skriv ut värdet
            System.out.println(värde);

        }
    }

    void siffrorKastSumma(int förväntatvärde) {
        int antal = 0;
        for(int i = 0; i < tärningar.size(); i++) {
            if (((Tärning) tärningar.get(i)).kastatvärde == förväntatvärde) {

                siffrorKastSumma += förväntatvärde;
                antal++;
            }

        }
        for (int i = 0; i < antal; i++) {
            // Minska antal tärningar med 1
            tärningar.remove(i);
        }

        System.out.println("Tillfällig summa: " + siffrorKastSumma);
        System.out.println("Antal tärningar: " + tärningar.size());

    }

    void sparaKastSumma() {
        sparadKastSumma.add(siffrorKastSumma);
        System.out.println("Sparad summa: " + sparadKastSumma);
    }

    void pokerKastSumma(int tärningsNr) {

        // Spara vald tärnings kastvärde (inkrementsumma)
        siffrorKastSumma += ((Tärning) tärningar.get(tärningsNr-1)).kastatvärde;
        // Spara tärningsvärde i vektor
        sparadeVärdenPåTärningskast.add(((Tärning) tärningar.get(tärningsNr-1)).kastatvärde);
        // Ta bort tärningen
        tärningar.remove(tärningsNr-1);
        // Ta bort alla kastadevärden
        värdenPåTärningskast.remove(tärningsNr-1);

        System.out.println("Tillfällig summa (val): " + siffrorKastSumma);
        System.out.println("Antal tärningar (val): " + tärningar.size());
        System.out.println("Sparade tärningar (val): " + sparadeVärdenPåTärningskast);
        System.out.println("Tärningsvärden (val): " + värdenPåTärningskast);

    }


    void sparaTärning() {

        System.out.println("Värden på Tärningskast: " + värdenPåTärningskast);

        while (tärningar.size() > 0) {
            System.out.println("Spara tärning?");
            Scanner sc = new Scanner(System.in);
            String fraga = sc.next();

            if (fraga.equals("ja")) {
                // Spara tärning
                sparaSpecifikTärning();
                break;
            } else if (fraga.equals("nej")) {
                // Ta bort alla värden för det aktuella tärningskastet
                värdenPåTärningskast.removeAllElements();
                break;
            } else {
                System.out.println("Skriv in 'ja' eller 'nej'");
            }
        }

    }

    void sparaSpecifikTärning() {
        String fraga;

        while (true) {
            try {
                System.out.println("Vilken tärning vill du spara? 1-" + tärningar.size());
                Scanner sc = new Scanner(System.in);
                int tärning = sc.nextInt();

                if(tärning >= 1 && tärning <= tärningar.size()) {

                    pokerKastSumma(tärning);

                    if(tärningar.size() > 0) {

                        System.out.println("Klar?");
                        Scanner sp = new Scanner(System.in);
                        fraga = sp.next();
                    }

                    else {
                        break;
                    }

                    if (fraga.equals("ja")) {
                        värdenPåTärningskast.removeAllElements();
                        break;
                    } else if (fraga.equals("nej")) {
                        for(int i = 0; i < värdenPåTärningskast.size(); i++) {
                            System.out.println(värdenPåTärningskast.get(i));
                        }
                    } else {
                        System.out.println("Skriv in 'ja' eller 'nej'");
                    }

                }
                else {
                    System.out.println("Du angav ett felaktigt värde");
                }
                // Om värdet på "rutor" inte är heltal (int) så skriv nedanstående ut
            } catch (InputMismatchException exception) {
                //Print "This is not an integer"
                //when user put other than integer
                System.out.println("Du angav inte ett heltal!");
            }
        }
    }


    void rensaVektorer() {
        värdenPåTärningskast.removeAllElements();
        sparadeVärdenPåTärningskast.removeAllElements();
        värdenPåTärningskast.removeAllElements();
        tillfälligPokerSumma.removeAllElements();
    }

    void ettPar() {
        System.out.println("/// Ett par ///");
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println(sparadeVärdenPåTärningskast);
        // Sortera sparadeVärdenPåTärningskast
        Collections.sort(sparadeVärdenPåTärningskast);
        for (int i = 0; i < sparadeVärdenPåTärningskast.size() - 1; i++) {

            if (sparadeVärdenPåTärningskast.get(i) == sparadeVärdenPåTärningskast.get(i + 1)) {
                tillfälligPokerSumma.add((Integer) sparadeVärdenPåTärningskast.get(i) + (Integer) sparadeVärdenPåTärningskast.get(i + 1));
            }
        }

        //Hitta högsta paret
        if(tillfälligPokerSumma.size() > 0) {
            // Räkna ut högsta paret om det finns
            System.out.println("Tillfälliga par: " + tillfälligPokerSumma);
            Object högst = Collections.max(tillfälligPokerSumma);
            System.out.println("Högst: " + högst);
            sparadPokerSumma.add(högst);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

        // Om inget par återfinns i (sparade + sista kastet) tärningar så lägg till 0 i vektorn "sparadPokerSumma"
        else {
            sparadPokerSumma.add(0);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

    }

    void tvåPar() {
        System.out.println("/// Två par ///");
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println(sparadeVärdenPåTärningskast);
        // Sortera sparadeVärdenPåTärningskast
        Collections.sort(sparadeVärdenPåTärningskast);
        for (int i = 0; i < sparadeVärdenPåTärningskast.size() - 1; i++) {
            if (sparadeVärdenPåTärningskast.get(i) == sparadeVärdenPåTärningskast.get(i + 1)) {
                tillfälligPokerSumma.add((Integer) sparadeVärdenPåTärningskast.get(i) + (Integer) sparadeVärdenPåTärningskast.get(i + 1));
            }
        }

        //Hitta högsta paret
        if(tillfälligPokerSumma.size() > 0) {
            // Räkna ut högsta paret om det finns
            System.out.println("Tillfälliga poker: " + tillfälligPokerSumma);
            Object högst = Collections.max(tillfälligPokerSumma);
            System.out.println("Högst: " + högst);

            // Ta bort de högsta värde(na) från "tillfälligPokerSumma"
            for(int i = 0; i < tillfälligPokerSumma.size(); i++) {
                tillfälligPokerSumma.removeElement(högst);
            }
            // Om det finns fler värden i vektor (dvs ett par till)
            if(tillfälligPokerSumma.size() > 0) {
                // Räkna ut det hösta paret (som nu är näst högst då vi tog bort det högsta tidigare)
                System.out.println("Tillfälliga poker (näst högst): " + tillfälligPokerSumma);
                Object nästhögst = Collections.max(tillfälligPokerSumma);
                System.out.println("Näst högst: " + nästhögst);
                // Lägg till summan av höst och näst högst
                sparadPokerSumma.add((Integer) högst + (Integer) nästhögst);
                System.out.println("Sparad pokersumma: " + sparadPokerSumma);

            }
            // Annars lägg till bara hösta paret
            else {

                sparadPokerSumma.add(högst);
                System.out.println("Sparad pokersumma: " + sparadPokerSumma);
            }
        }

        // Om inget par återfinns i (sparade + sista kastet) tärningar så lägg till 0 i vektorn "sparadPokerSumma"
        else {
            sparadPokerSumma.add(0);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

    }

    void triss() {
        System.out.println("/// Triss ///");
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println(sparadeVärdenPåTärningskast);
        // Sortera sparadeVärdenPåTärningskast
        Collections.sort(sparadeVärdenPåTärningskast);
        for (int i = 0; i < sparadeVärdenPåTärningskast.size() - 2; i++) {

            if (sparadeVärdenPåTärningskast.get(i) == sparadeVärdenPåTärningskast.get(i + 1) && sparadeVärdenPåTärningskast.get(i + 1) == sparadeVärdenPåTärningskast.get(i + 2)) {
                tillfälligPokerSumma.add((Integer) sparadeVärdenPåTärningskast.get(i) + (Integer) sparadeVärdenPåTärningskast.get(i + 1) + (Integer) sparadeVärdenPåTärningskast.get(i + 2));
            }
        }

        //Hitta högsta paret
        if(tillfälligPokerSumma.size() > 0) {
            // Räkna ut högsta paret om det finns
            System.out.println("Tillfälliga par: " + tillfälligPokerSumma);
            Object högst = Collections.max(tillfälligPokerSumma);
            System.out.println("Högst: " + högst);
            sparadPokerSumma.add(högst);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

        // Om inget par återfinns i (sparade + sista kastet) tärningar så lägg till 0 i vektorn "sparadPokerSumma"
        else {
            sparadPokerSumma.add(0);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

    }

    void fyrtal() {
        System.out.println("/// Fyrtal ///");
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println("Sparade tärningar: " + sparadeVärdenPåTärningskast);
        // Sortera sparadeVärdenPåTärningskast
        Collections.sort(sparadeVärdenPåTärningskast);
        for (int i = 0; i < sparadeVärdenPåTärningskast.size() - 3; i++) {

            if (sparadeVärdenPåTärningskast.get(i) == sparadeVärdenPåTärningskast.get(i + 1) && sparadeVärdenPåTärningskast.get(i + 1) == sparadeVärdenPåTärningskast.get(i + 2)
                    && sparadeVärdenPåTärningskast.get(i + 2) == sparadeVärdenPåTärningskast.get(i + 3)) {
                sparadPokerSumma.add((Integer) sparadeVärdenPåTärningskast.get(i) + (Integer) sparadeVärdenPåTärningskast.get(i + 1) + (Integer) sparadeVärdenPåTärningskast.get(i + 2) + (Integer) sparadeVärdenPåTärningskast.get(i + 3));
                System.out.println("Sparad pokersumma: " + sparadPokerSumma);
                break;
            }

            // Om inget par återfinns i (sparade + sista kastet) tärningar så lägg till 0 i vektorn "sparadPokerSumma"
            else {
                sparadPokerSumma.add(0);
                System.out.println("Sparad pokersumma: " + sparadPokerSumma);
            }
        }
    }

    void litenStege() {
        System.out.println("/// Liten Stege ///");
        boolean stege = true;
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println(sparadeVärdenPåTärningskast);
        // Sortera sparadeVärdenPåTärningskast
        Collections.sort(sparadeVärdenPåTärningskast);
        for (int i = 0; i < sparadeVärdenPåTärningskast.size(); i++) {
            if ((Integer) sparadeVärdenPåTärningskast.get(i+1) != i+1) {
                stege = false;
                break;
            }
        }

        //Hitta högsta paret
        if(stege) {
            sparadPokerSumma.add(15);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

        // Om inget par återfinns i (sparade + sista kastet) tärningar så lägg till 0 i vektorn "sparadPokerSumma"
        else {
            sparadPokerSumma.add(0);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

    }

    void storStege() {
        System.out.println("/// Stor stege ///");
        boolean stege = true;
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println(sparadeVärdenPåTärningskast);
        // Sortera sparadeVärdenPåTärningskast
        Collections.sort(sparadeVärdenPåTärningskast);
        for (int i = 0; i < sparadeVärdenPåTärningskast.size(); i++) {
            if ((Integer) sparadeVärdenPåTärningskast.get(i+2) != i+2) {
                stege = false;
                break;
            }
        }

        //Hitta högsta paret
        if(stege) {
            sparadPokerSumma.add(20);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

        // Om inget par återfinns i (sparade + sista kastet) tärningar så lägg till 0 i vektorn "sparadPokerSumma"
        else {
            sparadPokerSumma.add(0);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

    }

    void kåk() {
        System.out.println("/// Kåk ///");
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println(sparadeVärdenPåTärningskast);
        // Sortera sparadeVärdenPåTärningskast
        Collections.sort(sparadeVärdenPåTärningskast);
        // Kolla efter par + triss
        int summa = 0;
        if (sparadeVärdenPåTärningskast.get(0) == sparadeVärdenPåTärningskast.get(1) && sparadeVärdenPåTärningskast.get(1) == sparadeVärdenPåTärningskast.get(2)
                && sparadeVärdenPåTärningskast.get(2) == sparadeVärdenPåTärningskast.get(3)) {

            for (int i = 0; i < sparadeVärdenPåTärningskast.size(); i++) {
                summa += (Integer) sparadeVärdenPåTärningskast.get(i);
            }
            sparadPokerSumma.add(summa);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

        else if (sparadeVärdenPåTärningskast.get(3) == sparadeVärdenPåTärningskast.get(4) && sparadeVärdenPåTärningskast.get(0) == sparadeVärdenPåTärningskast.get(1)
                && sparadeVärdenPåTärningskast.get(1) == sparadeVärdenPåTärningskast.get(2)) {

            for (int i = 0; i < sparadeVärdenPåTärningskast.size(); i++) {
                summa += (Integer) sparadeVärdenPåTärningskast.get(i);
            }
            sparadPokerSumma.add(summa);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

        else {
            sparadPokerSumma.add(0);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }
    }

    void chans() {
        System.out.println("/// Chans ///");
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        int summa = 0;
        for (int i = 0; i < sparadeVärdenPåTärningskast.size(); i++) {
            summa += (Integer) sparadeVärdenPåTärningskast.get(i);
        }
        sparadPokerSumma.add(summa);
        System.out.println("Sparad pokersumma: " + sparadPokerSumma);
    }

    void yatzy() {
        System.out.println("/// Yatzy ///");
        // Merge sparadetärningar + sistaKastet
        sparadeVärdenPåTärningskast.addAll(värdenPåTärningskast);
        System.out.println(sparadeVärdenPåTärningskast);
        // Kolla om alla är lika
        int summa = 0;
        if (sparadeVärdenPåTärningskast.get(0) == sparadeVärdenPåTärningskast.get(1) && sparadeVärdenPåTärningskast.get(1) == sparadeVärdenPåTärningskast.get(2)
                && sparadeVärdenPåTärningskast.get(2) == sparadeVärdenPåTärningskast.get(3) && sparadeVärdenPåTärningskast.get(3) == sparadeVärdenPåTärningskast.get(4)) {

            for (int i = 0; i < sparadeVärdenPåTärningskast.size(); i++) {
                summa =+ (Integer) sparadeVärdenPåTärningskast.get(i);
            }
            sparadPokerSumma.add(summa);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }

        else {
            sparadPokerSumma.add(0);
            System.out.println("Sparad pokersumma: " + sparadPokerSumma);
        }
    }

    void spelaSiffror() {

        for (int i = 0; i < 6; i++) {
            skapaTärningar();
            for (int j = 0; j < 3; j++) {
                kastaTärningar();
                siffrorKastSumma(i+1); //Spara automatiskt den siffran som man ska ha
                //sparaTärning();
            }
            sparaKastSumma();
            siffrorKastSumma = 0;
            antalTärningar = 5;
        }
    }


    void spelaPoker() {

        for (int i = 0; i < 9; i++) {
            skapaTärningar();
            // Spelare har max 3 kast
            for (int j = 0; j < 3; j++) {
                kastaTärningar();
                /*
                värdenPåTärningskast.add(1);
                värdenPåTärningskast.add(1);
                värdenPåTärningskast.add(1);
                värdenPåTärningskast.add(1);
                värdenPåTärningskast.add(1);
                System.out.println(värdenPåTärningskast);
                */
                //siffrorKastSumma(i+1); //Spara automatiskt den siffran som man ska ha
                sparaTärning();

            }
            if(i == 0) {
                ettPar();
                rensaVektorer();
            }

            if(i == 1) {
                tvåPar();
                rensaVektorer();
            }

            if(i == 2) {
                triss();
                rensaVektorer();
            }

            if(i == 3) {
                fyrtal();
                rensaVektorer();
            }

            if(i == 4) {
                litenStege();
                rensaVektorer();
            }

            if(i == 5) {
                storStege();
                rensaVektorer();
            }

            if(i == 6) {
                kåk();
                rensaVektorer();
            }

            if(i == 7) {
                chans();
                rensaVektorer();
            }

            if(i == 8) {
                yatzy();
                rensaVektorer();
            }

        }


    }

}




