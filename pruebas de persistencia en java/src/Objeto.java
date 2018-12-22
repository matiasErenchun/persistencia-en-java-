public class Objeto
{
    private String name;
    private float peso;
    private float valor;

    public Objeto(String name, float valor, float peso)
    {
        this.name = name;
        this.peso = peso;
        this.valor = valor;
    }

    public String getName()
    {
        return name;
    }

    public float getPeso()
    {
        return peso;
    }

    public float getValor()
    {
        return valor;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }
}
