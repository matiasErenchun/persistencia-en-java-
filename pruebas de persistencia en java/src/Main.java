import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main
{
    private static ArrayList<Personaje> listaDePersonajes;
    private static Lector lector;
    public static void main(String[] args) throws IOException
    {
       lector= new Lector();
       listaDePersonajes = new ArrayList<Personaje>();
       File fichero = new File("D:/repo git local 2/persitencia en java/persistencia-en-java-/pruebas de persistencia en java/data.txt");// al momento de eliminar esta direccion es la valida.
       System.out.println("el archivo existe"+fichero.exists());
       Main.crearArchivo(fichero);

       Main.menu();
       Main.eliminarFichero(fichero);
    }

    private static void menu()
    {
        boolean b=true;
        int i=0;
        while(b)
        {
            System.out.println("las opciones de menu son :");
            System.out.println("presione 0 para salir.");
            System.out.println("presione 1 para agregar un personaje.");
            System.out.println("presione 2 para mostrar todos los personajes.");
            i=lector.leerNumero("recuerde ingresar un valor valido los cuales son 0, 1 y 2");
            switch (i){
                case 0:
                    b=false;
                    break;
                case 1:
                    Personaje pj=Main.crearPersonaje();
                    Main.agregarPersonaje(pj);

                    break;
                case 2:
                    Main.mostrarPersonajes();
                    break;

                default:
                    System.out.println("recuerde ingresar un valor valido: ");
                    System.out.println("presione 0 para salir.");
                    System.out.println("presione 1 para agregar un personaje.");
                    System.out.println("presione 2 para mostrar todos los personajes.");
                    break;


            }

        }


    }

    private static Personaje crearPersonaje()
    {
        System.out.println("ingrese el nombre del pj");
        String name=lector.leerContraseña();
        System.out.println("ingrese la raza del pj");
        String raza=lector.leerContraseña();
        System.out.println("ingrese la clase del pj");
        String clase=lector.leerContraseña();
        System.out.println("ingrese la raza del pj");
        int edad=lector.leerNumero("ingrese un numero ");
        Personaje pj1= new Personaje(name,raza,clase,edad);
        return pj1;
    }

    private static boolean agregarPersonaje(Personaje personaje)
    {
       return listaDePersonajes.add(personaje);
    }

    private static void mostrarPersonajes()
    {
        if(listaDePersonajes.size()==0)
        {
            System.out.println(" no existen personajes creados");
        }
        else{
            for (Personaje pj: listaDePersonajes)
            {
                String s= pj.ToString();
                System.out.println(s);
            }
        }
    }

    private static void crearArchivo(File file)throws IOException
    {
        BufferedWriter bw = null;
        if(file.exists())
        {
            bw = new BufferedWriter(new FileWriter(file));

        }
        else
        {
            File fichero = new File("D:/repo git local 2/persitencia en java/persistencia-en-java-/pruebas de persistencia en java/data.txt");//la direccion final define el nombre  del archivo
            fichero.setWritable(true);
            bw = new BufferedWriter(new FileWriter(fichero));

        }
        bw.close();

    }

    private static void eliminarFichero(File fichero)
    {
        String nombreFile;
        if (!fichero.exists())// si el fichero no existe
        {
            nombreFile=fichero.getName();
            System.out.println("El archivo "+nombreFile +" no existe.");
        }
        else // si el fichero existe
        {
            nombreFile=fichero.getName();
            fichero.delete();
            System.out.println("El archivo "+nombreFile +" fue eliminado.");
        }
    }

}
