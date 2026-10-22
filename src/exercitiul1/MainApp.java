package exercitiul1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicatia principala pentru gestiunea echipamentelor electronice.
 */

//modificam case 3 si 4 cu getClass in moduri diferite, adica sa fie diferite intre ele si diferite de case 2
//la case 7 folosim pattern matching instance of ptr a nu mai face cast

public class MainApp {
    /**
     * Citeste echipamentele din fisierul text.
     *
     * @param fis calea catre fisier
     * @param echipamente lista in care sunt adaugate echipamentele
     * @throws java.io.FileNotFoundException daca fisierul nu exista
     */
    static void citesteText(String fis, List<Echipament> echipamente) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fis));
        int nrLinie = 0;
        while (scanner.hasNextLine()) {
            String linie = scanner.nextLine().trim();
            nrLinie++;
            if (linie.isEmpty()) {
                continue;
            }
            String[] c = linie.split(";");
            try {
                String denumire = c[0].trim();
                int nrInv = Integer.parseInt(c[1].trim());
                double pret = Double.parseDouble(c[2].trim());
                String zonaMag = c[3].trim();
                StareEchipament stare = StareEchipament.valueOf(c[4].trim().toUpperCase());
                String tip = c[5].trim().toLowerCase();
                switch (tip) {
                    case "imprimanta":
                        echipamente.add(new Imprimanta(
                                denumire, nrInv, pret, zonaMag, stare,
                                Integer.parseInt(c[6].trim()),
                                c[7].trim(),
                                Integer.parseInt(c[8].trim()),
                                ModTiparire.valueOf(c[9].trim().toUpperCase())
                        ));
                        break;
                    case "copiator":
                        echipamente.add(new Copiator(
                                denumire, nrInv, pret, zonaMag, stare,
                                Integer.parseInt(c[6].trim()),
                                FormatCopiere.valueOf(c[7].trim().toUpperCase())
                        ));
                        break;
                    case "sistem de calcul":
                        echipamente.add(new SistemCalcul(
                                denumire, nrInv, pret, zonaMag, stare,
                                c[6].trim(),
                                Double.parseDouble(c[7].trim()),
                                Double.parseDouble(c[8].trim()),
                                SistemOperare.valueOf(c[9].trim().toUpperCase())
                        ));
                        break;
                    default:
                        System.out.println("Tip necunoscut la linia " + nrLinie);
                }
            } catch (RuntimeException e) {
                System.out.println("Linie invalida " + nrLinie + ": " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Cauta un echipament dupa numarul de inventar.
     *
     * @param echipamente lista echipamentelor
     * @param nrInv       numarul de inventar cautat
     * @return echipamentul gasit sau null
     */
    static Echipament cauta(List<Echipament> echipamente, int nrInv) {
        for (Echipament e : echipamente) {
            if (e.getNrInv() == nrInv) {
                return e;
            }
        }
        return null;
    }

    /**
     * Serializeaza un obiect intr-un fisier binar.
     *
     * @param o   obiectul de serializat
     * @param fis numele fisierului
     */
    static void scrie(Object o, String fis) {
        try {
            FileOutputStream f = new FileOutputStream(fis);
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(o);
            oos.close();
            f.close();
            System.out.println("Datele au fost salvate in " + fis);
        } catch (IOException e) {
            System.out.println("Eroare la salvare: " + e.getMessage());
        }
    }

    /**
     * Deserializeaza obiectul dintr-un fisier binar.
     *
     * @param fis numele fisierului
     * @return obiectul citit sau null
     */
    static Object citeste(String fis) {
        try {
            FileInputStream f = new FileInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(f);
            Object o = ois.readObject();
            ois.close();
            f.close();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eroare la incarcare: " + e.getMessage());
        }
        return null;
    }

    /**
     * Afiseaza meniul aplicatiei.
     */
    static void meniu() {
        System.out.println("\n  Meniu echipamente:  ");
        System.out.println("1. Afisarea tuturor echipamentelor");
        System.out.println("2. Afisare imprimante");
        System.out.println("3. Afisare copiatoare");
        System.out.println("4. Afisare sisteme de calcul");
        System.out.println("5. Modificare stare echipament");
        System.out.println("6. Setare mod tiparire imprimanta");
        System.out.println("7. Setare format copiere copiator");
        System.out.println("8. Instalare sistem de operare");
        System.out.println("9. Afisare echipamente vandute");
        System.out.println("10. Serializare in echip.bin");
        System.out.println("11. Deserializare din echip.bin");
        System.out.println("0. Iesire");
        System.out.print("Optiunea: ");
    }

    /**
     * Metoda principala a aplicatiei.
     */
    public static void main(String[] args) {
        //O singura colectie pentru toate echipamentele
        List<Echipament> echipamente = new ArrayList<Echipament>();
        try {
            citesteText("src/exercitiul1/echipamente.txt", echipamente);
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a gasit fisierul echipamente.txt");
            return;
        }


        System.out.println("Au fost citite " + echipamente.size() + " echipamente.");
        Scanner tastatura = new Scanner(System.in);
        int optiune = -1;
        while (optiune != 0) {
            meniu();
            try {
                optiune = Integer.parseInt(tastatura.nextLine());
                switch (optiune) {
                    // Afisarea tuturor echipamentelor
                    case 1:
                        for (Echipament e : echipamente) {
                            System.out.println(e);
                        }
                        break;
                    // Afisarea imprimantelor
                    case 2:
                        for (Echipament e : echipamente) {
                            if (e instanceof Imprimanta) {
                                System.out.println(e);
                            }
                        }
                        break;
                    // Afisarea copiatoarelor
                    case 3:
                        for (Echipament e : echipamente) {
                            if (e.getClass() == Copiator.class) {
                                System.out.println(e);
                            }
                        }
                        break;
                    // Afisarea sistemelor de calcul
                    case 4:
                        for (Echipament e : echipamente) {
                            if (e.getClass().getSimpleName().equals("SistemCalcul")) {
                                System.out.println(e);
                            }
                        }
                        break;

                    // Modificarea starii unui echipament
                    case 5: {
                        System.out.print("Nr. inventar: ");
                        int nr = Integer.parseInt(tastatura.nextLine());
                        Echipament e = cauta(echipamente, nr);
                        if (e == null) {
                            System.out.println("Echipamentul nu exista.");
                        } else {
                            System.out.print(
                                    "Stare (ACHIZITIONAT/EXPUS/VANDUT): ");
                            StareEchipament stare =
                                    StareEchipament.valueOf(
                                            tastatura.nextLine()
                                                    .trim().toUpperCase());
                            e.setStare(stare);
                            System.out.println("Starea a fost modificata.");
                        }
                        break;
                    }
                    // Modificarea modului de tiparire
                    case 6: {
                        System.out.print("Nr. inventar imprimanta: ");
                        int nr = Integer.parseInt(tastatura.nextLine());
                        Echipament e = cauta(echipamente, nr);
                        if (e instanceof Imprimanta) {
                            Imprimanta imp = (Imprimanta) e;
                            System.out.print("Mod (COLOR/ALB_NEGRU): ");
                            ModTiparire mod = ModTiparire.valueOf(
                                    tastatura.nextLine()
                                            .trim().toUpperCase());
                            imp.setModTiparire(mod);
                            System.out.println("Modul a fost modificat.");
                        } else {
                            System.out.println(
                                    "Nu exista aceasta imprimanta.");
                        }
                        break;
                    }
                    // Modificarea formatului de copiere
                    case 7: {
                        System.out.print("Nr. inventar copiator: ");
                        int nr=Integer.parseInt(tastatura.nextLine());
                        Echipament e=cauta(echipamente, nr);
                        if (e instanceof Copiator cop) {
                            //Copiator cop = (Copiator) e;
                            System.out.print("Format (A3/A4): ");
                            FormatCopiere format =
                                    FormatCopiere.valueOf(
                                            tastatura.nextLine()
                                                    .trim().toUpperCase());
                            cop.setFormatCopiere(format);
                            System.out.println("Formatul a fost modificat.");
                        } else {
                            System.out.println("Nu exista acest copiator.");
                        }
                        break;
                    }
                    // Instalarea unui sistem de operare
                    case 8: {
                        System.out.print("Nr. inventar sistem calcul: ");
                        int nr = Integer.parseInt(tastatura.nextLine());
                        Echipament e = cauta(echipamente, nr);
                        if (e instanceof SistemCalcul) {
                            SistemCalcul pc = (SistemCalcul) e;
                            System.out.print(
                                    "Sistem operare (WINDOWS/LINUX): ");
                            SistemOperare so = SistemOperare.valueOf(
                                    tastatura.nextLine()
                                            .trim().toUpperCase());
                            pc.instaleazaSistemOperare(so);
                            System.out.println(
                                    "Sistemul de operare a fost instalat.");
                        } else {
                            System.out.println(
                                    "Nu exista acest sistem de calcul.");
                        }
                        break;
                    }
                    // Afisarea echipamentelor vandute
                    case 9:
                        for (Echipament e : echipamente) {
                            if (e.getStare() == StareEchipament.VANDUT) {
                                System.out.println(e);
                            }
                        }
                        break;
                    // Serializarea listei
                    case 10:
                        scrie(echipamente, "echip.bin");
                        break;
                    // Deserializarea listei
                    case 11: {
                        Object o = citeste("echip.bin");
                        if (o instanceof List) {
                            @SuppressWarnings("unchecked")
                            List<Echipament> listaCitita =
                                    (List<Echipament>) o;
                            echipamente = listaCitita;
                            System.out.println(
                                    "Datele au fost incarcate din echip.bin");
                        }
                        break;
                    }
                    case 0:
                        System.out.println("La revedere!");
                        break;
                    default:
                        System.out.println("Optiune invalida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(
                        "Valoare invalida. Incercati din nou.");
            }
        }
        tastatura.close();
    }
}
