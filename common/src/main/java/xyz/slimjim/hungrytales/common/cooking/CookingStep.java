package xyz.slimjim.hungrytales.common.cooking;

public class CookingStep implements Comparable<CookingStep>{

    private int stepNumber;
    private String instruction;

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public int compareTo(CookingStep other) {
        if (other.stepNumber > this.stepNumber) {
            return -1;
        } else if (other.stepNumber < this.stepNumber) {
            return 1;
        } else {
            return 0;
        }
    }
}
