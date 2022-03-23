/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retodigimon;

import Sleer1.SLeer1;
import java.sql.*;

/**
 *
 * @author ismael
 */
public class Conexion {

    public static final String URL = "jdbc:mysql://localhost:3306/digimon";
    public static final String USER = "root";
    public static final String CLAVE = "123";

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }

    public static void crearJugador() throws Exception {
        Jugador j1 = new Jugador();
        String nomJug = SLeer1.datoString("Introduce el nombre de usuario: ");
        j1.setNick(nomJug);
        String conJug = SLeer1.datoString("Introduce la contrseña del usuario: ");
        j1.setContrasenya(conJug);

        Connection con = getConexion();
        String insert = "INSERT INTO jugador (nick, contraseña) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, j1.getNick());
        ps.setString(2, j1.getContrasenya());
        ps.executeUpdate();
        System.out.println("Se ha agregado el jugador " + nomJug + " a la base de datos\n\n");
    }

    public static void crearDigimon() throws Exception {

        Digimon d1 = new Digimon();
        System.out.println(d1.getTipo());

        String nomDig = SLeer1.datoString("Introduce el nombre del digimon: ");
        d1.setNombreDig(nomDig);

        int atqDig = SLeer1.datoInt("Introduce el ataque del digimon: ");
        d1.setAtaque(atqDig);

        int defDig = SLeer1.datoInt("Introduce la defensa del digimon: ");
        d1.setDefensa(defDig);
        SLeer1.limpiar();
        
        boolean bandera = false;
        do {
            String t = SLeer1.datoString("Introduce el tipo (NULO,VACUNA,VIRUS,ANIMAL,PLANTA,ELEMENTAL): ").toUpperCase();

            if (t.equals(Tipo.NULO.name())) {
                d1.setTipo(Tipo.NULO);
                bandera = true;
            } else if (t.equals(Tipo.VACUNA.name())) {
                d1.setTipo(Tipo.VACUNA);
                bandera = true;
            } else if (t.equals(Tipo.VIRUS.name())) {
                d1.setTipo(Tipo.VIRUS);
                bandera = true;
            } else if (t.equals(Tipo.ANIMAL.name())) {
                d1.setTipo(Tipo.ANIMAL);
                bandera = true;
            } else if (t.equals(Tipo.PLANTA.name())) {
                d1.setTipo(Tipo.PLANTA);
                bandera = true;
            } else if (t.equals(Tipo.ELEMENTAL.name())) {
                d1.setTipo(Tipo.ELEMENTAL);
                bandera = true;
            } else {
                System.err.println("Tipo no válido.\n");
            }

        } while (bandera != true);
        
        int nvlDig = SLeer1.datoInt("Introduce el nivel del Digimon: ");
        d1.setNivel(nvlDig);
        SLeer1.limpiar();
        
        String evoDigEvo = SLeer1.datoString("Introduce el nombre de la digievolucion: ");
        d1.setNombreDigEvo(evoDigEvo);
        
        Connection con = getConexion();
        String insertdos = "INSERT INTO digimon (nomDig, ataque, defensa, tipo, nivel, nomDigiEv) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insertdos);
        ps.setString(1, d1.getNombreDig());
        ps.setInt(2, d1.getAtaque());
        ps.setInt(3, d1.getDefensa());
        ps.setString(4, d1.getTipo().name());
        ps.setInt(5, d1.getNivel());
        ps.setString(6, d1.getNombreDigEvo());
        ps.executeUpdate();
        System.out.println("Se ha agregado el digimon " + nomDig + " a la base de datos\n\n");

    }
}
