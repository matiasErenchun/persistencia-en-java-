import java.util.Scanner;

public class Lector
{
    private Scanner lector;
    public Lector()
    {
        this.lector = new Scanner(System.in);
    }

    public String leerContraseña()
    {
        String contraseña = this.lector.nextLine();
        while(contraseña.equals("")|| contraseña.equals(null))
        {
            System.out.println(" ingrese un nombre valido");
            contraseña = this.lector.nextLine();
        }
        return contraseña;
    }

    public Integer leerNumero(String mensaje)
    {
        System.out.println(mensaje);
        String  numero = this.lector.nextLine();
        boolean seguir = true;
        while (seguir)
        {
            if(numero.matches("^[0-9]+$"))
            {
                seguir=false;
            }
            else
            {
                System.out.println("error en los campos ingresados por favor siga las instrucciones");
                System.out.println(mensaje);
                numero = this.lector.nextLine();
            }

        }
        return Integer.parseInt(numero);
    }

}
