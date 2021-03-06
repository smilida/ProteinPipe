/***********************************************************************

	This file is part of KEEL-software, the Data Mining tool for regression, 
	classification, clustering, pattern mining and so on.

	Copyright (C) 2004-2010
	
	F. Herrera (herrera@decsai.ugr.es)
    L. Sánchez (luciano@uniovi.es)
    J. Alcalá-Fdez (jalcala@decsai.ugr.es)
    S. García (sglopez@ujaen.es)
    A. Fernández (alberto.fernandez@ujaen.es)
    J. Luengo (julianlm@decsai.ugr.es)

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see http://www.gnu.org/licenses/
  
**********************************************************************/


package keel.Algorithms.ImbalancedClassification.Resampling;

import keel.Algorithms.Preprocess.Basic.*;
import keel.Dataset.Attribute;
import keel.Dataset.Attributes;
import keel.Dataset.Instance;

import org.core.*;

import java.util.StringTokenizer;

/**
 * <p>
 * File: SMOTE_TomekLinks.java
 * </p>
 *
 * The SMOTE Tomek Links algorithm is an oversampling method used to deal with
 * the imbalanced problem.
 *
 * @author Written by Salvador Garcia Lopez (University of Granada) 30/03/2006
 * @author Modified by Victoria Lopez Morales (University of Granada) 21/09/2010 
 * @version 0.1
 * @since JDK1.5
 *
 */
public class  SMOTE_TomekLinks extends Metodo {
  /**
   * <p>
   * The SMOTE Tomek Links algorithm is an oversampling method used to deal with
   * the imbalanced problem.
   * </p>
   */

  /*Own parameters of the algorithm*/
  private long semilla;
  private int kSMOTE;
  private int ASMO;
  private boolean balance;
  private double smoting;

  /**
   * <p>
   * Constructor of the class. It configures the execution of the algorithm by
   * reading the configuration script that indicates the parameters that are
   * going to be used.
   * </p>
   *
   * @param ficheroScript   Name of the configuration script that indicates the
   * parameters that are going to be used during the execution of the algorithm
   */
  public SMOTE_TomekLinks (String ficheroScript) {
    super (ficheroScript);
  }

  /**
   * <p>
   * The main method of the class that includes the operations of the algorithm.
   * It includes all the operations that the algorithm has and finishes when it
   * writes the output information into files.
   * </p>
   */
  public void run () {

	  int nPos = 0;
	  int nNeg = 0;
	  int i, j, l, m;
	  int tmp;
	  int posID, negID;
	  int positives[];
	  double conjS[][];
	  double conjR[][];
	  int conjN[][];
	  boolean conjM[][];
	  int clasesS[];
	  double genS[][];
	  double genR[][];
	  int genN[][];
	  boolean genM[][];
	  int clasesGen[];
	  int tamS;
	  int pos;
	  int neighbors[][];
	  int nn;
	  boolean marcas[];
	  int nSel = 0;
	  double conjS2[][];
	  double conjR2[][];
	  int conjN2[][];
	  boolean conjM2[][];
	  int clasesS2[];
	  double minDist, dist;

    long tiempo = System.currentTimeMillis();

    /*SMOTE PART*/

    /*Count of number of positive and negative examples*/
    for (i=0; i<clasesTrain.length; i++) {
      if (clasesTrain[i] == 0)
        nPos++;
      else
        nNeg++;
    }
    if (nPos > nNeg) {
      tmp = nPos;
      nPos = nNeg;
      nNeg = tmp;
      posID = 1;
      negID = 0;
    } else {
      posID = 0;
      negID = 1;
    }

    /*Localize the positive instances*/
    positives = new int[nPos];
    for (i=0, j=0; i<clasesTrain.length; i++) {
      if (clasesTrain[i] == posID) {
        positives[j] = i;
        j++;
      }
    }

    /*Randomize the instance presentation*/
    Randomize.setSeed (semilla);
    for (i=0; i<positives.length; i++) {
      tmp = positives[i];
      pos = Randomize.Randint(0,positives.length-1);
      positives[i] = positives[pos];
      positives[pos] = tmp;
    }

    /*Obtain k-nearest neighbors of each positive instance*/
    neighbors = new int[positives.length][kSMOTE];
    for (i=0; i<positives.length; i++) {
    	switch (ASMO) {
        	case 0:
        		KNN.evaluacionKNN2 (kSMOTE, datosTrain, realTrain, nominalTrain, nulosTrain, clasesTrain, datosTrain[positives[i]], realTrain[positives[i]], nominalTrain[positives[i]], nulosTrain[positives[i]], 2, distanceEu, neighbors[i]);
        		break;
        	case 1:
        		evaluationKNNClass (kSMOTE, datosTrain, realTrain, nominalTrain, nulosTrain, clasesTrain, datosTrain[positives[i]], realTrain[positives[i]], nominalTrain[positives[i]], nulosTrain[positives[i]], 2, distanceEu, neighbors[i],posID);
        		break;
        	case 2:
        		evaluationKNNClass (kSMOTE, datosTrain, realTrain, nominalTrain, nulosTrain, clasesTrain, datosTrain[positives[i]], realTrain[positives[i]], nominalTrain[positives[i]], nulosTrain[positives[i]], 2, distanceEu, neighbors[i],negID);
        		break;
    	}
    }

    /*Interpolation of the minority instances*/
    if (balance) {
    	genS = new double[nNeg-nPos][datosTrain[0].length];
    	genR = new double[nNeg-nPos][datosTrain[0].length];
    	genN = new int[nNeg-nPos][datosTrain[0].length];
    	genM = new boolean[nNeg-nPos][datosTrain[0].length];
    	clasesGen = new int[nNeg-nPos];
    } else {
    	genS = new double[(int)(nPos*smoting)][datosTrain[0].length];
    	genR = new double[(int)(nPos*smoting)][datosTrain[0].length];
    	genN = new int[(int)(nPos*smoting)][datosTrain[0].length];
    	genM = new boolean[(int)(nPos*smoting)][datosTrain[0].length];
    	clasesGen = new int[(int)(nPos*smoting)];
    }
    for (i=0; i<genS.length; i++) {
    	clasesGen[i] = posID;
    	nn = Randomize.Randint(0,kSMOTE-1);
    	interpolate (realTrain[positives[i%positives.length]],realTrain[neighbors[i%positives.length][nn]],nominalTrain[positives[i%positives.length]],nominalTrain[neighbors[i%positives.length][nn]],nulosTrain[positives[i%positives.length]],nulosTrain[neighbors[i%positives.length][nn]],genS[i],genR[i],genN[i],genM[i]);
    }

	if (balance) {
		tamS = 2*nNeg;
	} else {
		tamS = nNeg + nPos + (int)(nPos*smoting);
	}		    
   /*Construction of the S set from the previous vector S*/
    conjS = new double[tamS][datosTrain[0].length];
    conjR = new double[tamS][datosTrain[0].length];
    conjN = new int[tamS][datosTrain[0].length];
    conjM = new boolean[tamS][datosTrain[0].length];
    clasesS = new int[tamS];
    for (j=0; j<datosTrain.length; j++) {
      for (l=0; l<datosTrain[0].length; l++) {
        conjS[j][l] = datosTrain[j][l];
        conjR[j][l] = realTrain[j][l];
        conjN[j][l] = nominalTrain[j][l];
        conjM[j][l] = nulosTrain[j][l];
      }
      clasesS[j] = clasesTrain[j];
    }
    for (m=0;j<tamS; j++, m++) {
      for (l=0; l<datosTrain[0].length; l++) {
        conjS[j][l] = genS[m][l];
        conjR[j][l] = genR[m][l];
        conjN[j][l] = genN[m][l];
        conjM[j][l] = genM[m][l];
      }
      clasesS[j] = clasesGen[m];
    }

    /*TOMEK LINKS PART*/

    /*Inicialization of the instance flagged vector of the S set*/
    marcas = new boolean[conjS.length];
    for (i=0; i<conjS.length; i++) {
      marcas[i] = true;
    }
    nSel = conjS.length;

    for (i=0;i<conjS.length; i++) {
      minDist = Double.POSITIVE_INFINITY;
      pos = 0;
      for (j=0; j<conjS.length; j++) {
        if (i != j) {
          dist = KNN.distancia (conjS[i],conjS[j]);
          if (dist < minDist) {
            minDist = dist;
            pos = j;
          }
        }
      }
      if (clasesS[i] != clasesS[pos]) {
        if (clasesS[i] != posID) {
		  if (marcas[i]==true)
		  {
            marcas[i] = false;
			nSel--;
		  }
        } else {
		  if (marcas[pos]==true)
		  {
            marcas[pos] = false;
			nSel--;
		  }
        }
      }
    }

    /*Building of the S set from the flags*/
    conjS2 = new double[nSel][conjS[0].length];
    conjR2 = new double[nSel][conjS[0].length];
    conjN2 = new int[nSel][conjS[0].length];
    conjM2 = new boolean[nSel][conjS[0].length];
    clasesS2 = new int[nSel];
    for (i=0, l=0; i<conjS.length; i++) {
      if (marcas[i]) { //the instance will be copied to the solution
        for (j=0; j<conjS[0].length; j++) {
          conjS2[l][j] = conjS[i][j];
          conjR2[l][j] = conjR[i][j];
          conjN2[l][j] = conjN[i][j];
          conjM2[l][j] = conjM[i][j];
        }
        clasesS2[l] = clasesS[i];
        l++;
      }
    }


    System.out.println("SMOTE_TomekLinks "+ relation + " " + (double)(System.currentTimeMillis()-tiempo)/1000.0 + "s");

    OutputIS.escribeSalida(ficheroSalida[0], conjR2, conjN2, conjM2, clasesS2, entradas, salida, nEntradas, relation);
    OutputIS.escribeSalida(ficheroSalida[1], test, entradas, salida, nEntradas, relation);
  }

    /**
     * <p>
     * Computes the k nearest neighbors of a given item belonging to a fixed class.
     * With that neighbors a suggested class for the item is returned.
     * </p>
     *
     * @param nvec  Number of nearest neighbors that are going to be searched
     * @param conj  Matrix with the data of all the items in the dataset
     * @param real  Matrix with the data associated to the real attributes of the dataset
     * @param nominal   Matrix with the data associated to the nominal attributes of the dataset
     * @param nulos Matrix with the data associated to the missing values of the dataset
     * @param clases    Array with the associated class for each item in the dataset
     * @param ejemplo   Array with the data of the specific item in the dataset used
     * as a reference in the nearest neighbor search
     * @param ejReal    Array with the data of the real attributes of the specific item in the dataset
     * @param ejNominal Array with the data of the nominal attributes of the specific item in the dataset
     * @param ejNulos   Array with the data of the missing values of the specific item in the dataset
     * @param nClases   Class of the specific item in the dataset
     * @param distance  Kind of distance used in the nearest neighbors computation.
     * If true the distance used is the euclidean, if false the HVMD distance is used
     * @param vecinos   Array that will have the nearest neighbours id for the current specific item
     * @param clase Class of the neighbours searched for the item
     * @return the majority class for all the neighbors of the item
     */
    public static int evaluationKNNClass (int nvec, double conj[][], double real[][], int nominal[][], boolean nulos[][], int clases[], double ejemplo[], double ejReal[], int ejNominal[], boolean ejNulos[], int nClases, boolean distance, int vecinos[], int clase) {

            int i, j, l;
            boolean parar = false;
            int vecinosCercanos[];
            double minDistancias[];
            int votos[];
            double dist;
            int votada, votaciones;

            if (nvec > conj.length)
                    nvec = conj.length;

            votos = new int[nClases];
            vecinosCercanos = new int[nvec];
            minDistancias = new double[nvec];
            for (i=0; i<nvec; i++) {
                    vecinosCercanos[i] = -1;
                    minDistancias[i] = Double.POSITIVE_INFINITY;
            }

            for (i=0; i<conj.length; i++) {
                    dist = KNN.distancia(conj[i], real[i], nominal[i], nulos[i], ejemplo, ejReal, ejNominal, ejNulos, distance);
                    if (dist > 0 && clases[i] == clase) {
                            parar = false;
                            for (j = 0; j < nvec && !parar; j++) {
                                    if (dist < minDistancias[j]) {
                                            parar = true;
                                            for (l = nvec - 1; l >= j+1; l--) {
                                                    minDistancias[l] = minDistancias[l - 1];
                                                    vecinosCercanos[l] = vecinosCercanos[l - 1];
                                            }
                                            minDistancias[j] = dist;
                                            vecinosCercanos[j] = i;
                                    }
                            }
                    }
            }

            for (j=0; j<nClases; j++) {
                    votos[j] = 0;
            }

            for (j=0; j<nvec; j++) {
                    if (vecinosCercanos[j] >= 0)
                            votos[clases[vecinosCercanos[j]]] ++;
            }

            votada = 0;
            votaciones = votos[0];
            for (j=1; j<nClases; j++) {
                    if (votaciones < votos[j]) {
                            votaciones = votos[j];
                            votada = j;
                    }
            }

            for (i=0; i<vecinosCercanos.length; i++)
                    vecinos[i] = vecinosCercanos[i];

            return votada;
    }

    /**
     * <p>
     * Generates a synthetic example for the minority class from two existing
     * examples in the current population
     * </p>
     *
     * @param ra    Array with the real values of the first example in the current population
     * @param rb    Array with the real values of the second example in the current population
     * @param na    Array with the nominal values of the first example in the current population
     * @param nb    Array with the nominal values of the second example in the current population
     * @param ma    Array with the missing values of the first example in the current population
     * @param mb    Array with the missing values of the second example in the current population
     * @param resS  Array with the general data about the generated example
     * @param resR  Array with the real values of the generated example
     * @param resN  Array with the nominal values of the generated example
     * @param resM  Array with the missing values of the generated example
     */
    void interpolate (double ra[], double rb[], int na[], int nb[], boolean ma[], boolean mb[], double resS[], double resR[], int resN[], boolean resM[]) {

            int i;
            double diff;
            double gap;
            int suerte;

            for (i=0; i<ra.length; i++) {
                    if (ma[i] == true && mb[i] == true) {
                            resM[i] = true;
                            resS[i] = 0;
                    } else if (ma[i] == true){
                            if (entradas[i].getType() == Attribute.REAL) {
                                    resR[i] = rb[i];
                                    resS[i] = (resR[i] + entradas[i].getMinAttribute()) / (entradas[i].getMaxAttribute() - entradas[i].getMinAttribute());
                            } else if (entradas[i].getType() == Attribute.INTEGER) {
                                    resR[i] = rb[i];
                                    resS[i] = (resR[i] + entradas[i].getMinAttribute()) / (entradas[i].getMaxAttribute() - entradas[i].getMinAttribute());
                            } else {
                                    resN[i] = nb[i];
                                    resS[i] = (double)resN[i] / (double)(entradas[i].getNominalValuesList().size() - 1);
                            }
                    } else if (mb[i] == true) {
                            if (entradas[i].getType() == Attribute.REAL) {
                                    resR[i] = ra[i];
                                    resS[i] = (resR[i] + entradas[i].getMinAttribute()) / (entradas[i].getMaxAttribute() - entradas[i].getMinAttribute());
                            } else if (entradas[i].getType() == Attribute.INTEGER) {
                                    resR[i] = ra[i];
                                    resS[i] = (resR[i] + entradas[i].getMinAttribute()) / (entradas[i].getMaxAttribute() - entradas[i].getMinAttribute());
                            } else {
                                    resN[i] = na[i];
                                    resS[i] = (double)resN[i] / (double)(entradas[i].getNominalValuesList().size() - 1);
                            }
                    } else {
                            resM[i] = false;
                            if (entradas[i].getType() == Attribute.REAL) {
                                    diff = rb[i] - ra[i];
                                    gap = Randomize.Rand();
                                    resR[i] = ra[i] + gap*diff;
                                    resS[i] = (resR[i] + entradas[i].getMinAttribute()) / (entradas[i].getMaxAttribute() - entradas[i].getMinAttribute());
                            } else if (entradas[i].getType() == Attribute.INTEGER) {
                                    diff = rb[i] - ra[i];
                                    gap = Randomize.Rand();
                                    resR[i] = Math.round(ra[i] + gap*diff);
                                    resS[i] = (resR[i] + entradas[i].getMinAttribute()) / (entradas[i].getMaxAttribute() - entradas[i].getMinAttribute());
                            } else {
                                    suerte = Randomize.Randint(0, 2);
                                    if (suerte == 0) {
                                            resN[i] = na[i];
                                    } else {
                                            resN[i] = nb[i];
                                    }
                                    resS[i] = (double)resN[i] / (double)(entradas[i].getNominalValuesList().size() - 1);
                            }
                    }
            }
    }

  /**
   * <p>
   * Obtains the parameters used in the execution of the algorithm and stores
   * them in the private variables of the class
   * </p>
   *
   * @param ficheroScript Name of the configuration script that indicates the
   * parameters that are going to be used during the execution of the algorithm
   */
  public void leerConfiguracion (String ficheroScript) {

    String fichero, linea, token;
    StringTokenizer lineasFichero, tokens;
    byte line[];
    int i, j;

    ficheroSalida = new String[2];

    fichero = Fichero.leeFichero (ficheroScript);
    lineasFichero = new StringTokenizer (fichero,"\n\r");

    lineasFichero.nextToken();
    linea = lineasFichero.nextToken();

    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    token = tokens.nextToken();

    /*Getting the names of the training and test files*/
    line = token.getBytes();
    for (i=0; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroTraining = new String (line,i,j-i);
    for (i=j+1; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroTest = new String (line,i,j-i);

    /*Getting the path and base name of the results files*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    token = tokens.nextToken();

    /*Getting the names of output files*/
    line = token.getBytes();
    for (i=0; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroSalida[0] = new String (line,i,j-i);
    for (i=j+1; line[i]!='\"'; i++);
    i++;
    for (j=i; line[j]!='\"'; j++);
    ficheroSalida[1] = new String (line,i,j-i);

    /*Getting the seed*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    semilla = Long.parseLong(tokens.nextToken().substring(0));
    /*Getting the number of neighbors*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    kSMOTE = Integer.parseInt(tokens.nextToken().substring(0));
    /*Getting the type of SMOTE algorithm*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    token = tokens.nextToken();
    token = token.substring(0);
    if (token.equalsIgnoreCase("both")) ASMO = 0;
    else if (token.equalsIgnoreCase("minority")) ASMO = 1;
    else ASMO = 2;

    /*Getting the type of balancing in SMOTE*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    token = tokens.nextToken();
    token = token.substring(0);
    if (token.equalsIgnoreCase("YES")) balance = true;
    else balance = false;  		

    /*Getting the quantity of smoting*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    smoting = Double.parseDouble(tokens.nextToken().substring(0));

    /*Getting the type of distance function*/
    linea = lineasFichero.nextToken();
    tokens = new StringTokenizer (linea, "=");
    tokens.nextToken();
    distanceEu = tokens.nextToken().substring(0).equalsIgnoreCase("Euclidean")?true:false;
  }
  
	/** 
	 * This function builds the data matrix for reference data and normalizes inputs values
	 * @throws CheckException Can not be normalized
	 */	
	protected void normalizar () throws CheckException {

		int i, j, k;
		Instance temp;
		double caja[];
		StringTokenizer tokens;
		boolean nulls[];

		/*Check if dataset corresponding with a classification problem*/

		if (Attributes.getOutputNumAttributes() < 1) {
		  throw new CheckException ("This dataset haven?t outputs, so it not corresponding to a classification problem.");
		} else if (Attributes.getOutputNumAttributes() > 1) {
		  throw new CheckException ("This dataset have more of one output.");
		}

		if (Attributes.getOutputAttribute(0).getType() == Attribute.REAL) {
		  throw new CheckException ("This dataset have an input attribute with floating values, so it not corresponding to a classification problem.");
		}

		entradas = Attributes.getInputAttributes();
		salida = Attributes.getOutputAttribute(0);
		nEntradas = Attributes.getInputNumAttributes();
		tokens = new StringTokenizer (training.getHeader()," \n\r");
		tokens.nextToken();
		relation = tokens.nextToken();

		datosTrain = new double[training.getNumInstances()][Attributes.getInputNumAttributes()];
		clasesTrain = new int[training.getNumInstances()];
		caja = new double[1];

		nulosTrain = new boolean[training.getNumInstances()][Attributes.getInputNumAttributes()];
		nominalTrain = new int[training.getNumInstances()][Attributes.getInputNumAttributes()];
		realTrain = new double[training.getNumInstances()][Attributes.getInputNumAttributes()];

		for (i=0; i<training.getNumInstances(); i++) {
			temp = training.getInstance(i);
			nulls = temp.getInputMissingValues();
			datosTrain[i] = training.getInstance(i).getAllInputValues();
			for (j=0; j<nulls.length; j++)
				if (nulls[j]) {
					datosTrain[i][j]=0.0;
					nulosTrain[i][j] = true;
				}
			caja = training.getInstance(i).getAllOutputValues();
			clasesTrain[i] = (int) caja[0];
			for (k = 0; k < datosTrain[i].length; k++) {
				if (Attributes.getInputAttribute(k).getType() == Attribute.NOMINAL) {
					nominalTrain[i][k] = (int)datosTrain[i][k]; 
					datosTrain[i][k] /= Attributes.getInputAttribute(k).
					getNominalValuesList().size() - 1;
				} else {
					realTrain[i][k] = datosTrain[i][k];
					datosTrain[i][k] -= Attributes.getInputAttribute(k).getMinAttribute();
					datosTrain[i][k] /= Attributes.getInputAttribute(k).getMaxAttribute() -
					Attributes.getInputAttribute(k).getMinAttribute();
					if (Double.isNaN(datosTrain[i][k])){
						datosTrain[i][k] = realTrain[i][k];
			    }
				}
			}
		} 
              
            
        
      datosTest = new double[test.getNumInstances()][Attributes.getInputNumAttributes()];
		clasesTest = new int[test.getNumInstances()];
      caja = new double[1];
              
      for (i=0; i<test.getNumInstances(); i++) {
			temp = test.getInstance(i);
			nulls = temp.getInputMissingValues();
			datosTest[i] = test.getInstance(i).getAllInputValues();
			for (j=0; j<nulls.length; j++)
				if (nulls[j]) {
					datosTest[i][j]=0.0;
				}
			caja = test.getInstance(i).getAllOutputValues();
			clasesTest[i] = (int) caja[0];
		} 
              		
	} //end-method
  
}