package xyz.slimjim.hungrytales.web.dto;

public class InstructionDTO extends BaseDTO {

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
    public String toString() {
        return "CookingStepDTO{" +
                "stepNumber=" + stepNumber +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
