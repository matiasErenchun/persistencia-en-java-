import java.io.Serializable;
import java.util.ArrayList;

public class Personaje implements Serializable
{
    private String name;
    private String raza;
    private String clase;
    private int edad;
    private ArrayList<Objeto>mochila;

    public Personaje(String name, String raza, String clase, int edad)
    {
        this.name = name;
        this.raza = raza;
        this.clase = clase;
        this.edad = edad;
        this.mochila= new ArrayList<Objeto>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza)
    {
        this.raza = raza;
    }

    public String getClase()
    {
        return clase;
    }

    public void setClase(String clase)
    {
        this.clase = clase;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    public String myToString()
    {
        return this.name+" es de la raza "+this.raza+" tiene una edad de  "+this.edad+" y es de la clase "+this.clase;
    }

    public void agregarObjeto(String nombre,float valor, float peso)
    {

        Objeto ob=new Objeto(nombre,valor,peso);
        this.mochila.add(ob);
    }

    public void mostrarObjetos()
    {
        for (Objeto obj: this.mochila)
        {
            obj.mostrarObjeto();
        }
    }

}
