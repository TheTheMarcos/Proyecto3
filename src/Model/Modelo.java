package Model;
import Observer.*;
import java.util.ArrayList;
public class Modelo {
    private ArrayList<Observer> observers;

    private double number;
    private double previousNumber;
    private String currentTypeOfOperation;

    public Modelo () {
        observers = new ArrayList<>();
        number = 0;
        previousNumber = 0;
        currentTypeOfOperation = "";
    }

    public void addDigit (float digit) {
        setNumber((getNumber() * 10) + digit);
    }

    public Double makeOperation () {
        Double result = 0.0;
        switch (this.currentTypeOfOperation) {
            case "+":
                result = this.getPreviousNumber() + this.getNumber();
                break;
            case "-":
                result = this.getPreviousNumber() - this.getNumber();
                break;
            case "x":
                result = this.getPreviousNumber() * this.getNumber();
                break;
            case "/":
                result = this.getPreviousNumber() / this.getNumber();
                break;
        }
        return result;
    }

    public double getNumber () {
        return number;
    }

    public void setNumber (double number) {
        this.number = number;
        notifyObservers();
    }

    public String getCurrentTypeOfOperation() {
        return currentTypeOfOperation;
    }

    public void setCurrentTypeOfOperation(String currentTypeOfOperation) {
        this.currentTypeOfOperation = currentTypeOfOperation;
    }

    public double getPreviousNumber() {
        return previousNumber;
    }

    public void setPreviousNumber(double previousNumber) {
        this.previousNumber = previousNumber;
    }


    public void registerObserver (Observer o) {
        observers.add(o);
    }


    public void unregisterObserver (Observer o) {
        observers.remove(o);
    }


    public void notifyObservers() {
        this.observers.forEach(Observer::update);
    }
}
