import java.io.*;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Main
{
    private static ArrayList<Personaje> listaDePersonajes;
    private static Lector lector;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       lector= new Lector();
       listaDePersonajes = new ArrayList<Personaje>();
       File fichero = new File("D:/repo git local 2/persitencia en java/persistencia-en-java-/pruebas de persistencia en java/data.txt");// al momento de eliminar esta direccion es la valida.
       fichero=Main.crearArchivo(fichero);
       if(fichero.exists()) {
           Main.leerDatos(fichero);
       }

       Main.menu();
       Main.escribirDatos(fichero);
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
        System.out.println("ingrese la edad del pj");
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
                String s= pj.myToString();
                System.out.println(s);
            }
        }
    }

    private static File  crearArchivo(File fichero)throws IOException
    {

        if(!fichero.exists())// si el archivo no existe se crea uno nuevo.
        {
            File miFichero = new File("D:/repo git local 2/persitencia en java/persistencia-en-java-/pruebas de persistencia en java/data.txt");//la direccion final define el nombre  del archivo
            miFichero.setWritable(true);
            System.out.println(miFichero.getName());
            return miFichero;

        }

        return fichero;
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

    private static void escribirDatos(File fichero)throws IOException
    {
        Main.eliminarFichero(fichero);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
        for (Personaje personaje: listaDePersonajes)
        {
                oos.writeObject(personaje);
        }
        oos.close();
        
    }

    private static void cargarDatos(File fichero)throws IOException, ClassNotFoundException
    {
        if(fichero.exists())
        {
            Main.leerDatos(fichero);
        }
    }

    private static void leerDatos(File fichero) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
        try {


            Object personajeContenedor = ois.readObject();
            while (personajeContenedor != null) {
                if (personajeContenedor instanceof Personaje) {
                    Main.agregarPersonaje((Personaje) personajeContenedor);
                }
                personajeContenedor = ois.readObject();
            }
            ois.close();
        }  catch(EOFException ex) // es nesesaria esta excepcion  para que al leer el final del archivo no se caiga el programa o se podria poner algun simbolo que detenga la lectura al final del archivo
        {
            System.out.println("\nFinal de archivo, carga de datos correcta");
        }
    }




}
