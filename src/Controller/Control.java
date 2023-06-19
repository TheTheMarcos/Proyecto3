package Controller;

import Observer.*;
import View.vie;
import Model.Modelo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
public class Control implements Observer {
    private final vie view;
    private final Modelo model;

    public Control (vie view, Modelo model) {
        this.view = view;
        this.model = model;

        this.view.addCalculationListener(new CalculateListener());
        this.view.addOperationListener(new OperationListener());
        this.model.registerObserver(this);
    }

    private class CalculateListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            int numberUserPressed = Integer.parseInt(((JButton)e.getSource()).getText());
            model.addDigit(numberUserPressed);
        }
    }

    private class OperationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String operationPressed = ((JButton)e.getSource()).getText();

            if (Objects.equals(operationPressed, "=")) {
                model.setNumber(model.makeOperation());
            } else {
                model.setCurrentTypeOfOperation(operationPressed);
                model.setPreviousNumber(model.getNumber());
                model.setNumber(0);
            }
        }
    }

    @Override
    public void update() {
        view.setNumber(model.getNumber());
        view.setPreviousNumber(model.getPreviousNumber(), model.getCurrentTypeOfOperation());
    }
}
