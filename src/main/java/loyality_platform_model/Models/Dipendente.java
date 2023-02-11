package loyality_platform_model.Models;

import java.util.Objects;


/**
 * Class representing an Employee within this platform.
 * An employee, based on the restrictions that are imposed
 * on him (initially default) is able to perform a series
 * of activities, such as creating cards for customers,
 * identifying customers and performing a series of activities
 * within their profile and other things.
 * An employee in the future could also have full access to the
 * platform, if the Store Manager changes his permissions.
 * At that point, he is also able to carry out other particular
 * activities.
 */
public class Dipendente {

    private static int contatoreDipendente = 0;

    /**
     * This attribute represent the id for
     * this Employee.
     */
    private int idDipendente;

    /**
     * This attribute represent the Name
     * for this Employee.
     */
    private final String nome;

    /**
     * This attribute represent the Surname
     * for this Employee.
     */
    private final String cognome;

    /**
     * This attribute represent the email
     * for this Employee.
     */
    private String email;

    /**
     * This attribute represent the restriction
     * for this Employee. If the restrictions are false,
     * it means they have full access to the entire platform.
     */
    private boolean restrizioni;


    /**
     * Constructor that creates an object of type Employee.
     * It will be created if a Store Manager wants to create a new user with access to the platform.
     *
     * @param nome        the name for the Employee.
     * @param cognome     the surname for the Employee.
     * @param email       the email for the Employee.
     * @param restrizioni the restrictions for the Employee.
     */
    public Dipendente(String nome, String cognome, String email, boolean restrizioni) {
        this.idDipendente = ++contatoreDipendente;
        if (Objects.equals(nome, "") || Objects.equals(cognome, "") || Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal value for nome, cognome or email");
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.restrizioni = restrizioni;
    }

    public int getIdDipendente() {
        return idDipendente;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (Objects.equals(email, ""))
            throw new IllegalArgumentException("Illegal email for Employee.");
        this.email = email;
    }

    public boolean isRestrizioni() {
        return this.restrizioni;
    }

    public void setRestrizioni(GestorePuntoVendita gestorePuntoVendita, boolean restrizioni) {
        Objects.requireNonNull(gestorePuntoVendita);
        this.restrizioni = restrizioni;
    }

    /**
     * Equals method of the Employee (Dipendente) class, simply
     * compare if the passed object is equivalent to this Employee(Dipendente),
     * by checking the id, name and email about the Employee.
     * Returns true if the object is equal, false otherwise.
     *
     * @param object the Object to compare.
     * @return true if is equals, false otherwise.
     */
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof Dipendente tmp) {
            if (this.getIdDipendente() == tmp.getIdDipendente() && this.getNome() == tmp.getNome() &&
                    this.getEmail() == tmp.getDetails())
                return true;
        }
        return false;
    }

    public String getDetails() {
        return "\t-DETAILS EMPLOYEE-" +
                "\nNome : " + this.getNome() +
                "\nCognome : " + this.getCognome() +
                "\nEmail : " + this.getEmail() +
                "\nRestrizioni : " + this.isRestrizioni();
    }

}