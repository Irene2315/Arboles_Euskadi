package principal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import clases.Arbol;

public class GestorArbolApp {
	private static final String HOST= "localhost";
	private static final String BBDD= "arboles";
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	
	public static void run() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner (System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection ("jdbc:mysql://"+HOST+ "/"+BBDD,USERNAME,PASSWORD);
			Arbol arbol = new Arbol();
			PreparedStatement preparedSt;
			Scanner teclado = new Scanner(System.in);
			final int INSERTAR_ARBOL = 1;
			final int ELIMINAR_ARBOL = 2;
			final int MODIFICAR_ARBOL = 3;
			final int VISUALIZAR_ARBOLES = 4;
			final int SALIR = 0;
			int opcion_menu;

			do {
				System.out.println("------MENU-------");
				System.out.println(INSERTAR_ARBOL + ". Insertar arbol");
				System.out.println(ELIMINAR_ARBOL + ". Eliminar arbol");
				System.out.println(MODIFICAR_ARBOL + ". Modificar arbol");
				System.out.println(VISUALIZAR_ARBOLES + ". Visualizar arboles");
				System.out.println(SALIR + ". Salir");
				System.out.println("*******************");
				System.out.println("Elije una de las opciones");
				opcion_menu = teclado.nextInt();

				switch (opcion_menu) {
				
				case INSERTAR_ARBOL:
					
					arbol = new Arbol();
					String nombreComun;
					String nombreCientifico;
					String habitat;
					int altura;
					String origen;
					
					System.out.println("Introduce el nombre comun del arbol");
				    nombreComun= scan.nextLine();
				    arbol.setNombreComun(nombreComun);
				    
				    System.out.println("Introduce el nombre cientifico del arbol");
				    nombreCientifico= scan.nextLine();
				    arbol.setNombreCientifico(nombreCientifico);
				    
				    System.out.println("Introduce el habitat del arbol");
				    habitat= scan.nextLine();
				    arbol.setHabitat(habitat);
				    
				    System.out.println("Introduce la altura del arbol");
				    altura= scan.nextInt();
				    arbol.setAltura(altura);
				    
				    System.out.println("Introduce el origen del arbol");
				    scan.nextLine();
				    origen= scan.nextLine();
				    arbol.setOrigen(origen);
					
					
					
					//INSERTAR ARBOL
					//INSERT INTO `arboles_euskadi` (, `nombre_comun`, `nombre_cientifico`, `habitat`, `altura`, `origen`) VALUES (, '', '', '', '', '')
					
					
					
					preparedSt = con.prepareStatement("INSERT INTO `arboles_euskadi` "
							+ "(`nombre_comun`, `nombre_cientifico`, `habitat`, `altura`, `origen`) VALUES "
							+ "(?, ?,?,?, ?)");
					preparedSt.setString(1 , arbol.getNombreComun());
					preparedSt.setString(2 , arbol.getNombreCientifico());
					preparedSt.setString(3 , arbol.getHabitat());
					preparedSt.setInt(4 , arbol.getAltura());
					preparedSt.setString(5 , arbol.getOrigen());
					
					preparedSt.execute();

					break;
					
				case ELIMINAR_ARBOL:
					arbol = new Arbol();
					int arbolEliminar;
					//ELIMINAR ARBOL
					// "DELETE FROM arboles_euskadi WHERE nombre_comun= 'pino' "
					System.out.println("Introduce el id del arbol que quieras eliminar");
					arbolEliminar= scan.nextInt();
					arbol.setId(arbolEliminar);
					
					preparedSt = con.prepareStatement("DELETE FROM arboles_euskadi WHERE id = ?");
					preparedSt.setInt(1,arbol.getId());
					preparedSt.execute();
					break;
				case MODIFICAR_ARBOL:
					//modificar arbol
					//UPDATE `arboles_euskadi` SET `nombre_comun` = 'pin' WHERE `arboles_euskadi`.`id` = 1
					arbol = new Arbol();
					int arbolModificar;
					String nuevoNombreCo;
					String nuevoNombreCi;
					String nuevoHabitat;
					int nuevaAltura;
					String nuevoOrigen;
					System.out.println("Introduce el id del arbol que quieras modificar");
					arbolModificar= scan.nextInt();
					arbol.setId(arbolModificar);
					
					scan.nextLine();
					System.out.println("Introduce el nuevo nombre comun");
					nuevoNombreCo=scan.nextLine();
					arbol.setNombreComun(nuevoNombreCo);
					
					System.out.println("Introduce el nuevo nombre cientifico");
					nuevoNombreCi=scan.nextLine();
					arbol.setNombreCientifico(nuevoNombreCi);
					
					System.out.println("Introduce el nuevo habitat");
					nuevoHabitat=scan.nextLine();
					arbol.setHabitat(nuevoHabitat);
					
					System.out.println("Introduce el nuevo nueva altura");
					nuevaAltura=scan.nextInt();
					arbol.setAltura(nuevaAltura);
					
					System.out.println("Introduce el nuevo origen");
					scan.nextLine();
					nuevoOrigen=scan.nextLine();
					arbol.setOrigen(nuevoOrigen);
					
					
					
					//UPDATE `arboles_euskadi` SET `nombre_comun` = 'dddd', `nombre_cientifico` = 'jj', `habitat` = 'sss', `origen` = 'gg' WHERE `arboles_euskadi`.`id` = 3

					
					
					
					preparedSt = con.prepareStatement("UPDATE arboles_euskadi SET nombre_comun = ?,"
						    + " nombre_cientifico = ?, habitat = ?, altura = ?, origen = ? "
							+ "WHERE arboles_euskadi.id = ?");
					preparedSt.setString(1,arbol.getNombreComun() );
					preparedSt.setString(2,arbol.getNombreCientifico() );
					preparedSt.setString(3,arbol.getHabitat());
					preparedSt.setInt(4,arbol.getAltura() );
					preparedSt.setString(5,arbol.getOrigen() );
					preparedSt.setInt(6,arbol.getId() );
					preparedSt.executeUpdate();
					
					break;
				case VISUALIZAR_ARBOLES:
					String senteciaSelect="SELECT * FROM arboles_euskadi";
					Statement st = con.createStatement();
					ResultSet resultado = st.executeQuery(senteciaSelect);	
					
					ArrayList<Arbol> arboles = new ArrayList<Arbol>();
					while (resultado.next()) {
						arbol = new Arbol();
						arbol.setId( resultado.getInt(1));
						arbol.setNombreComun(resultado.getString(2));
						arbol.setNombreCientifico(resultado.getString(3));
						arbol.setHabitat(resultado.getString(4));
						arbol.setAltura(resultado.getInt(5));
						arbol.setOrigen(resultado.getString(6));
						
						arboles.add(arbol);
				    
					}
					for (Arbol arbol2 : arboles) {
						System.out.println(arbol2);
					}
					
					break;
				case SALIR:

				}
				System.out.println("");
			} while (opcion_menu != SALIR);
			teclado.close();

			
			
		
			
			
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		scan.close();	
			}
	
	

}