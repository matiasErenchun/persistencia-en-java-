import java.io.Serializable;

public class Personaje implements Serializable
{
    private String name;
    private String raza;
    private String clase;
    private int edad;

    public Personaje(String name, String raza, String clase, int edad)
    {
        this.name = name;
        this.raza = raza;
        this.clase = clase;
        this.edad = edad;
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

    public String ToString()
    {
        return this.name+" es de la raza "+this.raza+" tiene una edad de  "+this.edad+" y es de la clase "+this.clase;
    }
}
