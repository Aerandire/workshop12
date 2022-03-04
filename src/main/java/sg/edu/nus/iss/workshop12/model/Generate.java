package sg.edu.nus.iss.workshop12.model;

import java.io.Serializable;

public class Generate implements Serializable{
    private int noOfNumberToRandomize;

    public int getNoOfNumberToRandomize() {
        return noOfNumberToRandomize;
    }

    public void setNoOfNumberToRandomize(int noOfNumberToRandomize) {
        this.noOfNumberToRandomize = noOfNumberToRandomize;
    }
}
