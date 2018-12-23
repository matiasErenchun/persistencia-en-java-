import java.io.*;
import java.util.ArrayList;

import com.google.gson.*;


public class Main
{
    private static ArrayList<Personaje> listaDePersonajes;
    private static Lector lector;
    private static Gson miGson;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       lector= new Lector();
       listaDePersonajes = new ArrayList<Personaje>();
       miGson = new Gson();
       File fichero = new File("D:/repo git local 2/persitencia en java/persistencia-en-java-/pruebas de persistencia en java/data.txt");// al momento de eliminar esta direccion es la valida.
       fichero=Main.crearArchivo(fichero);
       /*if(fichero.exists()) {
           Main.leerDatos(fichero);
       }
        */
       Main.leerConGson();
       Main.menu();
       String stringFile =  Main.guardarEnGson();
       Main.escribirGson(stringFile);
       //Main.escribirDatos(fichero);
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
                case 3:
                    Main.agregarObjetos();
                    break;

                default:
                    System.out.println("recuerde ingresar un valor valido: ");
                    System.out.println("presione 0 para salir.");
                    System.out.println("presione 1 para agregar un personaje.");
                    System.out.println("presione 2 para mostrar todos los personajes.");
                    System.out.println("presione 3 para agregar objeto aun  personajes.");
                    break;


            }

        }


    }

    private static void agregarObjetos()
    {
        System.out.println("ingrese el nombre del pj al que quiere agregar el objeto");
        String nombre = lector.leerContraseña();
        int i=0;
        while(i<listaDePersonajes.size())
        {
            Personaje p=listaDePersonajes.get(i);
            if(p.getName().equalsIgnoreCase(nombre))
            {
                System.out.println("ingrese el nombre del objeto");
                String name = lector.leerContraseña();
                System.out.println("ingrese el valor del objeto");
                String valorr=lector.leerContraseña();
                float valor =Float.parseFloat(valorr);
                System.out.println("ingrese el valor del objeto");
                String pesoo=lector.leerContraseña();
                float peso =Float.parseFloat(pesoo);
                p.agregarObjeto(name,valor,peso);
                listaDePersonajes.get(i).mostrarObjetos();
                break;
            }
            i++;
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
                pj.mostrarObjetos();
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

    private static void escribirGson( String string)throws IOException
    {
       FileWriter archivo = new FileWriter("D:/repo git local 2/persitencia en java/persistencia-en-java-/pruebas de persistencia en java/data.json");
       archivo.write(string);// se escriben los dato en el archivo.json
       archivo.close();

    }

    private static void leerConGson() throws IOException {

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("D:/repo git local 2/persitencia en java/persistencia-en-java-/pruebas de persistencia en java/data.json"));
            String json= br.readLine();// leemos la linea donde estan los datos
            Personaje[] jArr = miGson.fromJson(json,Personaje[].class);// guardamos los objetos principales en una lista de objetos de ese tipo
            System.out.println(jArr.length);
            for (Personaje p: jArr)
            {
                listaDePersonajes.add(p);// agregamos todos los objetos a la lista correspondiente.
                System.out.println(p.getName());
            }



        }

        catch (FileNotFoundException e)
        {
                e.printStackTrace();
        }
        finally
        {
            if(br!=null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
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

    public static String guardarEnGson()
    {
         String jSon = miGson.toJson(listaDePersonajes);
        return jSon;
    }



}
