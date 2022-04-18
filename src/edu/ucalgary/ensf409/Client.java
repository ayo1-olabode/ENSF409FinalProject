/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.3
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

public class Client {
    private int maleAdults;
    private int femaleAdults;
	private int ChildUnder8;
    private int ChildOver8;
    
    private int totalWG;
    private int totalFV;
    private int totalP;
    private int totalO;
    private int totalC;
    
	//gets the required amount of macros for the specified type of person in the hamper
    private ClientValues constants;
    
    public Client(int maleAdults, int femaleAdults, int ChildUnder8, int ChildOver8, ClientValues constants) throws HamperCreationException {
        this.maleAdults = maleAdults;
      
        this.femaleAdults = femaleAdults;
      
        this.ChildUnder8 = ChildUnder8;
      
        this.ChildOver8 = ChildOver8;
       
	    if (this.maleAdults == 0 && this.femaleAdults == 0 && this.ChildOver8 == 0 && this.ChildUnder8 == 0 ) {
                throw new HamperCreationException();
        }
	    
        this.constants = constants;

        calculateTotals();
    }

	//find total macronutrients for client
    private void calculateTotals() {
    	totalWG = constants.getAdultMaleWG()*maleAdults + constants.getAdultFemaleWG()*femaleAdults + constants.getChildOverWG()*ChildOver8 + constants.getChildUnderWG()*ChildUnder8;
        totalFV = constants.getAdultMaleFV()*maleAdults + constants.getAdultFemaleFV()*femaleAdults + constants.getChildOverFV()*ChildOver8 + constants.getChildUnderFV()*ChildUnder8;
        totalP = constants.getAdultMaleP()*maleAdults + constants.getAdultFemaleP()*femaleAdults + constants.getChildOverP()*ChildOver8 + constants.getChildUnderP()*ChildUnder8;
        totalO = constants.getAdultMaleO()*maleAdults + constants.getAdultFemaleO()*femaleAdults + constants.getChildOverO()*ChildOver8 + constants.getChildUnderO()*ChildUnder8;
        totalC = constants.getAdultMaleC()*maleAdults + constants.getAdultFemaleC()*femaleAdults + constants.getChildOverC()*ChildOver8 + constants.getChildUnderC()*ChildUnder8;
    }

    //getters and setters
    public int getMaleAdults(){
        return maleAdults;
    }

    public int getFemaleAdults(){
        return femaleAdults;
    }

    public int getChildUnder8(){
        return ChildUnder8;
    }

    public int getChildOver8(){
        return ChildOver8;
    }
 	
 	//return total values for nutritional needs
	public int getTotalCalories() {
		return this.totalC;
	}

	public int getTotalOther() {
		return this.totalO;
	}

	public int getTotalProtein() {
		return this.totalP;
	}

	public int getTotalFruitsVeggies() {
		return this.totalFV;
	}

	public int getTotalWholeGrains() {
		return this.totalWG;
	}
}
