package org.example;
import java.util.ArrayList;
import java.util.Scanner;

//Creamos una clase empleado con todos los datos que necesitamos
class Empleado {
    String nombres;
    String apellidos;
    String documento;
    int diasTrabajados;
    double salarioBase;
    boolean recibeSubsidioTransporte;
    double subsidioTransporte;

    double devengos;
    double descuentos;
    double salarioNeto;

    //Ahora realizamos un constructor para llamar los datos con el this
    public Empleado(String nombres, String apellidos, String documento, int diasTrabajados, double salarioBase, boolean recibeSubsidioTransporte, double subsidioTransporte) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.diasTrabajados = diasTrabajados;
        this.salarioBase = salarioBase;
        this.recibeSubsidioTransporte = recibeSubsidioTransporte;
        this.subsidioTransporte = subsidioTransporte;
    }

    //Realizamos un metodo para calcular la nomina
    public void calcularNomina() {
        double salarioDiario = salarioBase / 30;
        double salarioDevengado = salarioDiario * diasTrabajados;

        /* Para el auxilio de transporte se utiliza este operador "?",
        * el cual hace que la variable recibeSubsidioDeTransporte haga
        * una condicion, si esta se cumple se realiza el calculo del
        * subsidio en el caso de que no, el valor del subsidio es igual a 0*/
        double auxilioTransporte = recibeSubsidioTransporte ? (subsidioTransporte / 30) * diasTrabajados : 0;

        devengos = salarioDevengado + auxilioTransporte;

        //Calculo de salud y pension que se traducen a los descuentos
        double salud = devengos * 0.04;
        double pension = devengos * 0.04;

        descuentos = salud + pension;

        salarioNeto = devengos - descuentos;
    }
}

public class Main {
    public static void main(String[] args) {
        //Se utiliza el objeto scaner para pedir los datos en consola
        Scanner scanner = new Scanner(System.in);

        //Se crea un array list para guardar los datos de los empleados
        ArrayList<Empleado> empleados = new ArrayList<>();

        System.out.println("Ingrese el número de empleados:");
        int numeroEmpleados = Integer.parseInt(scanner.nextLine());

        /*Se crea un for con una variable inicial en 0 y despues
        * se hace la condicion que si i es menor al numero de
        * empleados se haga un incremento, para que el bucle pida los
        * respectivos datos de los empleados*/
        for (int i = 0; i < numeroEmpleados; i++) {
            System.out.println("Ingrese los datos del empleado " + (i + 1));

            System.out.print("Nombres: ");
            String nombres = scanner.nextLine();

            System.out.print("Apellidos: ");
            String apellidos = scanner.nextLine();

            System.out.print("Documento: ");
            String documento = scanner.nextLine();

            System.out.print("Días trabajados: ");
            int diasTrabajados = Integer.parseInt(scanner.nextLine());

            System.out.print("Salario base mensual: ");
            double salarioBase = Double.parseDouble(scanner.nextLine());

            System.out.print("¿Recibe subsidio de transporte? (true/false): ");
            boolean recibeSubsidioTransporte = Boolean.parseBoolean(scanner.nextLine());

            double subsidioTransporte = 140606; // Valor del subsidio de transporte en 2024

            /*Ahora se intancia la clase empleado, y ademas se llama al metodo
            * calcularNomina() y se añade el objeto empleado a la lista empleados,
            * para seguir almacenando los datos*/
            Empleado empleado = new Empleado(nombres, apellidos, documento, diasTrabajados, salarioBase, recibeSubsidioTransporte, subsidioTransporte);
            empleado.calcularNomina();
            empleados.add(empleado);
        }

        /* Por ultimo se realiza un for recorriendo el objeto empleado en la
        * lista empleados, de esta forma se muestran los datos relevantes
        * de la liquidacion*/
        System.out.println("\nResumen de la nómina:");
        for (Empleado empleado : empleados) {
            System.out.println("Empleado: " + empleado.nombres + " " + empleado.apellidos);
            System.out.println("Documento: " + empleado.documento);
            System.out.println("Total Devengos: " + empleado.devengos);
            System.out.println("Total Descuentos: " + empleado.descuentos);
            System.out.println("Salario Neto a Pagar: " + empleado.salarioNeto);
            System.out.println();
        }

        scanner.close();
    }
}
