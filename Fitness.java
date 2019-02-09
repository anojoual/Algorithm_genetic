/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package fitness;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import static java.lang.Math.random;

import java.util.Arrays;

import java.util.Collections;

import java.util.IntSummaryStatistics;

import java.util.LinkedList;

import java.util.Random;



/**

 *

 * @author PC

 */

public class Fitness {



    // generer aleatoirement les individus (String de taille 10 ) a  partir de la list des characteaires 

    public static String generator (LinkedList possibblevalues ){

    

        String identifiant = "";

        

        for (int i = 0 ; i < 10 ; i++){

        

            Random generator = new Random();

            int randomIndex = generator.nextInt(possibblevalues.size());

            

            identifiant = identifiant + possibblevalues.get(randomIndex) + "";

        }

        

        return identifiant;

        

    }

    

    

    // retourne la valeur du fitness pour chaque String testé

    

    public static String calculateFitness(String value) throws IOException{



        String stringFitness = "";



        String caractere;

        Process process = new ProcessBuilder("C:\\Users\\PC\\Desktop\\Master Intelligence Artificielle\\ibi\\ibi_2017-2018_fitness_windows.exe","6",value).start();



          BufferedReader input =  new BufferedReader (new InputStreamReader(process.getInputStream()));  

        

        while ((caractere = input.readLine()) != null) {  



           stringFitness += caractere;  



        }        

        String[] parts = stringFitness.split("	");

        



        return parts[1];



    }

    

    public static LinkedList Population (LinkedList possiblevalues , int numberIndividus) {

    

        LinkedList popula = new LinkedList();

        

            for (int j = 0 ; j < numberIndividus ; j++){

        

            String id = generator(possiblevalues);

            popula.add(id);



        } 

        

        return popula;

    }

    

    public static LinkedList BestIndividus (LinkedList<String> population ) throws IOException{

    

        

        LinkedList best = new LinkedList();

        LinkedList IndividusFitness = new LinkedList();



            for (int j = 0 ; j < population.size() ; j++){

        

            

            String fitness = calculateFitness(population.get(j));

            IndividusFitness.add(Double.parseDouble(fitness));

           



        }    

          

            LinkedList orig = (LinkedList) IndividusFitness.clone() ;

            Collections.sort(IndividusFitness);

            LinkedList bestfitness = new LinkedList();

            for (int i = IndividusFitness.size() -1  ; i > IndividusFitness.size() - 11 ; i--){

            

                bestfitness.add(IndividusFitness.get(i));

            }

            

            for (int k = 0 ; k < bestfitness.size() ; k++){

            

                for (int t = 0 ; t < orig.size() ; t++){

                

                    if (bestfitness.get(k) == orig.get(t)) {

                    

                        best.add(population.get(t));

                    }

                }

            }

            

            /*System.out.println(" Individus  ="+population);

            System.out.println(" fitness ="+orig);

            System.out.println(" bestfitness ="+bestfitness );

            

            System.out.println(" best Individus ="+best);

            

                */

                return best;

    }

    

    public static String replaceCharAt(String s, int pos, char c) {



  return s.substring(0,pos) + c + s.substring(pos+1);



}

    

    // mutation

    public static LinkedList mutation (LinkedList<String> Best , LinkedList<String> values){

    

        LinkedList ind = new LinkedList();

        for (int i = 0 ; i < Best.size() ; i++){

        

            String individu = Best.get(i);

            

            for (int j = 0 ; j < individu.length() ; j++){

            

                for (int k = 0 ; k < values.size() ; k++){

                    String car = individu.charAt(j)+"";

                    if (car.equals(values.get(k))) {

                        

                        if (car.equals("_")) {

                        String indiv1 = replaceCharAt(individu,j, 'Z');

                        String indiv2 = replaceCharAt(individu,j, '0');

                        

                        ind.add(indiv1);

                        ind.add(indiv2);

                            

                        }

                        else if (car.equals("0")) {

                        

                        String indiv1 = replaceCharAt(individu,j, '_');

                        String indiv2 = replaceCharAt(individu,j,  '1');

                        

                        ind.add(indiv1);

                        ind.add(indiv2);

                        }

                        else {

                         

                        String indiv1 = replaceCharAt(individu,j,  values.get(k-1).charAt(0));

                        String indiv2 =replaceCharAt(individu,j,  values.get(k+1).charAt(0));

                        

                        ind.add(indiv1);

                        ind.add(indiv2);

                        }

                    }

                }

            }

            

        }

       // System.out.println(" individu ="+ind);

        return ind ;

    }

    

    

    

    

    

    public static LinkedList mutation2 (LinkedList<String> pop , LinkedList<String> values) {

    

        LinkedList ind = new LinkedList();

        

        for (int i = 0 ; i < pop.size() ; i++){

        

            String indiv = pop.get(i);

            Random generator = new Random();

            int randomIndex = generator.nextInt(indiv.length());

            String car = indiv.charAt(randomIndex)+"";

            for (int k = 0 ; k < values.size() ; k++){

                    if (car.equals(values.get(k))) {

                        if (car.equals("_")) {

                        String indiv1 = replaceCharAt(indiv,randomIndex, 'Z');

                        String indiv2 = replaceCharAt(indiv,randomIndex, '0');

                        

                        ind.add(indiv1);

                        ind.add(indiv2);

                            

                        }

                        else if (car.equals("0")) {

                        

                        String indiv1 = replaceCharAt(indiv,randomIndex, '_');

                        String indiv2 = replaceCharAt(indiv,randomIndex,  '1');

                        

                        ind.add(indiv1);

                        ind.add(indiv2);

                        }

                        else {

                         

                        String indiv1 = replaceCharAt(indiv,randomIndex,  values.get(k-1).charAt(0));

                        String indiv2 =replaceCharAt(indiv,randomIndex,  values.get(k+1).charAt(0));

                        

                        ind.add(indiv1);

                        ind.add(indiv2);

                        }

                    }

                    }

        }

        

        //System.out.println(" individu ="+ind);

        

        return ind;

    }

    

    public static String crossover (String ind1, String ind2, double fit1, double fit2) {



       String cross = "";



       double somme = fit1 + fit2;



       



       for (int i = 0; i < ind1.length(); i++) {



           Double random = new Random().nextDouble() * somme;



           if (random < fit1) {



               cross += ind1.charAt(i);



           } else {



               cross += ind2.charAt(i);



           }



       }      



       



       return cross;



   }

    

    public static void RecursiveMutation (LinkedList<String> Best , LinkedList<String> values) {

    

        LinkedList best = mutation2(Best, values);

        int cnt = 0 ;

        

        if (cnt == 20){

                

        }

        else {

        

            mutation2(best, values);

        }

        

    }

    

    /*public static String crossover (String ind1 , String ind2 , double fit1 , double fit2){

    

        String crossover = "" ;

        int indicecrossover = (int) ((fit2/fit1) * ind1.length());

        

        for (int i = 0 ; i < ind1.length() ; i++){

        

          if (i < indicecrossover ) {

          

              crossover = crossover +  ind1.charAt(i);

          }  

          else {

          

              crossover = crossover + ind2.charAt(i);

          }

        }

        

        return crossover;

    }*/

    

    

    

   public  static String[] split1(String string)

    {

        

        char[] chars = string.toCharArray();

        String[] strings = new String[chars.length];

        

        for (int i = 0; i < chars.length; i++)

        {

            strings[i] = String.valueOf(chars[i]);

        }

        return strings;

    }

   

  /* public static LinkedList newChar (LinkedList best ) {

   

   LinkedList chars = new LinkedList();

   

      for (int i = 0 ; i < best.size() ; i++)  {

      

          String[] cha = split1((String) best.get(i));

          for (int j = 0 ; j < cha.length ; j++){

          

              chars.add(cha[j]);

          }

      }

      

      for (int k = 0 ; k < chars.size() ; k++){

         int drp =0; 

          for ( int t = k+1 ; t < chars.size() ; t++){

              if (chars.get(k).equals(chars.get(t))){

                 

                  drp = 1 ;

                  

              }

                                

                  if (drp ==1){

                  

                      chars.remove(t);

                  }

          }

      }

       System.out.println(" chars ="+chars);

   return chars ;

   

   }*/

   



    

    

    

    public static double maxListe (LinkedList<String> liste) throws IOException {

        double max = 0.0;

        for (int i = 0; i < liste.size(); i++) {

            if (max < Double.parseDouble(calculateFitness(liste.get(i)))) {

                max = Double.parseDouble(calculateFitness(liste.get(i)));

            }

        }

        return max;

    }



    public static double moyenneListe (LinkedList<String> liste) throws IOException {

        double moyenne = 0.0;

        for (int i = 0; i < liste.size(); i++) {

            moyenne += Double.parseDouble(calculateFitness(liste.get(i)));

        }

        moyenne /= liste.size();

        return moyenne;

    }

    

    

    public static void main(String[] args) throws IOException {

        LinkedList possibleValues = new LinkedList();

        String chain = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_";

        String [] val = split1(chain);

        

        for (int i = 0 ; i < val.length ; i++) {

            possibleValues.add(val[i]);

        }

        

        double globalMax = 0.0;

        String solution = "";

        LinkedList fitnessMax = new LinkedList();

        LinkedList fitnessMoy = new LinkedList();

        LinkedList<String> best = new LinkedList();

        

        LinkedList pop = Population(possibleValues, 110);

       

        System.out.println("Population génération 1");

        System.out.println(pop);

        

        for (int i = 0; i < 100; i++) {

            best = BestIndividus(pop);

            

double localMax = maxListe(best);

           

           if (globalMax < localMax) {

               globalMax = localMax;

               solution = best.get(0);

           }

           

           if (localMax > 0.999999) {

               System.out.println("Solution trouvée après " + i + " générations");

               break;

           }



           fitnessMax.add(localMax);

           fitnessMoy.add(moyenneListe(pop));

            

            pop = new LinkedList();

            pop = mutation2(best, possibleValues);

            for (int j = 0; j < 4; j++) {

                for (int k = j+1; k < 5; k++) {

                    pop.add(crossover(best.get(j), best.get(k), Double.parseDouble(calculateFitness(best.get(j))), Double.parseDouble(calculateFitness(best.get(k)))));

                }

            }

        }

        

        System.out.println("Meilleure solution \"" + solution + "\" avec une fitness de " + globalMax);

        

        System.out.println("Meilleures fitness pour chaque génération : " + fitnessMax);

        System.out.println("Fitness moyenne pour chaque génération : " + fitnessMoy);

    }

    

}