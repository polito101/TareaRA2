package es.dam.ad.modelo;

public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private double saldo;

    public Cliente() {}

    public Cliente(int id, String nombre, String email, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.saldo = saldo;
    }

    // Constructor sin id para INSERT
    public Cliente(String nombre, String email, double saldo) {
        this.nombre=nombre;
        this.email=email;
        this.saldo=saldo;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
  

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

