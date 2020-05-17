/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import dao.ClientesDAO;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Luisa
 */
public class ProbarClientesDAO {

    static ClientesDAO clientes = new ClientesDAO();
    static Scanner sc = new Scanner(System.in, "ISO-8859-1");

    public static void main(String[] args) {

        //ver los primeros 10 clientes
        verCliente(0);

        if (clientes.getConexion() == null) {
            System.out.println("Programa terminado, error en la conexion ");
            System.exit(1);
        }
        int opc;
        boolean fin = true;
        int cont = 0;

        while (fin) {
            System.out.println("\n--------------------------------------------------------");
            System.out.println("\t\tBASE DE DATOS CLIENTES ");
            System.out.println("--------------------------------------------------------");
            System.out.println("\t1. Buscar datos cliente");
            System.out.println("\t2. Visualizar los diez siguientes ");
            System.out.println("\t3. Visualizar los diez anteriores ");
            System.out.println("\t4. Insertar un nuevo cliente ");
            System.out.println("\t5. Actualizar dato de cliente por idCliente");
            System.out.println("\t6. Eliminar datos del cliente");
            System.out.println("\t0.- Terminar la aplicacion");
            System.out.print("\nLa opcion seleccionada es: \t");

            try {
                Scanner ent = new Scanner(System.in);
                opc = ent.nextInt();
                switch (opc) {
                    case 1:
                        System.out.println("------------------------------");
                        System.out.println("Busqueda de cliente ");
                        System.out.println("------------------------------");
                        buscarCliente();
                        break;

                    case 2:
                        System.out.println("------------------------------");
                        System.out.println("Visualizar los diez siguientes");
                        System.out.println("------------------------------");
                        cont += 10;
                        verCliente(cont);
                        break;

                    case 3:
                        System.out.println("------------------------------");
                        System.out.println("Visualizar los diez anteriores");
                        System.out.println("------------------------------");
                        cont -= 10;
                        verCliente(cont);
                        break;
                    case 4:
                        System.out.println("------------------------------");
                        System.out.println("Añadir un cliente");
                        System.out.println("------------------------------");
                        añadirCliente();
                        break;
                    case 5:
                        System.out.println("------------------------------");
                        System.out.println("Modificar un cliente");
                        System.out.println("------------------------------");
                        modificarCliente();
                        break;
                    case 6:
                        System.out.println("------------------------------");
                        System.out.println("Borrar un cliente");
                        System.out.println("------------------------------");
                        borrarCliente();
                        break;
                    case 0:
                        System.out.println("\nGracias por utilizar la aplicacion\n");
                        fin = false;
                        break;
                    default:
                        System.out.println("Introduzca un numero entre 0 y 7");
                }
            } catch (java.util.InputMismatchException exc) {
                System.out.println("Introduzca un valor valido");
            }

        }

    }

    //leer cliente
    public static Cliente existeCliente() {
        Cliente c = null;
        System.out.print("\nIndique el ID del cliente que desea buscar: ");
        c = clientes.read(Integer.parseInt(sc.nextLine()));
        return c;
    }

    public static void buscarCliente() {
        Cliente cliente = existeCliente();

        if (cliente != null) {
            String emp = cliente.getEmpresa();
            if (emp.length() > 25) {
                emp = emp.substring(0, 20);
            }
            //System.out.println(cliente.toString());
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.println("   " + "ID\t CODIGO\t\t  EMPRESA\t\t\tCONTACTO\t\tCARGO CONTACTO\t\t  PAIS");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("|%5d\t| %-5s | %-30s| %-25s | %-25s | %-12s |\n", cliente.getId(), cliente.getCodigo(), emp, cliente.getContacto(), cliente.getCargo_Contacto(), cliente.getPais());
        } else {
            System.err.println("El cliente no existe o no se puede leer.");
        }

    }

    private static void verCliente(int cont) {
        Cliente cliente = new Cliente();
        ClientesDAO clientes = new ClientesDAO();
        System.out.println("Clientes");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("   " + "ID\t CODIGO\t\t  EMPRESA\t\t\tCONTACTO\t\tCARGO CONTACTO\t\t  PAIS");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        try {
            for (Cliente a : clientes.filter(cont)) {
                String emp = a.getEmpresa();
                if (emp.length() > 25) {
                    emp = emp.substring(0, 20);
                }
                System.out.printf("|%5d\t| %-5s | %-30s| %-25s | %-25s | %-12s |\n", a.getId(), a.getCodigo(), emp, a.getContacto(), a.getCargo_Contacto(), a.getPais());

            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("Error " + e.toString());
            e.getStackTrace();
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------");

    }

    private static void añadirCliente() {
        Cliente c = new Cliente();
        /*BBDD autoincrementada por consiguiente no se ingresa id de forma manual*/
        System.out.print("Ingrese el Código del cliente: ");
        c.setCodigo(sc.next());
        System.out.print("Ingrese Empresa del cliente:");
        c.setEmpresa(sc.next());
        System.out.print("Ingrese Contacto del cliente: ");
        c.setContacto(sc.next());
        System.out.print("Ingrese Cargo del contacto: ");
        c.setCargo_Contacto(sc.next());
        System.out.print("Ingrese la dirección del cliente: ");
        c.setDireccion(sc.next());
        System.out.print("Ingrese la ciudad del cliente: ");
        c.setCiudad(sc.next());
        System.out.print("Ingrese region del cliente: ");
        c.setRegion(sc.next());
        System.out.print("Ingrese Codigo postal del cliente: ");
        c.setCp(sc.next());
        System.out.print("Ingrese Pais del cliente: ");
        c.setPais(sc.next());
        System.out.print("Ingrese Teléfono del cliente: ");
        c.setTelefono(sc.next());
        System.out.print("Ingrese Fax del cliente: ");
        c.setFax(sc.next());

        if (clientes.insert(c)) {
            System.out.println("\n\tEl cliente " + c.getEmpresa() + " con código " + c.getCodigo() + " se ha añadido satisfactoriamente");
        } else {
            System.out.println("\n\tEl cliente que desea añadir no es válido o ya existe");
        }
    }

    private static void modificarCliente() {
        Cliente cliente = existeCliente();

        if (cliente == null) {
            System.out.println("El cliente no existe o no puede ser verificado");
        }

        while (true) {
            try {

                System.out.println("\n" + cliente);
                System.out.println("-----------------------------------------------");
                System.out.println("\nElija algunas de las siguientes opciones: ");

                Integer opcion;

                System.out.println("\n\t1. Codigo");
                System.out.println("\t2. Empresa");
                System.out.println("\t3. Contacto");
                System.out.println("\t4. Cargo Contacto");
                System.out.println("\t5. Direccion");
                System.out.println("\t6. Ciudad");
                System.out.println("\t7. Region");
                System.out.println("\t8. Codigo Postal");
                System.out.println("\t9. Pais");
                System.out.println("\t10. Teléfono");
                System.out.println("\t11. Fax");
                System.out.println("\t0. Finalizar");
                System.out.print("\nLa opcion seleccionada es: \t");
                opcion = Integer.parseInt(sc.nextLine());

                if (opcion > 0 && opcion < 11) {
                    System.out.print("\nIntroduzca la modificacion que desea realizar: ");
                }
                switch (opcion) {
                    case 1:
                        cliente.setCodigo(sc.nextLine());
                        clientes.update(cliente);
                        if (clientes.update(cliente) == true) {
                            System.out.println("\nCliente modificado con exito: ");
                        }
                        break;
                    case 2:
                        cliente.setEmpresa(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 3:
                        cliente.setContacto(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 4:
                        cliente.setCargo_Contacto(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 5:
                        cliente.setDireccion(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 6:
                        cliente.setCiudad(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 7:
                        cliente.setRegion(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 8:
                        cliente.setCp(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 9:
                        cliente.setPais(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 10:
                        cliente.setTelefono(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 11:
                        cliente.setFax(sc.nextLine());
                        clientes.update(cliente);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Ingrese una opcion entre 0 y 11");
                }

            } catch (java.util.InputMismatchException exc) {
                System.out.println("La opcion ingresada no es válida");
            } catch (NumberFormatException nfe) {
                System.err.println("\nError: " + nfe.getMessage() + "\n");
            }
        }
    }

    private static void borrarCliente() {
        Cliente cliente = existeCliente();
        String result = null;
        if (cliente != null) {
            System.out.println("\nEsta seguro de querer eliminar el cliente " + "\n" + cliente.getEmpresa());
            System.out.print("Su respuesta [Y / N ]: ");
            result = sc.nextLine();

            if (result.equalsIgnoreCase("y")) {
                clientes.delete(cliente.getId());
                System.out.println("Cliente eliminado ");
            } else {
                System.out.println("Cliente no eliminado ");
            }
        } else {
            System.out.println("Cliente no existe o no se puede validar");

        }

    }

}
