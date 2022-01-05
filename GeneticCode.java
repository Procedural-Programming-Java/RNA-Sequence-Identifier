/**
 * @author Yaaqov Shkifati
 * @since 12/05/19
 * @version 1.0 Description: Ribonucleic acid, or RNA is one of the three major
 * biological macromolecules that are essential for all known forms of life
 * (along with DNA and proteins). A central tenet of molecular biology states
 * that the flow of genetic information in a cell is from DNA through RNA to
 * proteins: “DNA makes RNA makes protein”. Proteins are the workhorses of the
 * cell; they play leading roles in the cell as enzymes, as structural
 * components, and in cell signaling, to name just a few. DNA(deoxyribonucleic
 * acid) is considered the “blueprint” of the cell; it carries all of the
 * genetic information required for the cell to grow, to take in nutrients, and
 * to propagate. RNA–in this role–is the “DNA photocopy” of the cell. When the
 * cell needs to produce a certain protein, it activates the protein’s gene–the
 * portion of DNA that codes for that protein–and produces multiple copies of
 * that piece of DNA in the form of messenger RNA, or mRNA. The multiple copies
 * of mRNA are then used to translate the genetic code into protein through the
 * action of the cell’s protein manufacturing machinery, the ribosomes. Thus,
 * RNA expands the quantity of a given protein that can be made at one time from
 * one given gene, and it provides an important control point for regulating
 * when and how much protein gets made. Therefore, we are Writing a program that
 * would indicate the codon within the RNA strip and returning the amino acid.
 * This was a challenging program but, it help me improve on String manipulation
 * and a better understanding of a new way on how to write different methods.
*
 */
package geneticcode;

import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;

public class GeneticCode {

    public static PrintStream dna;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new File("Data.txt"));
        dna = new PrintStream(" RNAfile.txt");

        final int MAXNUMBER = 66;
        String[] codons = new String[MAXNUMBER];
        String[] aminoAcid = new String[MAXNUMBER];
        String RNA, found, fixed;
        int count, i;

        count = readRNACodonTable(codons, aminoAcid);

        sortRNA(codons, aminoAcid, count);

        RNA = sc.next();

        if (isValidRNA(RNA)) {

            for (i = 0; i < RNA.length() - 2; i++) {

                if (RNA.charAt(i) == 'A' && RNA.charAt(i + 1) == 'U'
                        && RNA.charAt(i + 2) == 'G') {

                    fixed = RNA.substring(i, i + 3);

                    while (!(fixed.equals("UGA") || fixed.equals("UAG")
                            || fixed.equals("UAA"))) {

                        found = codonLookup(codons, aminoAcid, fixed, count);

                        dna.printf("%s", found);

                        i = i + 3;

                        fixed = RNA.substring(i, i + 3);

                    }
                    dna.println(" # Protein");
                }
            }
        }
    }
//  Reading from a file to populate codon and aminoAcid Arrays

    public static int readRNACodonTable(String[] codons, String[] aminoAcid)
            throws Exception {

        Scanner sc = new Scanner(new File("Code.txt"));

        int count = 0;
        while (sc.hasNext()) {
            codons[count] = sc.next();
            aminoAcid[count] = sc.next();
            count++;

        }
        return count;
    }
// This method is to sort the codon Array in codon order .

    public static void sortRNA(String[] codons, String[] aminoAcid, int n) {
        int j, pass;
        String temp, hold;
        boolean switched = true;

        for (pass = 0; pass < n - 1 && switched; pass++) {
            switched = false;

            for (j = 0; j < n - pass - 1; j++) {

                if (codons[j].compareToIgnoreCase(codons[j + 1]) > 0) {

                    switched = true;
                    hold = aminoAcid[j];
                    aminoAcid[j] = aminoAcid[j + 1];
                    aminoAcid[j + 1] = hold;
                    temp = codons[j];
                    codons[j] = codons[j + 1];
                    codons[j + 1] = temp;

                }
            }
        }
    }
// Sequential search of 3 charcters from condon Array and returns the Amino acid

    public static String codonLookup(String[] codons, String[] aminoAcid,
            String item, int count) throws Exception {

        int i;

        for (i = 0; i < count; i++) {
            if (codons[i].equals(item)) {
                return aminoAcid[i];
            }

        }

        return aminoAcid[i];
    }
// validating wether the RNA is true or not.

    public static boolean isValidRNA(String RNA) {
        boolean isNotProtien = true;
        char p;

        for (int i = 0; i < RNA.length(); i++) {

            p = RNA.charAt(i);

            if (!(p == 'A' || p == 'C' || p == 'G' || p == 'U')) {

                isNotProtien = false;

            }

        }

        return isNotProtien;

    }
}
