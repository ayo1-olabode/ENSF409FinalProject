/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.2
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

public class ClientValues {
    private int adultMaleWG = 1;
    private int adultMaleFV= 1;
    private int adultMaleP= 1;
    private int adultMaleO= 1;
    private int adultMaleC= 1;
    
    private int adultFemaleWG;
    private int adultFemaleFV;
    private int adultFemaleP;
    private int adultFemaleO;
    private int adultFemaleC;
    
    private int childOverWG;
    private int childOverFV;
    private int childOverP;
    private int childOverO;
    private int childOverC;
    
    private int childUnderWG;
    private int childUnderFV;
    private int childUnderP;
    private int childUnderO;
    private int childUnderC;
    
	//object that collects macro requirements of different types of people
    public ClientValues(int amwg, int amfv, int amp, int amo, int amc, int afwg, int affv, int afp, int afo, 
    		int afc,int cowg, int cofv, int cop, int coo, int coc,int cuwg, int cufv, int cup, int cuo, int cuc){
        this.adultMaleWG = amwg;
        
        this.adultMaleFV = amfv;
        this.adultMaleP = amp;
        this.adultMaleO = amo;
        this.adultMaleC = amc;

        this.adultFemaleWG = afwg;
        this.adultFemaleFV = affv;
        this.adultFemaleP = afp;
        this.adultFemaleO = afo;
        this.adultFemaleC = afc;

        this.childOverWG = cowg;
        this.childOverFV = cofv;
        this.childOverP = cop;
        this.childOverO = coo;
        this.childOverC = coc;

        this.childUnderWG = cuwg;
        this.childUnderFV = cufv;
        this.childUnderP = cup;
        this.childUnderO = cuo;
        this.childUnderC = cuc;
    }

	//getters for macros
    
	public int getAdultMaleWG() {
		return adultMaleWG;
	}

	public int getAdultMaleFV() {
		return adultMaleFV;
	}

	public int getAdultMaleP() {
		return adultMaleP;
	}

	public int getAdultMaleO() {
		return adultMaleO;
	}

	public int getAdultMaleC() {
		return adultMaleC;
	}

	public int getAdultFemaleWG() {
		return adultFemaleWG;
	}

	public int getAdultFemaleFV() {
		return adultFemaleFV;
	}

	public int getAdultFemaleP() {
		return adultFemaleP;
	}

	public int getAdultFemaleO() {
		return adultFemaleO;
	}

	public int getAdultFemaleC() {
		return adultFemaleC;
	}

	public int getChildOverWG() {
		return childOverWG;
	}

	public int getChildOverFV() {
		return childOverFV;
	}

	public int getChildOverP() {
		return childOverP;
	}

	public int getChildOverO() {
		return childOverO;
	}

	public int getChildOverC() {
		return childOverC;
	}

	public int getChildUnderWG() {
		return childUnderWG;
	}

	public int getChildUnderFV() {
		return childUnderFV;
	}

	public int getChildUnderP() {
		return childUnderP;
	}

	public int getChildUnderO() {
		return childUnderO;
	}

	public int getChildUnderC() {
		return childUnderC;
	}
}
