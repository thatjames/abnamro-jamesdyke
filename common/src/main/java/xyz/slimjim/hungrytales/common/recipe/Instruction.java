package xyz.slimjim.hungrytales.common.recipe;

public class Instruction implements Comparable<Instruction>{

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
    public int compareTo(Instruction other) {
        if (other.stepNumber > this.stepNumber) {
            return -1;
        } else if (other.stepNumber < this.stepNumber) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "CookingStep{" +
                "stepNumber=" + stepNumber +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
