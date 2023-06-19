import Controller.Control;
import Model.Modelo;
import View.vie;
public class Main {
    public static void main(String[] args) {
        vie view = new vie();
        Modelo model = new Modelo();
        Control controller= new Control(view, model);

        controller.update();
    }
}
