package EXAMEN;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class Test {
    static class Point {
        int x, y;
    }

    static class EnsembleEntier {
        int[] tab; // tableau contenant les éléments de l'ensemble
        int nbElements; // nombre d'éléments actuellement présents dans l'ensemble
    }

    // échange le contenu des 2 points passés en paramètre
    void permuterPoints(Point a, Point b) {
        Point tmp = new Point();
        tmp.x = a.x;
        tmp.y = a.y;
        a.x = b.x;
        a.y = b.y;
        b.x = tmp.x;
        b.y = tmp.y;
    }

    // retourne la position du plus grand élément dans le tableau
    int indiceMax(int[] tab) {
        int max = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
            }
        }
        return max;
    }

    // retourne la position dans le tableau tab du premier élement égal à "element"
    int position(int[] tab, int element) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == element) {
                return tab[i];
            }
        }
        return -1;
    }

    // retourne le nombre d'occurrences du caractère c dans la chaîne s
    int nbOcc(String s, char c) {
        int nbOccurrences = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                nbOccurrences++;
            }
        }

        return nbOccurrences;
    }

    // remplace dans la chaîne s chaque occurrence du caractère c par la chaîne "remplacement"
    String replace(String s, char c, String remplacement) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                builder.append(remplacement);
            } else {
                builder.append(s.charAt(i));
            }
        }

        return builder.toString();
    }

    // demande à l'utilisateur de saisir une note au clavier. Il faut garantir que la note est comprise entre 0 et 20 (et recommencer la saisie sinon).
    float lireNote() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez saisir une note entre 0 et 20");

        if (scanner.hasNextFloat()) {
            float in = scanner.nextFloat();

            if (in >= 0 && in <= 20) {
                return in;
            } else {
                return lireNote();
            }
        } else {
            return lireNote();
        }
    }

    // retourne le nombre de mots dans la chaîne s (les mots sont séparés par un ou plusieurs caractère espace)
    int nombreMots(String s) {
        return s.split("\\s+").length;
    }

    // retourne le nombre d'espaces dans la chaîne s
    int nombreEspace(String s) {
        int nbEspaces = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                nbEspaces++;
            }
        }

        return nbEspaces;
    }

    // ajouter un élément à l'ensemble d'entiers
    void ajouter(EnsembleEntier set, int n) {
        set.nbElements++;
        int[] newTab = new int[set.tab.length + 1];

        for (int i = 0; i < set.tab.length; i++) {
            newTab[i] = set.tab[i];
        }

        newTab[set.tab.length] = n;

        set.tab = newTab;
    }

    // retirer un élément à l'ensemble d'entiers
    void retirer(EnsembleEntier set, int n) {
        set.nbElements--;

        int[] newTab = new int[set.tab.length - 1];

        for (int i = 0; i < set.tab.length; i++) {
            if (set.tab[i] != n) {
                newTab[i] = set.tab[i];
            } else {
                for (int j = i + 1; j < set.tab.length; j++) {
                    newTab[j - 1] = set.tab[j];
                }
                break;
            }
        }

        set.tab = newTab;
    }

    // retourne la position de la première occurrence de aiguille dans la chaîne meule (-1 si non trouvé)
    int position(String meule, String aiguille) {
        int position = -1;
        for (int i = 0; i < meule.length(); i++) {
            for (int j = 0; j < aiguille.length(); j++) {
                if (!(meule.charAt(i + j) == aiguille.charAt(j))) {
                    break;
                }
                return i;
            }
        }
        return position;
    }

    public static void main(String[] args) {
        Test test = new Test();
        Point a = new Point();
        a.x = 1;
        a.y = 2;
        Point b = new Point();
        b.x = 3;
        b.y = 4;

        System.out.println("(" + a.x + " " + a.y + ")" + "(" + b.x + " " + b.y + ")");
        test.permuterPoints(a, b);
        System.out.println("(" + a.x + " " + a.y + ")" + "(" + b.x + " " + b.y + ")");

        int[] tab = new int[]{1, 2, 3, 4, 5, 6};

        System.out.println(test.indiceMax(tab));

        System.out.println(test.position(tab, 1));

        String str = "Bonjour";

        System.out.println(test.nbOcc(str, 'o'));

        System.out.println(test.replace(str, 'o', "a"));

        //System.out.println(test.lireNote());

        String mot = "Bonjour    je suis Lucas";
        System.out.println(test.nombreMots(mot));
        System.out.println(test.nombreEspace(mot));

        EnsembleEntier ensemble = new EnsembleEntier();
        ensemble.tab = tab;
        ensemble.nbElements = tab.length;

        System.out.println(Arrays.toString(ensemble.tab));

        test.ajouter(ensemble, 7);

        System.out.println(Arrays.toString(ensemble.tab));

        test.retirer(ensemble, 2);

        System.out.println(Arrays.toString(ensemble.tab));

        test.retirer(ensemble, 5);

        System.out.println(Arrays.toString(ensemble.tab));


        String meule = "Bonjour";
        String aiguille = "nj";
        System.out.println(test.position(meule, aiguille));
    }
}